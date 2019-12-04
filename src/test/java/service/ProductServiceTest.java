package service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sandu.dto.ImageHolder;
import com.sandu.dto.ProductExecution;
import com.sandu.entity2.TbProduct;
import com.sandu.entity2.TbProductCategory;
import com.sandu.entity2.TbShop;
import com.sandu.enums.ProductStateEnum;
import com.sandu.service.ProductService;

import d2d.BaseTest;

public class ProductServiceTest extends BaseTest {
	@Autowired
	private ProductService productService;

	public void testProduct() {
		TbProduct product = new TbProduct();
		TbShop shop = new TbShop();
		shop.setShopId(1);
		TbProductCategory pc = new TbProductCategory();
		pc.setProductCategoryId(1);
		product.setShop(shop);
		product.setProductCategory(pc);
		product.setProductName("测试商品1");
		product.setProductDesc("测试商品1");
		product.setPriority(20);
		product.setCreateTime(new Date());
		product.setEnableStatus(ProductStateEnum.SUCCESS.getState());
		// 创建缩略图文件流
		File thumbnailFile = new File("C:\\Users\\sandu\\Desktop\\星空.jpg");
		ImageHolder is = new ImageHolder(thumbnailFile.getName(), thumbnailFile);
		// 创建两个商品详情图文件并将它们添加到详情图列表中
		File thumbnailFile2 = new File("C:\\Users\\sandu\\Desktop\\星空.jpg");
		File thumbnailFile3 = new File("C:\\Users\\sandu\\Desktop\\星空.jpg");
		List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
		productImgList.add(new ImageHolder(thumbnailFile2.getName(), thumbnailFile2));
		productImgList.add(new ImageHolder(thumbnailFile3.getName(), thumbnailFile3));
		// 添加商品并验证
		ProductExecution pe = productService.addProdut(product, is, productImgList);
		System.out.println(pe.getState());

	}

	@Test
	public void testModifyProduct() {
		TbProduct pd = new TbProduct();
		TbShop shop = new TbShop();
		TbProductCategory pc = new TbProductCategory();
		shop.setShopId(1);
		pc.setProductCategoryId(1);
		pd.setProductName("正式的商品");
		pd.setProductDesc("正式的商品");
		pd.setProductId(1);
		pd.setShop(shop);
		pd.setProductCategory(pc);
		File thumbnailFile = new File("C:\\Users\\sandu\\Desktop\\111.jpg");
		ImageHolder is = new ImageHolder(thumbnailFile.getName(), thumbnailFile);
		File thumbnailFile2 = new File("C:\\Users\\sandu\\Desktop\\111 - 副本.jpg");
		File thumbnailFile3 = new File("C:\\Users\\sandu\\Desktop\\111 - 副本 (2).jpg");
		List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
		productImgList.add(new ImageHolder(thumbnailFile2.getName(), thumbnailFile2));
		productImgList.add(new ImageHolder(thumbnailFile3.getName(), thumbnailFile3));
		ProductExecution pe = productService.modifyProdut(pd, is, productImgList);
		System.out.println(pe.getStateInfo());
	
	}
}
