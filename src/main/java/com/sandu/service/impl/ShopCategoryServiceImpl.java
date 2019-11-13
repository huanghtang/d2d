package com.sandu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sandu.dao.TbShopCategoryDao;
import com.sandu.entity2.TbShopCategory;
import com.sandu.service.ShopCategoryService;

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {

	@Autowired
	TbShopCategoryDao shopCategoryDao;

	@Override
	public List<TbShopCategory> getShopCateogoryList(TbShopCategory shopCategoryCondition) {
		return shopCategoryDao.queryShopCategory(shopCategoryCondition);
	}

}
