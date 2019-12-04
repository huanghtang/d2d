package com.sandu.dao;

import com.sandu.entity2.TbProduct;
import com.sandu.entity2.TbShop;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TbProductDao {
	int deleteByPrimaryKey(Integer productId);

	TbProduct selectByPrimaryKey(Integer productId);

	List<TbProduct> selectAll();

	int updateByPrimaryKey(TbProduct record);

	/**
	 * 插入商品
	 * 
	 * @param product
	 * @return
	 */
	int insertProduct(TbProduct product);

	int updateProduct(TbProduct product);

	TbProduct queryProductById(int productId);

	/**
	 * 查询商品列表并分页，可输入的条件有:商品名，商品状态，店铺Id商品类别
	 * 
	 * @return
	 */
	List<TbProduct> queryProductList(@Param("productCondition") TbProduct productCondition,
			@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

	int queryProductCount(@Param("productCondition") TbProduct productCondition);
}