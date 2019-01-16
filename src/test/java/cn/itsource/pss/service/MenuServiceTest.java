package cn.itsource.pss.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itsource.pss.domain.Employee;
import cn.itsource.pss.domain.Menu;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MenuServiceTest {
	@Autowired
	private IMenuService menuService;
	
	@Test
	public void testQueryAll(){
		Employee employee = new Employee();
		employee.setId(2L);
		List<Menu> menus = menuService.findMenusByEmployee(employee);
		for (Menu menu : menus) {
			System.out.println("一级菜单："+menu);
			List<Menu> children = menu.getChildren();
			for (Menu menu2 : children) {
				System.out.println("       二级菜单："+menu2);
			}
		}
	}
}
