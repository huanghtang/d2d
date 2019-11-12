package com.sandu.dao;

import com.sandu.entity2.TbProductImg;
import java.util.List;

public interface TbProductImgDao {
    int deleteByPrimaryKey(Integer productImgId);

    int insert(TbProductImg record);

    TbProductImg selectByPrimaryKey(Integer productImgId);

    List<TbProductImg> selectAll();

    int updateByPrimaryKey(TbProductImg record);
}