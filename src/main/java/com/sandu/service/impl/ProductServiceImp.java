package com.sandu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sandu.dao.TbAreaDao;
import com.sandu.dao.TbProductDao;
import com.sandu.dao.TbProductImgDao;
import com.sandu.dto.ImageHolder;
import com.sandu.dto.ProductExecution;
import com.sandu.entity2.TbArea;
import com.sandu.entity2.TbProduct;
import com.sandu.entity2.TbProductImg;
import com.sandu.enums.ProductStateEnum;
import com.sandu.exceptions.ProductOperationException;
import com.sandu.service.AreaService;
import com.sandu.service.ProductService;
import com.sandu.util.ImageUtil;
import com.sandu.util.PageCalculator;
import com.sandu.util.PathUtil;

@Service
public class ProductServiceImp implements ProductService {

	@Autowired
	private TbProductDao productDao;
	@Autowired
	private TbProductImgDao productImgDao;

	@Override
	@Transactional
	// 1.处理缩略图，获取缩略图相对路径并赋值给product
	// 2.往tb_product写入商品信息，获取productId
	// 3.结合productId批量处理商品详情图
	// 4.讲商品详情图列表批量插入tb_product_img中
	public ProductExecution addProdut(TbProduct product, ImageHolder image, List<ImageHolder> imageList)
			throws ProductOperationException {
		// 空值判断
		if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
			// 给商品设置上默认属性
			product.setCreateTime(new Date());
			product.setLastEditTime(new Date());
			// 默认为上架的状态
			product.setEnableStatus(1);
			// 若商品缩略图不为空则添加
			if (image != null) {
				addThumbnail(product, image);
			}
			try {
				// 创建商品信息
				int effectNum = productDao.insertProduct(product);
				if (effectNum <= 0) {
					throw new ProductOperationException("创建商品失败");
				}
			} catch (Exception e) {
				throw new ProductOperationException("创建商品失败:" + e.toString());
			}
			// 若商品详情图不为空则添加
			if (imageList != null && imageList.size() > 0) {
				addProductImgList(product, imageList);
			}
			return new ProductExecution(ProductStateEnum.SUCCESS, product);
		} else {
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
	}

	/**
	 * 添加缩略图
	 * 
	 * @param product
	 * @param image
	 */
	private void addThumbnail(TbProduct product, ImageHolder image) {
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		String thumbnailAddr = ImageUtil.generrateThumbnail(image.getImage(), dest);
		product.setImgAddr(thumbnailAddr);
		image.getImage().delete();
	}

	private void addProductImgList(TbProduct product, List<ImageHolder> imageList) {
		// 获取图片存储路径，这里直接存放在相应店铺的文件夹底下
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		List<TbProductImg> productImgList = new ArrayList<TbProductImg>();
		// 遍历图片一次去处理，并添加进productImg实体类里
		for (ImageHolder productImage : imageList) {
			String imgAddr = ImageUtil.generrateNormalImg(productImage.getImage(), dest);
			TbProductImg productImg = new TbProductImg();
			productImg.setImgAddr(imgAddr);
			productImg.setProductId(product.getProductId());
			productImg.setCreateTime(new Date());
			productImgList.add(productImg);
		}
		if (productImgList.size() > 0) {
			try {
				int effectedNum = productImgDao.batchInsertProductImg(productImgList);
				if (effectedNum <= 0) {
					throw new ProductOperationException("创建商品详情图片失败");
				}
				for (ImageHolder productImage : imageList) {
					productImage.getImage().delete();
				}
			} catch (Exception e) {
				throw new ProductOperationException("创建商品详情图片失败:" + e.getMessage());
			}
		}
	}

	@Override
	public TbProduct getProductId(Integer productId) {
		// TODO Auto-generated method stub
		return productDao.queryProductById(productId);
	}

	/**
	 * 删除某个商品下的所有详情图
	 */
	public void delecteProductImgList(int productId) {
		// 根据productId获取原来的图片
		List<TbProductImg> productImgList = productImgDao.queryProductImgList(productId);
		for (TbProductImg tbProductImg : productImgList) {
			ImageUtil.deleteFileOrPath(tbProductImg.getImgAddr());
		}
		// 删除数据库原有图片的信息
		productImgDao.deleteProductImgByProductId(productId);
	}

	// 1.若缩略图参数有值，则处理缩略图
	// 若原先存在缩略图则先删除再添加新图，之后获取缩略图相对路径并赋予给product

	@Override
	public ProductExecution modifyProdut(TbProduct product, ImageHolder image, List<ImageHolder> imageList)
			throws ProductOperationException {
		// 空值判断
		if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
			// 给商品设置上默认属性
			product.setLastEditTime(new Date());
			// 若商品缩略图不为空且原有缩略图不为空则删除原有缩略图并添加
			if (image != null) {
				// 先获取一遍原有信息,因为原来的信息里有原图片地址
				TbProduct tempProduct = productDao.queryProductById(product.getProductId());
				if (tempProduct.getImgAddr() != null) {
					ImageUtil.deleteFileOrPath(tempProduct.getImgAddr());
				}
				addThumbnail(product, image);
			}
			// 如果有新存入的商品详情图，则将原先的删除，并添加新的图片
			if (imageList != null && imageList.size() > 0) {
				delecteProductImgList(product.getProductId());
				addProductImgList(product, imageList);
			}
			try {
				// 更新商品信息
				int effectNum = productDao.updateProduct(product);
				if (effectNum <= 0) {
					throw new ProductOperationException("更新商品信息失败");
				}
				return new ProductExecution(ProductStateEnum.SUCCESS, product);
			} catch (Exception e) {
				throw new ProductOperationException("更新商品信息失败" + e.toString());
			}
		} else {
			return new ProductExecution(ProductStateEnum.EMPTY);
		}

	}

	@Override
	public ProductExecution getProductList(TbProduct productCondition, int pageIndex, int pageSize) {
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex,pageSize);
		List<TbProduct>productList=productDao.queryProductList(productCondition, rowIndex, pageSize);
		//基于同样的查询条件返回该查询条件下的商品总数
		int count = productDao.queryProductCount(productCondition);
		ProductExecution pe =new ProductExecution();
		pe.setProductList(productList);
		pe.setCount(count);
		return pe;
	}

}
