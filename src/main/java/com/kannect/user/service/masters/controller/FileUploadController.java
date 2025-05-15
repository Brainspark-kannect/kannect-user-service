package com.kannect.user.service.masters.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kannect.user.service.dto.response.SuccessResponse;
import com.kannect.user.service.utils.CloudinaryUploader;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user/masters/file")
@Validated
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class FileUploadController {
	
	private final CloudinaryUploader cloudinaryUploader;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);
	
	@PostMapping("/upload")
	public ResponseEntity<SuccessResponse> uploadFile(@RequestPart("fileName") String fileName,@RequestPart("file")MultipartFile  file) throws IOException {
		
		return ResponseEntity.ok(new SuccessResponse(HttpStatus.OK.value(), HttpStatus.OK,
				"File uploaded successfully", cloudinaryUploader.uploadFile(file,fileName)));
	}

}
