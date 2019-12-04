package d2d;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sandu.dao.TbProductCategoryDao;
import com.sandu.entity2.TbProductCategory;

public class ProductCategoryDaoTest extends BaseTest{
	@Autowired
	private TbProductCategoryDao tdao;
	
	
	public void testProductCategoryList() {
		List<TbProductCategory> list = tdao.queryProductCategoryList(1);
		
		for (TbProductCategory tbProductCategory : list) {
			System.out.println(tbProductCategory);
		}
	}
	
	@Test
	public void testBatchInsertProductCategory() {
		TbProductCategory productCategory =new TbProductCategory();
		productCategory.setProductCategoryName("商品类别1");
		productCategory.setPriority(1);
		productCategory.setCreateTime(new Date());
		productCategory.setShopId(1);
		TbProductCategory productCategory2 =new TbProductCategory();
		productCategory2.setProductCategoryName("商品类别2");
		productCategory2.setPriority(2);
		productCategory2.setCreateTime(new Date());
		productCategory2.setShopId(2);
		ArrayList<TbProductCategory> list = new ArrayList<TbProductCategory>();
		list.add(productCategory);
		list.add(productCategory2);
		int ef = tdao.batchInsertProductCategory(list);
		System.out.println(ef);
	}
}
