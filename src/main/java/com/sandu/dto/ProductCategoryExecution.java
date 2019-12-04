package com.sandu.dto;

import java.util.List;

import com.sandu.entity2.TbProductCategory;
import com.sandu.enums.ProductCategoryStateEnum;

public class ProductCategoryExecution {
	// 结果状态
	private int state;

	// 状态标识
	private String stateInfo;

	// 店铺数量
	private int count;

	private List<TbProductCategory> productCategoryList;

	public ProductCategoryExecution() {

	}

	public List<TbProductCategory> getProductCategoryList() {
		return productCategoryList;
	}

	public void setProductCategoryList(List<TbProductCategory> productCategoryList) {
		this.productCategoryList = productCategoryList;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	
	// 店铺操作失败的时候使用的构造器
	public ProductCategoryExecution(ProductCategoryStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	// 店铺操作成功的时候的构造器
	public ProductCategoryExecution(ProductCategoryStateEnum stateEnum, List<TbProductCategory> productCategoryList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.productCategoryList = productCategoryList;
	}
}
