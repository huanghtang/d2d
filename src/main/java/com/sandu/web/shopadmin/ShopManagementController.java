package com.sandu.web.shopadmin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sandu.dto.ShopExecution;
import com.sandu.entity2.TbPersonInfo;
import com.sandu.entity2.TbShop;
import com.sandu.enums.ShopStateEnum;
import com.sandu.service.ShopService;
import com.sandu.util.HttpServletRequestUtil;
import com.sandu.util.ImageUtil;
import com.sandu.util.PathUtil;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
	@Autowired
	private ShopService shopService;

	@RequestMapping(value = "/registershop", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> registerShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 1.接收并转化相应的参数，包括店铺信息以及图片信息
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		ObjectMapper mapper = new ObjectMapper();
		TbShop shop = null;
		try {
			shop = mapper.readValue(shopStr, TbShop.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		CommonsMultipartFile shopImg = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 如果request包含上传文件流
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "上传图片不能为空");
			return modelMap;
		}
		// 2.注册店铺
		if (shop != null && shopImg != null) {
			TbPersonInfo owner = new TbPersonInfo();
			owner.setUserId(1);
			shop.setOwner(owner);
			File shopImgFile = new File(
					PathUtil.getImgBasePath() + ImageUtil.getRandomNumb(5) + shopImg.getOriginalFilename());
			try {
				shopImg.transferTo(shopImgFile);
			} catch (IllegalStateException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "上传图片转化file出错");
				e.printStackTrace();
				return modelMap;
			} catch (IOException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "上传图片转化file出错");
				e.printStackTrace();
				return modelMap;
			}
			ShopExecution se = shopService.addShop(shop, shopImgFile);
			if (se.getState() == ShopStateEnum.CHECK.getState()) {
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", se.getStateInfo());
			}
			return modelMap;
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入店铺信息");
			return modelMap;
		}
		// 3.返回结果
	}
}
