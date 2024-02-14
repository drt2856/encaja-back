package com.encaja.aplication.service;

import com.encaja.aplication.Iservice.IUserService;
import com.encaja.domain.model.Authority;
import com.encaja.domain.model.AuthorityPK;
import com.encaja.domain.model.Bussines;
import com.encaja.domain.model.Users;
import com.encaja.domain.repository.UserRepository;
import com.encaja.infraestructure.dto.ChangePaswordDto;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BussinesService bussinesService;
    @Autowired
    private MediaService mediaService;


    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Users findById(String id) {
        if (userRepository.existsById(id)) {

            return userRepository.findById(id).get();
        } else {
            throw new EntityNotFoundException("There is no user with this id");
        }
    }

    @Override
    public Users findByUserName(String username) {

        Optional<Users> user = userRepository.findAll()
                .stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new EntityNotFoundException("This user does not exist or There is no user with this UserName");
        }

    }

    @Override
    public Boolean exitsById(String userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public Users createUser(Users user, MultipartFile uploadfile) {
        if (!existByUserName(user.getUsername())) {
            user.setId(UUID.randomUUID().toString());
            user.setEnabled(true);

            user.setAuthorityList(List.of(new Authority(new AuthorityPK(user.getId(), "USER"))));

            if(uploadfile!=null){
                user.setUrlImageProfile(mediaService.saveUserMedia(uploadfile,"users"));
            }else{
                user.setUrlImageProfile("");
            }



            return userRepository.save(user);
        } else {
            throw new EntityExistsException("This user name is already registered");
        }
    }

    @Override
    public Users createUsetWithAutorities(Users user) {
        return null;
    }


    @Override
    public Users update(Users user, MultipartFile file) {
        if (userRepository.existsById(user.getId())) {
            user.setAuthorityList(List.of(new Authority(new AuthorityPK(user.getId(), "USER"))));

            try {
                if(file!=null){
                    user.setUrlImageProfile(mediaService.saveUserMedia(file,"users"));
                }
                return userRepository.save(user);
            } catch (Exception e) {
                throw new EntityNotFoundException("Ya existe un usuario con ese nombre de usuario");
            }

        } else {
            throw new EntityNotFoundException("This user does not exist or There is no user with this id");
        }
    }


    @Override
    public void deleteById(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("This user does not exist or There is no user with this id");
        }

    }


    @Override
    public Boolean changePaswword(ChangePaswordDto changePaswordDto) {
        //validar contraseña nueva, que no haya ningun dato en blanco, cifrar contraseñas
        Users user = findById(changePaswordDto.getIdUser());
        if (user.getPassword().equals(changePaswordDto.getOldPassword())) {
            user.setPassword(changePaswordDto.getNewPassword());
            userRepository.save(user);
            return true;
        } else {
            throw new RuntimeException("This password is not correct");
        }

    }

    @Override
    public Boolean existByUserName(String username) {
        return userRepository.findAll().stream().anyMatch(users -> users.getUsername().equals(username));
    }


    @Override
    public Users addNewUserForBussines(String idOwner, Users employed, MultipartFile uploadfile) {



        Bussines bussines = bussinesService.findBussinesByUserId(idOwner);
        employed.setBussinesid(new Bussines(bussines.getId()));
        employed = createUser(employed,uploadfile);
        return employed;
    }

    @Override
    public List<Users> findAllWorkersByOnwerUserName(String ownerUsername) {

        String bussinesId = bussinesService.findBussinesByUserName(ownerUsername).getId();
        return findAll()
                .stream()
                .filter(users -> {
                            if (users.getBussinesid() != null) {
                                return users.getBussinesid().getId().equals(bussinesId);
                            }
                            return false;
                        }
                )
                .collect(Collectors.toList());
    }


}
