package com.sandu.entity2;

import java.util.Date;
import java.util.List;

public class TbProduct {
    private Integer productId;

    private String productName;

    private Integer productCategoryId;


    private String productDesc;
    //简略图
    private String imgAddr;

    private String normalPrice;

    private String promotionPrice;

    private Date createTime;

    private Date lastEditTime;

    private Integer priority;
    //0.下架 1.在前端展示系统展示
    private Integer enableStatus;

    private TbProductCategory productCategory;
    
    private List<TbProductImg> productImgList;
    
    private TbShop shop;
    
    
    public TbProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(TbProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public List<TbProductImg> getProductImgList() {
		return productImgList;
	}

	public void setProductImgList(List<TbProductImg> productImgList) {
		this.productImgList = productImgList;
	}

	public TbShop getShop() {
		return shop;
	}

	public void setShop(TbShop shop) {
		this.shop = shop;
	}

	public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }


    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc == null ? null : productDesc.trim();
    }

    public String getImgAddr() {
        return imgAddr;
    }

    public void setImgAddr(String imgAddr) {
        this.imgAddr = imgAddr == null ? null : imgAddr.trim();
    }

    public String getNormalPrice() {
        return normalPrice;
    }

    public void setNormalPrice(String normalPrice) {
        this.normalPrice = normalPrice == null ? null : normalPrice.trim();
    }

    public String getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(String promotionPrice) {
        this.promotionPrice = promotionPrice == null ? null : promotionPrice.trim();
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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

	@Override
	public String toString() {
		return "TbProduct [productId=" + productId + ", productName=" + productName + ", productCategoryId="
				+ productCategoryId + ", productDesc=" + productDesc + ", imgAddr=" + imgAddr + ", normalPrice="
				+ normalPrice + ", promotionPrice=" + promotionPrice + ", createTime=" + createTime + ", lastEditTime="
				+ lastEditTime + ", priority=" + priority + ", enableStatus=" + enableStatus + ", productCategory="
				+ productCategory + ", productImgList=" + productImgList + ", shop=" + shop + "]";
	}
    
    
}