package service;

import java.io.File;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.sandu.dto.ShopExecution;
import com.sandu.entity2.TbArea;
import com.sandu.entity2.TbPersonInfo;
import com.sandu.entity2.TbShop;
import com.sandu.entity2.TbShopCategory;
import com.sandu.enums.ShopStateEnum;
import com.sandu.service.ShopService;

import d2d.BaseTest;

public class ShopServiceTest extends BaseTest {
	@Autowired
	private ShopService shopService;
	
	@Test
	public void testGetShopList() {
		TbShop shopCondition=new TbShop();
		TbShopCategory sc = new TbShopCategory();
		sc.setShopCategoryId(2);
		shopCondition.setShopCategory(sc);
		ShopExecution se = shopService.getShopList(shopCondition,2,2);
		System.out.println(se.getShopList().size());
		System.out.println(se.getCount());
	}
	
	@Ignore
	@Test
	public void testAddShop() {
		TbShop shop = new TbShop();
		TbPersonInfo owner = new TbPersonInfo();
		TbArea area=new TbArea();
		TbShopCategory shopCategory=new TbShopCategory();
		owner.setUserId(1);
		area.setAreaId(2);
		shopCategory.setShopCategoryId(1);
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopDesc("test1");
		shop.setShopName("测试的店铺1");
		shop.setShopAddr("test1");
		shop.setPhone("test1");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("审核中");
		File shopImg=new File("C:\\Users\\sandu\\Desktop/星空.jpg");
		shopService.addShop(shop, shopImg);
	}
	
	@Ignore
	@Test
	public void testModifyShop() {
		TbShop shop = new TbShop();
		shop.setShopId(1);
		shop.setShopName("修改后的店铺名称22");
		File shopImg= new File("C:\\Users\\sandu\\Desktop\\upload\\upload\\images\\item\\shop\\6\\2017082223104932200.jpg");
		ShopExecution e=shopService.modifyShop(shop, shopImg);
		System.out.println(e);
	}
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getContextClassLoader().getResource("").getPath());
	}
}
