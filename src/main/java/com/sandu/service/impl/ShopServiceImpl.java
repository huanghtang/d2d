package com.sandu.service.impl;

import java.io.File;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sandu.dao.TbShopDao;
import com.sandu.dto.ShopExecution;
import com.sandu.entity2.TbShop;
import com.sandu.enums.ShopStateEnum;
import com.sandu.exceptions.ShopOperationException;
import com.sandu.service.ShopService;
import com.sandu.util.ImageUtil;
import com.sandu.util.PathUtil;

@Service
public class ShopServiceImpl implements ShopService {
	@Autowired
	private TbShopDao shopDao;

	@Transactional
	@Override
	public ShopExecution addShop(TbShop shop, File shopImg) {

		if (shop == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOPID);
		}
		try {
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			int effectedNum = shopDao.insert(shop);
			if (effectedNum <= 0) {
				throw new ShopOperationException("店铺创建失败");
			} else {
				if (shopImg != null) {
					// 存储图片
					try {
						addShopImg(shop, shopImg);
						shop.getShopImg();
					} catch (Exception e) {
						throw new ShopOperationException("addShopImg error:" + e.getMessage());
					}
					// 更新店铺的图片地址
					effectedNum = shopDao.updateByPrimaryKey(shop);
					if (effectedNum <= 0) {
						throw new ShopOperationException("更新图片地址失败");
					}

				}
			}

		} catch (Exception e) {
			throw new ShopOperationException("addShop error:" + e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.CHECK, shop);
	}

	private void addShopImg(TbShop shop, File shopImg) {
		// 获取shop图片目录的相对值路径
		String dest = PathUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr = ImageUtil.generrateThumbnail(shopImg, dest);
		shop.setShopImg(shopImgAddr);
	}

}
