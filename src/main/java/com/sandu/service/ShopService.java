package com.sandu.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.sandu.dto.ShopExecution;
import com.sandu.entity2.TbArea;
import com.sandu.entity2.TbShop;
import com.sandu.exceptions.ShopOperationException;

public interface ShopService {
	/**
	 * 通过店铺Id获取店铺信息
	 * @param shopId
	 * @return
	 */
	TbShop getByShopId(Integer shopId);
	
	ShopExecution modifyShop(TbShop shop,File shopImgFile) throws ShopOperationException;
	/**
	 * 根据shopCondition分页返回相应店铺列表
	 * @param shopCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	ShopExecution getShopList(TbShop shopCondition,int pageIndex,int pageSize);
	
	ShopExecution addShop(TbShop shop, File shopImg);
}
