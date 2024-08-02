package com.winnguyen1905.technologystore.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import com.winnguyen1905.technologystore.model.dto.FileDTO;

public interface IFileService {
    void handleCreateDirectory(String folder) throws URISyntaxException;
    FileDTO handleUploadFile(String dest, MultipartFile file) throws URISyntaxException, IOException;
    Long getFileLength(String fileName, String dest) throws URISyntaxException;
    InputStreamResource getResource(String fileName, String dest) throws FileNotFoundException, URISyntaxException;
}