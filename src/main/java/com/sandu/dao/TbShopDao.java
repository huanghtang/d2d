package com.sandu.dao;

import com.sandu.entity2.TbShop;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TbShopDao {
	int deleteByPrimaryKey(Integer shopId);

	/**
	 * 新增店铺
	 * 
	 * @param record
	 * @return
	 */
	int insert(TbShop record);

	/**
	 * 分页查询店铺，可输入的条件有:店铺名(模糊),店铺状态，店铺类别
	 * 
	 * @param rowIndex第几行开始
	 * @param pageSize返回的条数
	 */
	List<TbShop> queryShopList(@Param("shopCondition") TbShop shop, @Param("rowIndex") int rowIndex,
			@Param("pageSize") int pageSize);
	/**
	 * 返回queryShopList总数
	 * @param shop
	 * @return
	 */
	int queryShopCount(@Param("shopCondition")TbShop shop);
	
	TbShop queryByShopId(Integer shopId);

	TbShop selectByPrimaryKey(Integer shopId);

	List<TbShop> selectAll();

	int updateByPrimaryKey(TbShop record);
}