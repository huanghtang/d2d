package service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sandu.entity2.TbArea;
import com.sandu.service.AreaService;

import d2d.BaseTest;

public class AreaServiceTest extends BaseTest {
	@Autowired
	private AreaService areaService;
	
	@Test
	public void testArea(){
		List<TbArea> list=areaService.getAreaList();
		for (TbArea tbArea : list) {
			System.out.println(tbArea);
		}
	}
}
