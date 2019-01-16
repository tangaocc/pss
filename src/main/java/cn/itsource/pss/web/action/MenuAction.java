package cn.itsource.pss.web.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import cn.itsource.pss.domain.Department;
import cn.itsource.pss.domain.Menu;
import cn.itsource.pss.page.PageList;
import cn.itsource.pss.query.MenuQuery;
import cn.itsource.pss.service.IDepartmentService;
import cn.itsource.pss.service.IMenuService;

@Controller
@Scope("prototype")
public class MenuAction extends CRUDAction<Menu>{
	
	
	@Autowired
	private IMenuService menuService;
	
	private PageList<Menu>  pageList;
	private MenuQuery baseQuery = new  MenuQuery();
	private Menu menu;
	
	
	
	//对象栈还是应该放到map栈中
	//规范： 如果你当前Action操作的实体domian是本类的，那就放到对象栈中， 如果是操作的其他实体类，那统一都放到map栈中
	@Override
	protected void list() throws Exception {
		//查询员工列表
		pageList = menuService.findPageByQuery(baseQuery);
	}
	

	/**
	 * 新增或者修改的时候，跳转页面  input.jsp
	 */
	@Override
	public String input() throws Exception {
		return INPUT;
	}
	
	/**
	 * 新增和修改的方法
	 * 	@InputConfig
	 * 				methodName： 如果在执行目标方法的时候，报错了，它就会去执行methodName对应的方法
	 * 				resultName：如果在执行目标方法的时候，报错了，它就会直接返回逻辑视图为resultName对应的值，如果没写默认值就是input
	 * 				
	 * @return
	 */
	@InputConfig(methodName = INPUT)
	public String save(){
		if(menu.getId() == null){
			//如果是新增，我就给baseQuery设置最大页数
			baseQuery.setCurrentPage(Integer.MAX_VALUE);
		}
		menuService.save(menu);// 当menu保存之后，menu就变为是一个持久状态，持久状态有id
		return RELOAD;
	}
	
	public String delete() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		try {
			menuService.delete(id);
			response.getWriter().print("{\"success\":true,\"message\":\"恭喜你!删除成功\"}");
		} catch (Exception e) {
			response.getWriter().print("{\"success\":false,\"message\":\"亲，真的报错了，你手斗了，"+e.getMessage()+"\"}");
		}
		return NONE;
	}
	
	
	/**
	 * 验证用户名是否存在
	 * 		1.拿到当前用户名去数据库中进行查询
	 * 		2.如果查询的总数>0,那证明就已经存在了
	 * 		3.如果查询的总数=0,那证明该用户名是不存在的
	 * 
	 * 返回的结果值：  true表示用户名没重复，false表示用户名已重复了
	 * 
	 * 		判断用户名是否存在：
	 * 		判断新增还是修改，我们可以通过menu的主键id进行一个判断
	 * 				1. 新增
						  验证用户名是否存在
							1.拿到当前用户名去数据库中进行查询
							2.如果查询的总数>0,那证明就已经存在了
							3.如果查询的总数=0,那证明该用户名是不存在的
					
	 * 				2. 修改
	 * 					 通过id去把对应的menu对象查询出来
	 * 						1.username与menu对应的 username进行一个标胶
	 * 							如果相等：让它过，证明用户名是没有修改的
	 * 							如果不相等： 又要去数据库进行查询判断
	 * 					 
	 * 
	 * 	
	 * 
	 * @return
	 */
	private String name;
	public String checkName() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().println("{\"valid\":"+menuService.checkName(id,name)+",\"message\":\"用户名重复了\"}");
		return NONE;
	}
	
	

	/**
	 * 在新增或者修改之前，验证其方法
	 * 
	 * prepare： 在执行任何目标方法之前，会先执行prepare方法
	 * 
	 * validate： 在执行任何目标方法之前，它会先去执行validate验证方法，除了input,back,cancel,browse不会执行
	 * 
	 * prepareXxxx
	 * validateXxxx
	 */
	public void validateSave(){
		//如果用户名为空了，我要把该错误信息记录下来，但是不报错误
		if(StringUtils.isEmpty(menu.getName())){
			this.addFieldError("name", "名字不能为空！！");
		}
	}


	
	public void prepareInput() throws Exception {
		if(id != null){
			menu = menuService.queryOne(id);
		}
	}
	
	public void prepareSave(){
		if(id == null){
			menu = new Menu();
		}else{
			menu = menuService.queryOne(id);
		}
	
	}
	
	

	@Override
	public Menu getModel() {
		return menu;
	}
	

	public void setName(String name){
		this.name = name;
	}
	
	

	public Menu getMenu() {
		return menu;
	}

	public MenuQuery getBaseQuery() {
		return baseQuery;
	}

	public PageList<Menu> getPageList() {
		return pageList;
	}
	

}
