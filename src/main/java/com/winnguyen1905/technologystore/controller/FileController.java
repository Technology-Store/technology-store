package com.winnguyen1905.technologystore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.winnguyen1905.technologystore.exception.CustomRuntimeException;
import com.winnguyen1905.technologystore.model.dto.FileDTO;
import com.winnguyen1905.technologystore.service.IFileService;
import com.winnguyen1905.technologystore.util.FileValidationUtils;
import com.winnguyen1905.technologystore.util.annotation.MetaMessage;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("${release.api.prefix}/files")
public class FileController {
    @Autowired
    private IFileService fileService;

    @Value("${realestate.upload-file.base-uri}")
    private String uri;

    @PostMapping
    @MetaMessage(message = "Upload file success")
    public ResponseEntity<FileDTO> upload(
        @RequestParam(name = "file", required = true) MultipartFile file,
        @RequestParam(name = "folder", required = true) String folderName
    ) throws Exception {
        FileValidationUtils.validation(file);
        this.fileService.handleCreateDirectory(this.uri + folderName);
        return ResponseEntity
            .status(HttpStatus.CREATED.value())
            .body(this.fileService.handleUploadFile(this.uri + folderName, file));
    }

    @GetMapping
    public ResponseEntity<Resource> download(
        @RequestParam(name = "fileName", required = true) String fileName,
        @RequestParam(name = "folder", required = true) String folder
    ) throws URISyntaxException, FileNotFoundException {
        Long fileLength = this.fileService.getFileLength(fileName, this.uri + folder);
        InputStreamResource resource = this.fileService.getResource(fileName, this.uri + folder);
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName)
            .contentLength(fileLength)
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(resource);
    }
}