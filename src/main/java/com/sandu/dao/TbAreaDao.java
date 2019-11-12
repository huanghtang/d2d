package com.sandu.dao;

import com.sandu.entity2.TbArea;
import java.util.List;

public interface TbAreaDao {
    int deleteByPrimaryKey(Integer areaId);

    int insert(TbArea record);

    TbArea selectByPrimaryKey(Integer areaId);

   public List<TbArea> selectAll();

    int updateByPrimaryKey(TbArea record);
}