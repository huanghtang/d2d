package com.sandu.util;

public class PathUtil {
	private static String seperator = System.getProperty("file.separator");
	public static String getImgBasePath() {
		String os = System.getProperty("os.name");
		String basePath="";
		if(os.toLowerCase().startsWith("win")) {
			basePath="D:/projectdev/d2dimage";
		}else {
			//linux系统下的存放路径
			basePath="/home/d2d/image/";
		}
		basePath=basePath.replace("/", seperator);
		return basePath;
	}
	
	public static String getShopImagePath(long shopId) {
		String imagePath="/upload/item/shop/"+shopId+"/";
		return imagePath.replace("/", seperator);
	}
	
	public static void main(String[] args) {
		System.out.println(getImgBasePath());
	}
}
