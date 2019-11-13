package com.sandu.dao;

import com.sandu.entity2.TbHeadLine;
import java.util.List;

public interface TbHeadLineMapper {
    int deleteByPrimaryKey(Integer lineId);

    int insert(TbHeadLine record);

    TbHeadLine selectByPrimaryKey(Integer lineId);

    List<TbHeadLine> selectAll();

    int updateByPrimaryKey(TbHeadLine record);
}