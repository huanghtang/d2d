package com.sandu.dao;

import com.sandu.entity2.TbShop;
import java.util.List;

public interface TbShopMapper {
    int deleteByPrimaryKey(Integer shopId);

    int insert(TbShop record);

    TbShop selectByPrimaryKey(Integer shopId);

    List<TbShop> selectAll();

    int updateByPrimaryKey(TbShop record);
}