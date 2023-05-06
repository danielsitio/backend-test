package com.danest.backend.service;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {

    private final Path storageLocation;

    public StorageService() throws Exception {
        this.storageLocation = Paths.get("images").toAbsolutePath().normalize();
        Files.createDirectories(this.storageLocation);
    }

    public void delete(String fileName) {
        File fileToDelete = new File(this.storageLocation.resolve(fileName).toString());
        fileToDelete.delete();
    }

    public void store(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        Path filePath = this.storageLocation.resolve(file.getOriginalFilename());
        Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
    }

    public Path load(String filename) {
        return null;
    }

    public Path getStorageLocation() {
        return storageLocation;
    }
}