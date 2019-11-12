package com.sandu.dto;

import java.util.List;

import com.sandu.entity2.TbShop;
import com.sandu.enums.ShopStateEnum;

public class ShopExecution {
	// 结果状态
	private int state;

	// 状态标识
	private String stateInfo;

	// 店铺数量
	private int count;

	// 操作的shop(增删改店铺的时候用)
	private TbShop shop;

	// shop列表(查询店铺列表的时候使用)
	private List<TbShop> shopList;

	public ShopExecution() {

	}

	public TbShop getShop() {
		return shop;
	}

	public void setShop(TbShop shop) {
		this.shop = shop;
	}

	public List<TbShop> getShopList() {
		return shopList;
	}

	public void setShopList(List<TbShop> shopList) {
		this.shopList = shopList;
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
	public ShopExecution(ShopStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	// 店铺操作成功的时候的构造器
	public ShopExecution(ShopStateEnum stateEnum, TbShop shop) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.shop = shop;
	}

	// 店铺操作成功的时候的构造器
	public ShopExecution(ShopStateEnum stateEnum, List<TbShop> shopList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.shopList = shopList;
	}
}
