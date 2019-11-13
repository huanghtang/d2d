package d2d;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sandu.dao.TbShopCategoryDao;
import com.sandu.entity2.TbShopCategory;

public class ShopCategoryDaoTest extends BaseTest{

	@Autowired
	private TbShopCategoryDao sDao;

	@Test
	public void testQueryShopCategory() {
		TbShopCategory testParent= new TbShopCategory();
		List<TbShopCategory> list = sDao.queryShopCategory(testParent);
		
		for (TbShopCategory tbShopCategory : list) {
			System.out.println(tbShopCategory);
		}
		
	}
}
