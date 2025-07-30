package com.kruthik.scm.services.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.kruthik.scm.services.ImageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

	private final Cloudinary cloudinary;

	@Override
	public String uploadImage(String userEmail, String contactName, MultipartFile profilePic) {
		
		String folder = "scm/"+userEmail;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
		String timestamp = LocalDateTime.now().format(formatter);
		String public_id = contactName+"-"+timestamp;

		try {
			Map<?, ?> uploadResult = cloudinary.uploader().upload(profilePic.getBytes(),
					ObjectUtils.asMap("folder", folder, "public_id", public_id, "overwrite", true));

			// Get the URL from the response
			return uploadResult.get("secure_url").toString();

		} catch (IOException e) {

			e.printStackTrace();
			throw new RuntimeException("Failed to upload image", e);

		}

	}
}
	
	/*
	 * cloudinary.uploader().upload(...) 
	 * 		-> This is the main method provided by Cloudinary's Java SDK. 
	 * 		-> It uploads a file (in bytes, Base64, or URL) to your Cloudinary account
	 * 
	 * profilePic.getBytes()
	 * 		-> profilePic is a MultipartFile (from an HTML form).
	 * 		-> getBytes() converts the uploaded file into a byte[], which Cloudinary can accept.
	 * 		-> This is equivalent to uploading the raw content of the image file.
	 * 
	 * ObjectUtils.emptyMap()
	 * 		-> This means "use default upload options."
	 * 		-> You can also provide extra options here (we'll see examples below).
	 */
	
	
//---------------------------------------------------------------------------------------------------------------------------------	
	
	
	/*
	 * 	Example
		content of result Map
			{
			  "asset_id": "abc123",
			  "public_id": "abc123xyz",
			  "version": 1691234567,
			  "signature": "abcd...",
			  "width": 800,
			  "height": 600,
			  "format": "jpg",
			  "resource_type": "image",
			  "secure_url": "https://res.cloudinary.com/your_cloud/image/upload/v1234567890/abc123xyz.jpg"
			}
	*/
	
//---------------------------------------------------------------------------------------------------------------------------------	

	/*
	 	ObjectUtils.asMap(
		    "folder", "user_profile_pics",
		    "public_id", "user_1234_pic",
		    "overwrite", true
		)
		| Option        | Meaning                               |
		| ------------- | ------------------------------------- |
		| `"folder"`    | Uploads image to a specific folder    |
		| `"public_id"` | Custom name (default is random)       |
		| `"overwrite"` | Overwrites existing file if same name |
	*/

