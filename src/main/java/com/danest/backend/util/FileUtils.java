package com.danest.backend.util;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtils {

    @Value("${custom.image-directory}")
    static private String imageDirectory;

    static public String generateUrl(String name, MultipartFile file) {
        return imageDirectory + name + "." + FilenameUtils.getExtension(file.getOriginalFilename());
    }
}
