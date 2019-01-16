package cn.itsource.pss.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ProductTypeServiceTest {
	@Autowired
	private IProductTypeService productTypeService;
	
	@Test
	public void testQueryAll(){
		System.out.println(productTypeService.queryAll());
	}
}
