package com.sandu.dao;

import com.sandu.entity2.TbProduct;
import java.util.List;

public interface TbProductMapper {
    int deleteByPrimaryKey(Integer productId);

    int insert(TbProduct record);

    TbProduct selectByPrimaryKey(Integer productId);

    List<TbProduct> selectAll();

    int updateByPrimaryKey(TbProduct record);
}