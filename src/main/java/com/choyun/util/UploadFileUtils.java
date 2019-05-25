package com.choyun.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);
	
	public static String uploadFile(String uploadPath, String originalName, byte[] fileData) {
		
		String uploadedFileName = null;
		
		try {
			
			UUID uid = UUID.randomUUID();
			
			String savedName = uid.toString() + "_" + originalName;
			String savedPath = calcPath(uploadPath);
			
			File target = new File(uploadPath + savedPath, savedName);
			FileCopyUtils.copy(fileData, target);
			
			String formatName = originalName.substring(originalName.lastIndexOf(".") + 1);
			
			// Image File 인 경우 Thumbnail 생성
			if (MediaUtils.getMediaType(formatName) != null) {
				
				uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);
			}
			
			else {
				
				uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
			}
		}
		
		catch (Exception e) {
			
			logger.error("------ File Upload ERROR ------");
			logger.error(e.toString());
		}
		
		return uploadedFileName;
	}
	
	// 경로 처리 문자열 Return
	private static String makeIcon(String uploadPath, String path, String fileName) throws Exception {
		
		String iconName = null;
		
		try {
			
			iconName = uploadPath + path + File.separator + fileName;
		}
		
		catch (Exception e) {
			
			logger.error("------ CREATE PATH NAME ERROR ------");
			logger.error(e.toString());
		}
		
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	// 저장 경로 확인
	private static String calcPath(String uploadPath) {
		
		Calendar cal = Calendar.getInstance();
		
		String yearPath = File.separator + cal.get(Calendar.YEAR);
		
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		makeDir(uploadPath, yearPath, monthPath, datePath);
		
		return datePath;
	}
	
	// 저장될 Path가 존재하지 않는 경우 경로 생성
	private static void makeDir(String uploadPath, String ... paths) {
		
		if (new File(paths[paths.length - 1]).exists()) {
			
			return;
		}
		
		for (String path : paths) {
			
			File dirPath = new File(uploadPath + path);
			
			if (!dirPath.exists()) {
				
				dirPath.mkdir();
			}
		}
	}
	
	// Thumbnail Image 생성
	private static String makeThumbnail(String uploadPath, String path, String fileName) throws Exception {
		
		String thumbnailName = null;
		
		try {
			
			BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));
			BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
			
			thumbnailName = uploadPath + path + File.separator + "s_" + fileName;
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
			
			File newFile = new File(thumbnailName);
			ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		}
		
		catch(Exception e) {
			
			logger.error("------ CREATE THUMBNAIL IMAGE ERROR ------");
			logger.error(e.toString());
		}
		
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	// Image Type 식별
	public static class MediaUtils {
		
		private static Map<String, MediaType> mediaMap;
		
		static {
			
			mediaMap = new HashMap<String, MediaType>();
			
			mediaMap.put("JPG", MediaType.IMAGE_JPEG);
			mediaMap.put("GIF", MediaType.IMAGE_GIF);
			mediaMap.put("PNG", MediaType.IMAGE_PNG);
		}
		
		public static MediaType getMediaType(String type) {
			
			return mediaMap.get(type.toUpperCase());
		}
	}
}
