package com.sandu.dao;

import com.sandu.entity2.TbProductCategory;
import java.util.List;

public interface TbProductCategoryMapper {
    int deleteByPrimaryKey(Integer productCategoryId);

    int insert(TbProductCategory record);

    TbProductCategory selectByPrimaryKey(Integer productCategoryId);

    List<TbProductCategory> selectAll();

    int updateByPrimaryKey(TbProductCategory record);
}