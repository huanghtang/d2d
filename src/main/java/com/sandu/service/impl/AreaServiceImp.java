package com.sandu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sandu.dao.TbAreaDao;
import com.sandu.entity2.TbArea;
import com.sandu.service.AreaService;

@Service
public class AreaServiceImp implements AreaService{
	@Autowired
	private TbAreaDao areaDao;
	
	
	@Override
	public List<TbArea> getAreaList() {
		// TODO Auto-generated method stub
		return areaDao.selectAll();
	}

}
