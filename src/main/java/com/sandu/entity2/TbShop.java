package com.sandu.entity2;

import java.util.Date;

public class TbShop {
	private Integer shopId;

	private TbPersonInfo owner;

	private TbArea area;

	private TbShopCategory shopCategory;

	private String shopName;

	private String shopDesc;

	private String shopAddr;

	private String phone;

	private String shopImg;

	private Integer priority;

	private Date createTime;

	private Date lastEditTime;

	private Integer enableStatus;

	private String advice;

	public TbPersonInfo getOwner() {
		return owner;
	}

	public void setOwnerId(TbPersonInfo owner) {
		this.owner = owner;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public TbArea getArea() {
		return area;
	}

	public void setArea(TbArea area) {
		this.area = area;
	}

	public TbShopCategory getShopCategory() {
		return shopCategory;
	}

	public void setShopCategory(TbShopCategory shopCategory) {
		this.shopCategory = shopCategory;
	}

	public void setOwner(TbPersonInfo owner) {
		this.owner = owner;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName == null ? null : shopName.trim();
	}

	public String getShopDesc() {
		return shopDesc;
	}

	public void setShopDesc(String shopDesc) {
		this.shopDesc = shopDesc == null ? null : shopDesc.trim();
	}

	public String getShopAddr() {
		return shopAddr;
	}

	public void setShopAddr(String shopAddr) {
		this.shopAddr = shopAddr == null ? null : shopAddr.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getShopImg() {
		return shopImg;
	}

	public void setShopImg(String shopImg) {
		this.shopImg = shopImg == null ? null : shopImg.trim();
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

	public Integer getEnableStatus() {
		return enableStatus;
	}

	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice == null ? null : advice.trim();
	}

	@Override
	public String toString() {
		return "TbShop [shopId=" + shopId + ", owner=" + owner + ", area=" + area + ", shopCategory=" + shopCategory
				+ ", shopName=" + shopName + ", shopDesc=" + shopDesc + ", shopAddr=" + shopAddr + ", phone=" + phone
				+ ", shopImg=" + shopImg + ", priority=" + priority + ", createTime=" + createTime + ", lastEditTime="
				+ lastEditTime + ", enableStatus=" + enableStatus + ", advice=" + advice + "]";
	}
	
}