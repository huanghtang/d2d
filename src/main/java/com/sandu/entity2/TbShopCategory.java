package com.sandu.entity2;

import java.util.Date;

public class TbShopCategory {
    private Integer shopCategoryId;

    private String shopCategoryName;

    private String shopCategoryDesc;

    private String shopCategoryImg;

    private Integer priority;

    private Date createTime;

    private Date lastEditTime;

    private TbShopCategory parent;

    
    public TbShopCategory getParent() {
		return parent;
	}

	public void setParent(TbShopCategory parent) {
		this.parent = parent;
	}

	public Integer getShopCategoryId() {
        return shopCategoryId;
    }

    public void setShopCategoryId(Integer shopCategoryId) {
        this.shopCategoryId = shopCategoryId;
    }

    public String getShopCategoryName() {
        return shopCategoryName;
    }

    public void setShopCategoryName(String shopCategoryName) {
        this.shopCategoryName = shopCategoryName == null ? null : shopCategoryName.trim();
    }

    public String getShopCategoryDesc() {
        return shopCategoryDesc;
    }

    public void setShopCategoryDesc(String shopCategoryDesc) {
        this.shopCategoryDesc = shopCategoryDesc == null ? null : shopCategoryDesc.trim();
    }

    public String getShopCategoryImg() {
        return shopCategoryImg;
    }

    public void setShopCategoryImg(String shopCategoryImg) {
        this.shopCategoryImg = shopCategoryImg == null ? null : shopCategoryImg.trim();
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

	@Override
	public String toString() {
		return "TbShopCategory [shopCategoryId=" + shopCategoryId + ", shopCategoryName=" + shopCategoryName
				+ ", shopCategoryDesc=" + shopCategoryDesc + ", shopCategoryImg=" + shopCategoryImg + ", priority="
				+ priority + ", createTime=" + createTime + ", lastEditTime=" + lastEditTime + ", parent=" + parent
				+ "]";
	}

    
}