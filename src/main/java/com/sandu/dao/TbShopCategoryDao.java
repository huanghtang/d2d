package com.sandu.dao;

import com.sandu.entity2.TbShopCategory;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TbShopCategoryDao {
    int deleteByPrimaryKey(Integer shopCategoryId);

    int insert(TbShopCategory record);

    TbShopCategory selectByPrimaryKey(Integer shopCategoryId);

    List<TbShopCategory> selectAll();

    int updateByPrimaryKey(TbShopCategory record);
    
    List<TbShopCategory> queryShopCategory(@Param("shopCategoryCondition")TbShopCategory shopCategoryCondition);
}