package com.sandu.web.shopadmin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sandu.dto.ProductCategoryExecution;
import com.sandu.dto.Result;
import com.sandu.entity2.TbProductCategory;
import com.sandu.entity2.TbShop;
import com.sandu.enums.ProductCategoryStateEnum;
import com.sandu.service.ProductCategoryService;

@Controller
@RequestMapping(value = "/shopadmin", method = { RequestMethod.GET })
public class PrdocutCategoryManagementController {
	@Autowired
	private ProductCategoryService productCategoryService;

	@RequestMapping(value = "/getproductcategorylist", method = RequestMethod.GET)
	@ResponseBody
	private Result<List<TbProductCategory>> getProductCategoryList(HttpServletRequest request) {
		// 登录功能后删除
		/*
		 * TbShop shop = new TbShop(); shop.setShopId(1);
		 * request.getSession().setAttribute("currentShop", shop);
		 */

		TbShop currentShop = (TbShop) request.getSession().getAttribute("currentShop");
		List<TbProductCategory> list = null;
		if (currentShop != null && currentShop.getShopId() > 0) {
			System.out.println(1);
			list = productCategoryService.getProductCategoryList(currentShop.getShopId());
			return new Result<List<TbProductCategory>>(true, list);
		} else {
			ProductCategoryStateEnum ps = ProductCategoryStateEnum.INNER_ERROR;
			return new Result<List<TbProductCategory>>(false, ps.getState(), ps.getStateInfo());
		}

	}

	@RequestMapping(value = "/addproductcategorys", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> addProducCategorys(@RequestBody List<TbProductCategory> productCategoryList,
			HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		TbShop shop = (TbShop) request.getSession().getAttribute("currentShop");
		for (TbProductCategory pc : productCategoryList) {
			pc.setShopId(shop.getShopId());
		}
		if (productCategoryList != null && productCategoryList.size() > 0) {
			try {
				ProductCategoryExecution pe = productCategoryService.batchAddProductCategory(productCategoryList);
				if (pe.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", pe.getStateInfo());
				}
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请至少输入一个商品类别");
		}
		return modelMap;
	}

	@RequestMapping(value = "/removeproductcategory", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> removeProductCategory(Integer productCategoryId, HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (productCategoryId != null && productCategoryId > 0) {
			try {
				TbShop currentShop = (TbShop) request.getSession().getAttribute("currentShop");
				ProductCategoryExecution pe = productCategoryService.deleteProductCategory(productCategoryId,
						currentShop.getShopId());
				if (pe.getState() == pe.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", pe.getStateInfo());
				}
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请至少选择一个商品类别");
		}
		return modelMap;
	}
}
