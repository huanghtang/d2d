package com.sandu.dao;

import com.sandu.entity2.TbLocalAuth;
import java.util.List;

public interface TbLocalAuthMapper {
    int deleteByPrimaryKey(Integer localAuthId);

    int insert(TbLocalAuth record);

    TbLocalAuth selectByPrimaryKey(Integer localAuthId);

    List<TbLocalAuth> selectAll();

    int updateByPrimaryKey(TbLocalAuth record);
}