package com.sandu.service;

import java.util.List;

import com.sandu.dto.ProductCategoryExecution;
import com.sandu.entity2.TbArea;
import com.sandu.entity2.TbProductCategory;
import com.sandu.exceptions.ProductCategoryOperationException;

public interface ProductCategoryService {

	List<TbProductCategory> getProductCategoryList(int shopId);

	
	ProductCategoryExecution batchAddProductCategory(List<TbProductCategory>productCategoryList)throws ProductCategoryOperationException;
		
	ProductCategoryExecution deleteProductCategory(int productCategoryId,int shopId)throws ProductCategoryOperationException;

}
