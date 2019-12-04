package d2d;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sandu.dao.TbProductCategoryDao;
import com.sandu.dao.TbProductDao;
import com.sandu.dao.TbProductImgDao;
import com.sandu.entity2.TbProduct;
import com.sandu.entity2.TbProductCategory;
import com.sandu.entity2.TbProductImg;
import com.sandu.entity2.TbShop;

public class ProductDaoTest extends BaseTest {
	@Autowired
	private TbProductDao tdao;

	public void testInsertProduct() {
		TbProductCategory tc = new TbProductCategory();
		tc.setProductCategoryId(1);
		TbShop shop = new TbShop();
		shop.setShopId(2);
		TbProduct tb = new TbProduct();
		tb.setProductName("新产品");
		tb.setProductDesc("dddddesc");
		tb.setImgAddr("adddr");
		tb.setNormalPrice("14");
		tb.setCreateTime(new Date());
		tb.setLastEditTime(new Date());
		tb.setPriority(1);
		tb.setPromotionPrice("222");
		tb.setEnableStatus(2);
		tb.setProductCategory(tc);
		tb.setShop(shop);
		int a = tdao.insertProduct(tb);
		System.out.println(a);

	}

	public void testProductQuery() {
		List<TbProduct> list = tdao.selectAll();
		for (TbProduct tbProduct : list) {
			System.out.println(tbProduct);
		}
	}

	@Test
	public void testQueryProductList() {
		TbProduct productCondition=new TbProduct();
		List<TbProduct> productList=tdao.queryProductList(productCondition, 0, 113);
		for (TbProduct tbProduct : productList) {
			System.out.println(tbProduct);
		}
		int count = tdao.queryProductCount(productCondition);
	System.out.println(count);
	}
}
