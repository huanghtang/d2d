package com.sandu.web.shopadmin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
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
import com.sandu.entity2.TbArea;
import com.sandu.entity2.TbPersonInfo;
import com.sandu.entity2.TbShop;
import com.sandu.entity2.TbShopCategory;
import com.sandu.enums.ShopStateEnum;
import com.sandu.service.AreaService;
import com.sandu.service.ShopCategoryService;
import com.sandu.service.ShopService;
import com.sandu.util.CodeUtil;
import com.sandu.util.HttpServletRequestUtil;
import com.sandu.util.ImageUtil;
import com.sandu.util.PathUtil;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
	@Autowired
	private ShopService shopService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private ShopCategoryService shopCategoryService;

	@RequestMapping(value = "/getshopmanagementinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopManagementInfo(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int shopId = HttpServletRequestUtil.getInt(request, "shopId");
		if (shopId <= 0) {
			Object currentShopObj = request.getSession().getAttribute("currentShop");
			if (currentShopObj == null) {
				modelMap.put("redirect", true);
				modelMap.put("url", "/d2d/shopadmin/shoplist");
			} else {
				TbShop currentShop = (TbShop) currentShopObj;
				modelMap.put("redirect", false);
				modelMap.put("shopId", currentShop.getShopId());
			}
		} else {
			TbShop currentShop = new TbShop();
			currentShop.setShopId(shopId);
			request.getSession().setAttribute("currentShop", currentShop);
			modelMap.put("redirect", false);
		}
		return modelMap;
	}

	@RequestMapping(value = "/getshoplist", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopList(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		TbPersonInfo user = new TbPersonInfo();
		user.setUserId(1);
		user.setName("test");
		request.getSession().setAttribute("user", user);
		user = (TbPersonInfo) request.getSession().getAttribute("user");
		try {
			TbShop shopCondition = new TbShop();
			shopCondition.setOwner(user);
			ShopExecution se = shopService.getShopList(shopCondition, 0, 100);
			modelMap.put("shopList", se.getShopList());
			modelMap.put("user", user);
			modelMap.put("success", true);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

	@RequestMapping(value = "/getshopbyid", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopById(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int shopId = HttpServletRequestUtil.getInt(request, "shopId");
		if (shopId > -1) {
			try {
				TbShop shop = shopService.getByShopId(shopId);
				List<TbArea> areaList = areaService.getAreaList();
				modelMap.put("shop", shop);
				modelMap.put("areaList", areaList);
				modelMap.put("success", true);
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
			}

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty shopId");
		}
		return modelMap;
	}

	@RequestMapping(value = "/getshopinitinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopInitInfo() {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<TbShopCategory> shopCategoryList = new ArrayList<TbShopCategory>();
		List<TbArea> areaList = new ArrayList<TbArea>();

		try {
			shopCategoryList = shopCategoryService.getShopCateogoryList(new TbShopCategory());
			areaList = areaService.getAreaList();
			modelMap.put("shopCategoryList", shopCategoryList);
			modelMap.put("areaList", areaList);
			modelMap.put("success", true);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

	@RequestMapping(value = "/registershop", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> registerShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入了错误的验证码");
			return modelMap;
		}
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
			TbPersonInfo owner = (TbPersonInfo) request.getSession().getAttribute("user");
			shop.setOwner(owner);
			File shopImgFile = new File(
					PathUtil.getImgBasePath() + ImageUtil.getRandomNumb(5) + shopImg.getOriginalFilename());
			try {
				// 将CommonsMultipartFile转成file去处理
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
				// 该用户可以操作的店铺列表
				@SuppressWarnings("unchecked")
				List<TbShop> shopList = (List<TbShop>) request.getSession().getAttribute("shopList");
				if (shopList == null || shopList.size() == 0) {
					shopList = new ArrayList<TbShop>();
				}
				shopList.add(se.getShop());
				request.getSession().setAttribute("shopList", shopList);

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

	@RequestMapping(value = "/modifyshop", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> modifyShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入了错误的验证码");
			return modelMap;
		}
		// 1.接收并转化相应的参数，包括店铺信息以及图片信息
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		ObjectMapper mapper = new ObjectMapper();
		TbShop shop = null;
		ShopExecution se;
		try {
			shop = mapper.readValue(shopStr, TbShop.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		CommonsMultipartFile shopImg = null;
		// 如果request包含上传文件流
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
		System.out.println(ServletFileUpload.isMultipartContent(request));
		if (shopImg!=null) {
			
			// 2.修改店铺信息
			if (shop != null && shop.getShopId() != null ) {
				File shopImgFile = new File(
						PathUtil.getImgBasePath() + ImageUtil.getRandomNumb(5) + shopImg.getOriginalFilename());
				try {
					// 将CommonsMultipartFile转成file去处理
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

				se = shopService.modifyShop(shop, shopImgFile);
				if (se.getState() == ShopStateEnum.SUCCESS.getState()) {
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

		} else {
			// 没有上传图片

			se = shopService.modifyShop(shop, null);
			if (se.getState() == ShopStateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", se.getStateInfo());
			}
			return modelMap;
		}

	}

}
