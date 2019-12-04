package com.sandu.entity2;

import java.util.Date;

public class TbProductCategory {
    private Integer productCategoryId;

    private String productCategoryName;

    private Integer priority;

    private Date createTime;

    private Integer shopId;

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName == null ? null : productCategoryName.trim();
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

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

	@Override
	public String toString() {
		return "TbProductCategory [productCategoryId=" + productCategoryId + ", productCategoryName="
				+ productCategoryName + ", priority=" + priority + ", createTime=" + createTime + ", shopId=" + shopId
				+ "]";
	}
    
    
}