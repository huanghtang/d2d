package com.sandu.web.shopadmin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sandu.dto.ImageHolder;
import com.sandu.dto.ProductExecution;
import com.sandu.entity2.TbProduct;
import com.sandu.entity2.TbProductCategory;
import com.sandu.entity2.TbShop;
import com.sandu.enums.ProductStateEnum;
import com.sandu.service.ProductCategoryService;
import com.sandu.service.ProductService;
import com.sandu.util.CodeUtil;
import com.sandu.util.HttpServletRequestUtil;
import com.sandu.util.ImageUtil;
import com.sandu.util.PathUtil;

@Controller
@RequestMapping("/shopadmin")
public class PrdocutManagementController {
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductCategoryService productCategoryService;
	// 支持上传商品详情图的最大数量
	private static final int IMAGEMAXCOUNT = 6;

	@RequestMapping(value = "/addproduct", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> addProduct(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 验证码验证
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入了错误的验证码");
			return modelMap;
		}
		// 接受前端参数的变量的初始化，包括商品，缩略图，详情图列表实体类
		ObjectMapper mapper = new ObjectMapper();
		TbProduct product = null;
		String productStr = HttpServletRequestUtil.getString(request, "productStr");
		MultipartHttpServletRequest multipartRequest = null;
		ImageHolder thumbnail = null;
		List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		try {
			// 若请求中存在文件流，则取出相关的文件（包括缩略图好详情图）
			if (multipartResolver.isMultipart(request)) {
				thumbnail = handleImage(request, productImgList);

			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "上传图片不能为空");
				return modelMap;
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
			return modelMap;
		}
		try {
			// 尝试获取前端传过来的表单string流并将其转换成Product实体类
			product = mapper.readValue(productStr, TbProduct.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
			return modelMap;
		}
		// 若Product信息，缩略图以及详情图列表为非空,则开始进行商品添加操作
		if (product != null && thumbnail != null && productImgList.size() > 0) {
			try {
				// 从session中获取当前店铺的Id并赋值给product,减少对前端数据的依赖
				TbShop currentShop = (TbShop) request.getSession().getAttribute("currentShop");
				TbShop shop = new TbShop();
				shop.setShopId(currentShop.getShopId());
				product.setShop(shop);
				// 执行添加操作
				ProductExecution pe = productService.addProdut(product, thumbnail, productImgList);
				if (pe.getState() == ProductStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", pe.getStateInfo());
				}

			} catch (RuntimeException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入商品信息");
		}
		return modelMap;
	}

	private ImageHolder handleImage(HttpServletRequest request, List<ImageHolder> productImgList) throws IOException {
		MultipartHttpServletRequest multipartRequest;
		ImageHolder thumbnail;
		multipartRequest = (MultipartHttpServletRequest) request;
		// 取出缩略图并构建ImageHolder对象
		// 有问题
		CommonsMultipartFile thumbnailFile = (CommonsMultipartFile) multipartRequest.getFile("thumbnail");
		File productImgFile = new File(
				PathUtil.getImgBasePath() + ImageUtil.getRandomNumb(5) + thumbnailFile.getOriginalFilename());
		thumbnailFile.transferTo(productImgFile);
		thumbnail = new ImageHolder(productImgFile.getName(), productImgFile);
		// 取出详情图列表并构建List<ImageHolder>列表对象，最多支持六张图片上传
		for (int i = 0; i < IMAGEMAXCOUNT; i++) {
			CommonsMultipartFile productImgFileListMultipartFile = (CommonsMultipartFile) multipartRequest
					.getFile("productImg" + i);
			if (productImgFileListMultipartFile != null) {
				File productImgListFile = new File(PathUtil.getImgBasePath() + ImageUtil.getRandomNumb(5)
						+ productImgFileListMultipartFile.getOriginalFilename());
				productImgFileListMultipartFile.transferTo(productImgListFile);
				// 若取出的第i个详情图文件流不为空，则将其加入详情图列表
				ImageHolder productImg = new ImageHolder(productImgListFile.getName(), productImgListFile);
				productImgList.add(productImg);

			} else {
				// 若取出的第i个详情图片文件流为空，则终于循环
				break;
			}
		}
		return thumbnail;
	}

	@RequestMapping(value = "/getproductbyid", method = RequestMethod.GET)
	@ResponseBody
	private HashMap<String, Object> getProductById(@RequestParam int productId) {
		HashMap<String, Object> modelMap = new HashMap<String, Object>();
		System.out.println(111);
		// 非空判断
		if (productId > 0) {
			// 获取商品信息
			TbProduct product = productService.getProductId(productId);
			// 获取该店铺下的商品类别列表
			List<TbProductCategory> productCategoryList = productCategoryService
					.getProductCategoryList(product.getShop().getShopId());
			modelMap.put("product", product);
			modelMap.put("productCategoryList", productCategoryList);
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty prodcutId");
		}
		return modelMap;

	}

	@RequestMapping(value = "/modifyproduct", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	private Map<String, Object> modifyProduct(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 是商品编辑时候调用还是上下架操作是时候调用
		// 若为前者则进行验证码判断，后者则跳过验证码判断
		boolean statusChange = HttpServletRequestUtil.getBoolean(request, "statusChange");
		// 验证码判断
		if (!statusChange && !CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入流错误的验证码");
			return modelMap;
		}
		// 接收前端参数的变量的初始化，包括商品，缩略图，详情图列表实体类
		ObjectMapper mapper = new ObjectMapper();
		TbProduct product = null;
		ImageHolder thumbnail = null;
		List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());

		try {
			if (multipartResolver.isMultipart(request)) {
				thumbnail = handleImage(request, productImgList);
			}

		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
			return modelMap;
		}
		try {
			String productStr = HttpServletRequestUtil.getString(request, "productStr");
			// 尝试获取前端传过来的表单string流并将其转换成Product实体类
			product = mapper.readValue(productStr, TbProduct.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
			return modelMap;
		}
		// 非空判断
		if (product != null) {
			try {
				// 从session中获取当前店铺的Id并赋值给product，减少对前端数据的依赖
				TbShop currentShop = (TbShop) request.getSession().getAttribute("currentShop");
				TbShop shop = new TbShop();
				shop.setShopId(currentShop.getShopId());
				product.setShop(shop);
				// 开始进行商品信息变更操作
				ProductExecution pe = productService.modifyProdut(product, thumbnail, productImgList);
				if (pe.getState() == ProductStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);

				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", pe.getStateInfo());

				}
			} catch (RuntimeException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "product为空");
			return modelMap;
		}
		return modelMap;
	}

	@RequestMapping(value = "/getproductlistbyshop", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getProductListByShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 获取前台传过来的页码
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		// 获取前台传过来的每页要求返回的商品数上限
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		// 从当前session中获取店铺信息，主要是获取shopId
		TbShop currentShop = (TbShop) request.getSession().getAttribute("currentShop");
		// 空值判断
		if ((pageIndex > -1) && (pageSize > -1) && (currentShop != null) && (currentShop.getShopId() != null)) {
			// 获取传入的需要检索的条件，包括是否需要从某个商品类别以及模糊查找商品名去筛选某个店铺吓到商品列表
			// 筛选的条件可以进行排列组合
			int productCategoryId = HttpServletRequestUtil.getInt(request, "productCategoryId");
			String productName = HttpServletRequestUtil.getString(request, "productName");
			TbProduct productCondition = compactProductCondition(currentShop.getShopId(), productCategoryId,
					productName);
			// 传入查询条件以及分页信息进行插叙你，返回相应商品列表以及总数
			ProductExecution pe = productService.getProductList(productCondition, pageIndex, pageSize);
			modelMap.put("productList", pe.getProductList());
			modelMap.put("count", pe.getCount());
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty pageSize or pageIndex or shopId");
		}
		return modelMap;
	}

	private TbProduct compactProductCondition(int shopId, int productCategoryId, String productName) {
		TbProduct product = new TbProduct();
		TbShop shop = new TbShop();
		shop.setShopId(shopId);
		product.setShop(shop);
		// 若有指定类别的要求则添加进去
		if (productCategoryId != -1) {
			TbProductCategory productCategory = new TbProductCategory();
			productCategory.setProductCategoryId(productCategoryId);
			product.setProductCategory(productCategory);
		}
		// 若有商品名模糊查询的要求则添加进去
		if (productName != null) {
			product.setProductName(productName);
		}
		return product;
	}
}
