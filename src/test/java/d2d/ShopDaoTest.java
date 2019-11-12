package d2d;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sandu.dao.TbShopDao;
import com.sandu.entity2.TbArea;
import com.sandu.entity2.TbPersonInfo;
import com.sandu.entity2.TbShop;
import com.sandu.entity2.TbShopCategory;

public class ShopDaoTest extends BaseTest {

	@Autowired
	private TbShopDao shopDao;

	@Ignore
	@Test
	public void testInsertShop() {
		TbShop shop = new TbShop();
		TbPersonInfo owner = new TbPersonInfo();
		TbArea area = new TbArea();
		TbShopCategory shopCategory = new TbShopCategory();
		owner.setUserId(1);
		area.setAreaId(1);
		shopCategory.setShopCategoryId(1);
		shop.setAdvice("ssss");
		shop.setArea(area);
		shop.setOwner(owner);
		shop.setShopCategory(shopCategory);
		shop.setShopDesc("test");
		shop.setShopAddr("aaaa");
		shop.setPhone("123232323");
		shop.setShopImg("test");
		shop.setShopName("aaaaaa");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(1);
		int effeNum = shopDao.insert(shop);
		System.out.println(effeNum);
	}

	@Test
	public void testUpdateShop() {
		TbShop shop = new TbShop();
		TbPersonInfo owner = new TbPersonInfo();
		TbArea area = new TbArea();
		TbShopCategory shopCategory = new TbShopCategory();
		owner.setUserId(1);
		area.setAreaId(20);
		shopCategory.setShopCategoryId(1);
		shop.setAdvice("bbbbbbb");
		//shop.setArea(area);
		shop.setOwner(owner);
		shop.setShopCategory(shopCategory);
		shop.setShopDesc("uuuu");
		shop.setShopAddr("bbbbb");
		shop.setPhone("123232323");
		shop.setShopImg("test");
		shop.setShopName("aadasdasfasgdsgaaaa");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(1);
		shop.setShopId(40);
		int effeNum = shopDao.updateByPrimaryKey(shop);
		System.out.println(effeNum);
	}

	@Ignore
	@Test
	public void testSelectAllShop() {
		List<TbShop> list = shopDao.selectAll();
		for (TbShop tbShop : list) {
			System.out.println(tbShop);
		}
	}
}
