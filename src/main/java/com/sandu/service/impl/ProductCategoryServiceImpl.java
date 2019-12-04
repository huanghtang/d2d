package com.sandu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sandu.dao.TbProductCategoryDao;
import com.sandu.dto.ProductCategoryExecution;
import com.sandu.entity2.TbProductCategory;
import com.sandu.enums.ProductCategoryStateEnum;
import com.sandu.exceptions.ProductCategoryOperationException;
import com.sandu.service.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
	@Autowired
	private TbProductCategoryDao productcategoryDao;

	@Override
	public List<TbProductCategory> getProductCategoryList(int shopId) {
		return productcategoryDao.queryProductCategoryList(shopId);
	}

	@Override
	@Transactional
	public ProductCategoryExecution batchAddProductCategory(List<TbProductCategory> productCategoryList)
			throws ProductCategoryOperationException {
		if (productCategoryList != null && productCategoryList.size() > 0) {
			try {
				int effectNum = productcategoryDao.batchInsertProductCategory(productCategoryList);
				if (effectNum <= 0) {
					throw new ProductCategoryOperationException("店铺类别创建失败");
				} else {
					return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
				}
			} catch (Exception e) {
				throw new ProductCategoryOperationException("batchAddProductCategory error:" + e.getMessage());
			}
		} else {
			return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
		}

	}

	@Override
	@Transactional
	public ProductCategoryExecution deleteProductCategory(int productCategoryId, int shopId)
			throws ProductCategoryOperationException {
		// TODO 将此商品类别下的商品的类别Id值为空
		try {
			int effectNum = productcategoryDao.deleteProductCategory(productCategoryId, shopId);
			if (effectNum <= 0) {
				throw new ProductCategoryOperationException("商品类别删除失败");
			} else {
				return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);

			}
		} catch (Exception e) {
			throw new ProductCategoryOperationException("deleteProductCategory error:" + e.getMessage());
		}

	}
}
