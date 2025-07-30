package com.kruthik.scm.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

	String uploadImage(String userEmail, String contactName, MultipartFile profilePic);

}
