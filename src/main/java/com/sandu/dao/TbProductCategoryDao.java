package com.sandu.dao;

import com.sandu.entity2.TbProductCategory;

import java.util.List;

import org.apache.ibatis.annotations.Param;


public interface TbProductCategoryDao {
    int deleteByPrimaryKey(Integer productCategoryId);

    int insert(TbProductCategory record);

    TbProductCategory selectByPrimaryKey(Integer productCategoryId);

    List<TbProductCategory> selectAll();

    int updateByPrimaryKey(TbProductCategory record);

    List<TbProductCategory> queryProductCategoryList(int shopId);
    /**
     * 批量新增商品类别
     * @param productCategoryList
     * @return
     */
    int batchInsertProductCategory(List<TbProductCategory>productCategoryList);

    int deleteProductCategory(@Param("productCategoryId")int productCategoryId,@Param("shopId")int shopId);

}