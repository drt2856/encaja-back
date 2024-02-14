package com.encaja.infraestructure.controller;


import com.encaja.aplication.service.MediaService;
import com.encaja.domain.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/media")
public class MultimediaController {

    private final MediaService mediaService;

    @Autowired
    public MultimediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }




    @GetMapping({"/{whatIsThis}/{imageName}"})
    public Resource viewImage(@PathVariable String imageName,@PathVariable String whatIsThis) {
        return mediaService.viewImage(imageName,whatIsThis);
    }

}