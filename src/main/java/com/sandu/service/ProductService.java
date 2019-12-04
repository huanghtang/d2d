package com.sandu.service;

import java.io.File;
import java.util.List;

import com.sandu.dto.ImageHolder;
import com.sandu.dto.ProductExecution;
import com.sandu.entity2.TbArea;
import com.sandu.entity2.TbProduct;
import com.sandu.exceptions.ProductOperationException;

public interface ProductService {
	/**
	 * 添加商品信息以及图片处理
	 * 
	 * @return
	 * @throws ProductOperationException
	 */
	ProductExecution addProdut(TbProduct product,ImageHolder image,List<ImageHolder> imageList) throws ProductOperationException;


	TbProduct getProductId(Integer productId);
	
	ProductExecution modifyProdut(TbProduct product,ImageHolder image,List<ImageHolder> imageList) throws ProductOperationException;

	ProductExecution getProductList(TbProduct productCondition,int pageindex,int pageSize);
}
 