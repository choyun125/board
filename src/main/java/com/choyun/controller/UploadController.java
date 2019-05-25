package com.choyun.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.choyun.util.UploadFileUtils;
import com.choyun.util.UploadFileUtils.MediaUtils;

@Controller
@RequestMapping("/upload")
public class UploadController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	@RequestMapping("list")
	public String list() {
		
		return "board/BoardFileUpload";
	}
	
	@RequestMapping("ajax")
	public String listajax() {
		
		return "board/BoardFileUploadAjax";
	}
	
	@RequestMapping(value = "/uploadForm", method = RequestMethod.GET)
	public void uploadForm() {
		
	}
	
	@RequestMapping(value = "/uploadAjax", method = RequestMethod.GET)
	public void uploadAjax() {
		
	}
	
	// 파일 업로드
	@ResponseBody
	@RequestMapping(value = "/uploadAjax", method = RequestMethod.POST, produces = "text/plain; charset=UTF-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {
		
		return new ResponseEntity<>(UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()), HttpStatus.CREATED);
	}
	
	// 업로드 파일 삭제
	@ResponseBody
	@RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
	public ResponseEntity<String> deleteFile(String fileName) {
		
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
		MediaType mType = MediaUtils.getMediaType(formatName);
		if (mType != null) {
			String front = fileName.substring(0, 12);
			String end = fileName.substring(14);
			new File(uploadPath + (front + end).replace('/', File.separatorChar)).delete();
		}
		new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
	
	// 파일 다운로드
	@ResponseBody
	@RequestMapping("/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
		
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		try {
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
			MediaType mType = MediaUtils.getMediaType(formatName);
			HttpHeaders headers = new HttpHeaders();
			in = new FileInputStream(uploadPath + fileName);
			if (mType != null) {
				headers.setContentType(mType);
			}
			else {
				fileName = fileName.substring(fileName.indexOf("_") + 1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
			}
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		}
		catch (Exception e) {
			logger.error("-- UPLOAD PRINT ERROR --");
			logger.error(e.toString());
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}
		finally {
			in.close();
		}
		return entity;
	}
}
