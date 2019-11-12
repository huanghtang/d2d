package com.sandu.util;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtil {
	private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);
	
	private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	private static final SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyyMMddHH");
	
	
	/**
	 * 将CommonsMultipart转换成File类
	 * @param cFile
	 * @return
	 */
	public static File transferCommonsMultipartFileToFile(CommonsMultipartFile cFile) {
		File newFile=new File(cFile.getOriginalFilename());
		try {
			cFile.transferTo(newFile);
		} catch (IllegalStateException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		return newFile;
	}
	
	/**
	 * 处理缩略图，并返回新生成图片的相对值路径
	 * @param thumbnail
	 * @param targetAddr
	 * @return
	 */
	public static String generrateThumbnail(File thumbnail,String targetAddr) {
		String realFileName=getRandomFileName();
		String extension=getExtension(thumbnail);
		makeDirPath(targetAddr);
		String relativeAddr=targetAddr+realFileName+extension;
		logger.debug("relativeAddr:"+relativeAddr);
		File dest = new File(PathUtil.getImgBasePath()+relativeAddr);
		logger.debug("complete addr:"+PathUtil.getImgBasePath()+relativeAddr);
		try {
			Thumbnails.of(thumbnail).size(200, 200)
			.watermark(Positions.BOTTOM_LEFT, ImageIO.read(new File(basePath + "shuiyin.png")), 1f)
			.outputQuality(0.8f).toFile(dest);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			e.printStackTrace();
			
		}
		return relativeAddr;
	}

	private static String getExtension(File cFile) {
		int pointAddr=cFile.getName().lastIndexOf(".");
		String fileName=cFile.getName().substring(pointAddr);
		return fileName;
	}

	private static void makeDirPath(String targetAddr) {
		String realFileParentPath=PathUtil.getImgBasePath()+targetAddr;
		File dirPath=new File(realFileParentPath);
		if(!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}

	private static String getRandomFileName() {
		//获取随机的五位数
		String nowTimeStr=sDateFormat.format(new Date());
		return nowTimeStr+getRandomNumb(5);
	}

	public static String getRandomNumb(int length) { //length表示生成字符串的长度
	    String base = "0123456789";   
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    return sb.toString();   
	 }   
	
	public static void main(String[] args) throws IOException {
	//	String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		/*Thumbnails.of(new File("C:\\Users\\sandu\\Desktop\\星空.jpg")).size(200, 200)
				.watermark(Positions.BOTTOM_LEFT, ImageIO.read(new File(basePath + "/shuiyin.png")), 1f)
				.outputQuality(0.8f).toFile("C:/Users/sandu/Desktop/upload/xingkong.png");*/
		//long startTime=System.currentTimeMillis();
		//System.out.println(getRandomFileName());
		
		//System.out.println(getExtension());
		File f = new File(basePath + "shuiyin.png");
		Desktop.getDesktop().open(f);
		//long endTime=System.currentTimeMillis();
		//System.out.println(endTime-startTime);
	
	}
}
