package com.danest.backend.controller;

import java.io.File;
import java.nio.file.Paths;

import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danest.backend.service.StorageService;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final StorageService storageService;

    ImageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/{fileName}")
    Resource getImageAsResource(@PathVariable String fileName) {
        String fileUrl = this.storageService.getStorageLocation().resolve(fileName).toString();
        File file = new File(fileUrl);
        if (file.exists())
            return new PathResource(Paths.get(fileUrl));
        return null;
    }
}
