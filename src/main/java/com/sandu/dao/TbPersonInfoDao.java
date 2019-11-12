package com.sandu.dao;

import com.sandu.entity2.TbPersonInfo;
import java.util.List;

public interface TbPersonInfoDao {
    int deleteByPrimaryKey(Integer userId);

    int insert(TbPersonInfo record);

    TbPersonInfo selectByPrimaryKey(Integer userId);

    List<TbPersonInfo> selectAll();

    int updateByPrimaryKey(TbPersonInfo record);
}