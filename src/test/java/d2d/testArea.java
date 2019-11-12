package d2d;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sandu.dao.TbAreaDao;
import com.sandu.entity2.TbArea;


public class testArea extends BaseTest {
	@Autowired
	TbAreaDao tbA;
	
	@Test
	public void testQueryArea(){
		List<TbArea> areaList= tbA.selectAll();
		for (TbArea area : areaList) {
			System.out.println(area);
		}
	}
	
}
