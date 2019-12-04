package com.sandu.dto;

import java.util.List;

import com.sandu.entity2.TbProduct;
import com.sandu.enums.ProductCategoryStateEnum;
import com.sandu.enums.ProductStateEnum;

public class ProductExecution {
	// 结果状态
	private int state;

	// 状态标识
	private String stateInfo;

	// 店铺数量
	private int count;

	private TbProduct product;
	
	private List<TbProduct> productList;

	public ProductExecution() {

	}


	public TbProduct getProduct() {
		return product;
	}


	public void setProduct(TbProduct product) {
		this.product = product;
	}


	public List<TbProduct> getProductList() {
		return productList;
	}


	public void setProductList(List<TbProduct> productList) {
		this.productList = productList;
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

	
	// 产品操作失败的时候使用的构造器
	public ProductExecution(ProductStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	// 产品操作成功的时候的构造器
		public ProductExecution(ProductStateEnum stateEnum, TbProduct product) {
			this.state = stateEnum.getState();
			this.stateInfo = stateEnum.getStateInfo();
			this.product = product;
		}
	// 店铺操作成功的时候的构造器
	public ProductExecution(ProductStateEnum stateEnum, List<TbProduct> productList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.productList = productList;
	}
}
