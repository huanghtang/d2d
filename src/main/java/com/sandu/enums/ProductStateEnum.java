package com.sandu.enums;

public enum ProductStateEnum {
	CHECK(0, "审核中"), OFFLINE(-1, "非法商品"), SUCCESS(1, "操作成功"), PASS(2, "通过认证"), INNER_ERROR(-1001,
			"内部系统错误"), EMPTY(-1002, "传入参数为空");

	private int state;
	private String stateInfo;

	private ProductStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}


	public String getStateInfo() {
		return stateInfo;
	}


	/**
	 * 一句传入的state返回相应的enum值
	 * 
	 * @param state
	 * @return
	 */
	public static ProductStateEnum stateOf(int state) {
		for (ProductStateEnum stateEnum : values()) {
			if (stateEnum.getState() == state) {
				return stateEnum;
			}
		}
		return null;
	}
}
