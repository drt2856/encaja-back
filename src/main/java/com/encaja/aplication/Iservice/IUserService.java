package com.encaja.aplication.Iservice;



import com.encaja.domain.model.Users;
import com.encaja.infraestructure.dto.ChangePaswordDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IUserService {

    List<Users> findAll();

    Users findById(String id);

    Users findByUserName(String id);

    Boolean exitsById(String userId);

    Users createUser(Users user, MultipartFile uploadfile);

    Users createUsetWithAutorities(Users user);

    Users update(Users user, MultipartFile file);

    void deleteById(String id);



    Boolean changePaswword(ChangePaswordDto changePaswordDto) ;

    Boolean existByUserName(String username);


    Users addNewUserForBussines(String idOwner, Users employed, MultipartFile uploadfile);

    List<Users> findAllWorkersByOnwerUserName(String ownerUsername);
}
