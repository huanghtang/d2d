package com.sandu.dao;

import com.sandu.entity2.TbWechatAuth;
import java.util.List;

public interface TbWechatAuthMapper {
    int deleteByPrimaryKey(Integer wechatAuthId);

    int insert(TbWechatAuth record);

    TbWechatAuth selectByPrimaryKey(Integer wechatAuthId);

    List<TbWechatAuth> selectAll();

    int updateByPrimaryKey(TbWechatAuth record);
}