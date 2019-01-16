package cn.itsource.pss.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itsource.pss.domain.Employee;
import cn.itsource.pss.domain.Menu;
import cn.itsource.pss.repository.MenuRepository;
import cn.itsource.pss.service.IMenuService;

@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu, Long> implements IMenuService {
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Override
	public boolean checkName(Long id,String name) {
		
		String jpql = "select count(o) from Menu o where o.name=?";
		if(id == null){//证明是新增
			List list = menuRepository.findByJpql(jpql, name);
			Long count = (Long)list.get(0);
			return count == 0;
			
		}else{//证明是修改
			//通过id查询到对应的menu对象
			Menu menu = super.queryOne(id);
			if(name.equals(menu.getName())){
				return true;// 应该直接放行    true:代表是没重复， false：代表重复了
			}else{
				List list = menuRepository.findByJpql(jpql, name);
				Long count = (Long)list.get(0);
				return count == 0;
			}
		}
	}

	@Override
	public List<Menu> findMenusByEmployee(Employee employee) {
		//查询当前用户拥有哪些菜单列表(包括一级菜单，二级菜单)  它是没有层次结构的
//		String jpql = "select distinct m from Employee e join e.roles r join r.menus m where e=?";
//		List<Menu> menus = menuRepository.findByJpql(jpql, employee);
//		return menus;
		
		//先把一级菜单查询出来（虽然有层次结构，但是性能太低了）
//		String jpql = "select distinct m from Employee e join e.roles r join r.menus m where e=? and m.parent is null";
//		List<Menu> menus = menuRepository.findByJpql(jpql, employee);
//		for (Menu menu : menus) {
//			//查询二级菜单
//			jpql = "select distinct m from Employee e join e.roles r join r.menus m where e=? and m.parent=?";
//			List<Menu> children = menuRepository.findByJpql(jpql, employee,menu);
//			menu.setChildren(children);
//		}
//		return menus;
		
		String jpql = "select distinct m from Employee e join e.roles r join r.menus m where e=?";
		List<Menu> menus = menuRepository.findByJpql(jpql, employee);
		//先定义一个集合，它是专门来装一级菜单的
		List<Menu> parentMenus = new ArrayList<Menu>(); 
		for (Menu menu : menus) {
			if(menu.getParent()==null){
				parentMenus.add(menu);
			}
		}
		for (Menu menu : parentMenus) {//循环的 是一个一级菜单
			for (Menu m : menus) {
				//如果一级菜单的id与二级菜单的父id相等的话，我们就把二级菜单放到一级菜单内部
				if(m.getParent()!=null && menu.getId() == m.getParent().getId()){
					menu.getChildren().add(m);
				}
			}
		}
	
		
		return parentMenus;
		
	}
}
