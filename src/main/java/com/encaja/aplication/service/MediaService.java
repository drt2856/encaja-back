package com.encaja.aplication.service;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class MediaService {
    private static final String UPLOADED_FOLDER = "D:\\Daniel RT\\Espacio para programar\\PosCu\\Encaja-Server-Media\\";
    private static final long MAX_FILE_SIZE = 4000000L;

    public String saveUserMedia(MultipartFile uploadfile,String whatIsthis) {
        if (uploadfile.isEmpty()) {
            throw new RuntimeException("Por favor selecciona un archivo");
        } else if (!uploadfile.getContentType().startsWith("image")) {
            throw new RuntimeException("Solo se permiten imagenes");
        } else if (uploadfile.getSize() > MAX_FILE_SIZE) {
            throw new RuntimeException("El archivo es demasiado grande");
        } else {
            try {


                byte[] bytes = uploadfile.getBytes();
                Path filePath = Paths.get(UPLOADED_FOLDER +whatIsthis+"\\"+ UUID.randomUUID().toString() + ".png");
                Files.write(filePath, bytes, new OpenOption[0]);
                return filePath.getFileName().toString();
            } catch (Exception var4) {
                throw new RuntimeException(var4);
            }
        }
    }

    public Resource viewImage(String imageName,String whatIsthis) {
        try {
            Path filePath = Paths.get(UPLOADED_FOLDER+ whatIsthis+"\\" + imageName );
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists() && !resource.isReadable()) {
                throw new EntityNotFoundException("no se econtro imagen");
            } else return resource;
        } catch (Exception var4) {
            throw new EntityNotFoundException(var4);
        }
    }

}
