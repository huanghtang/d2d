package com.sandu.dto;

import java.io.File;

public class ImageHolder {
	private String imageName;
	private File image;
	
	public ImageHolder(String imageName,File image) {
		this.image=image;
		this.imageName=imageName;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}
	
}
