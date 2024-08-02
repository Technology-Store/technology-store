package com.winnguyen1905.technologystore.util;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.winnguyen1905.technologystore.exception.CustomRuntimeException;

public class FileValidationUtils {
    public static Boolean isNullOrEmpty(MultipartFile file) {
        return file == null || file.isEmpty();
    }

    @SuppressWarnings("null")
    public static Boolean isAllowedExtensions(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        List<String> allowedExtensions = Arrays.asList("pdf", "jpg", "jpeg", "png", "doc", "docx", "txt");
        return !allowedExtensions.stream().anyMatch(ext -> fileName.toLowerCase().endsWith("." + ext));
    }

    public static Boolean isAllowedMimeTypes(MultipartFile file) {
        List<String> allowedMimeTypes = Arrays.asList(
        "application/pdf",
            "image/jpeg",
            "image/png",
            "application/msword",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
        );
        String contentType = file.getContentType();
        return !allowedMimeTypes.contains(contentType);
    }

    public static void validation(MultipartFile file) {
        if(isNullOrEmpty(file)) throw new CustomRuntimeException("file upload empty");
        if(isAllowedMimeTypes(file)) throw new CustomRuntimeException("Unsupported mime type", 412);
        if(isAllowedExtensions(file)) throw new CustomRuntimeException("file unsupported media type", 415);
    }
}