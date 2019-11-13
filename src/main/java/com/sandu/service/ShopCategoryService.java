package com.sandu.service;

import java.util.List;

import com.sandu.entity2.TbShopCategory;

public interface ShopCategoryService {
	List<TbShopCategory> getShopCateogoryList(TbShopCategory shopCategoryCondition);
}
