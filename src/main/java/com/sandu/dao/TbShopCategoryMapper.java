package com.sandu.dao;

import com.sandu.entity2.TbShopCategory;
import java.util.List;

public interface TbShopCategoryMapper {
    int deleteByPrimaryKey(Integer shopCategoryId);

    int insert(TbShopCategory record);

    TbShopCategory selectByPrimaryKey(Integer shopCategoryId);

    List<TbShopCategory> selectAll();

    int updateByPrimaryKey(TbShopCategory record);
}