package com.sandu.service;

import java.io.File;
import java.util.List;

import com.sandu.dto.ShopExecution;
import com.sandu.entity2.TbArea;
import com.sandu.entity2.TbShop;

public interface ShopService {
	ShopExecution addShop(TbShop shop, File shopImg);
}
