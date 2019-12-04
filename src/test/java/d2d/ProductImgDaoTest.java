package d2d;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sandu.dao.TbProductCategoryDao;
import com.sandu.dao.TbProductImgDao;
import com.sandu.entity2.TbProductCategory;
import com.sandu.entity2.TbProductImg;

public class ProductImgDaoTest extends BaseTest {
	@Autowired
	private TbProductImgDao tdao;

	public void testBatchInsertProductImg() {
		// productId为1的商品里添加两个详情图片记录
		TbProductImg productImg1 = new TbProductImg();
		productImg1.setImgAddr("图片 1");
		productImg1.setImgDesc("测试图片1");
		productImg1.setPriority(1);
		productImg1.setCreateTime(new Date());
		productImg1.setProductId(1);
		TbProductImg productImg2 = new TbProductImg();
		productImg2.setImgAddr("图片 2");
		productImg2.setImgDesc("测试图片2");
		productImg2.setPriority(2);
		productImg2.setCreateTime(new Date());
		productImg2.setProductId(1);
		List<TbProductImg> list = new ArrayList<TbProductImg>();
		list.add(productImg1);
		list.add(productImg2);
		int eff = tdao.batchInsertProductImg(list);
		System.out.println(eff);
	}
	@Test
	public void testQueryImgList() {
		List<TbProductImg> list = tdao.queryProductImgList(1);
		for (TbProductImg tbProductImg : list) {
			System.out.println(tbProductImg);
		}
		
	}
}
