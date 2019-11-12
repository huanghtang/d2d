package com.sandu.dao;

import com.sandu.entity2.TbShop;
import java.util.List;

public interface TbShopDao {
    int deleteByPrimaryKey(Integer shopId);
    /**
     * 新增店铺
     * @param record
     * @return
     */
    int insert(TbShop record);

    TbShop selectByPrimaryKey(Integer shopId);

    List<TbShop> selectAll();

    int updateByPrimaryKey(TbShop record);
}