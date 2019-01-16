package cn.itsource.pss.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itsource.pss.domain.Employee;
import cn.itsource.pss.service.IMenuService;

/**
 * 主界面相关的信息
 * @author Administrator
 *
 */
@Controller
public class MainAction extends BaseAction{
	@Autowired
	private IMenuService menuService;
	@Override
	public String execute() throws Exception {
		Object object = ActionContext.getContext().getSession().get(USER_IN_SESSION);
		putContext("menus",menuService.findMenusByEmployee((Employee)object));
		return SUCCESS;
	}
	
	//主界面--右边的页面
	public String right(){
		return  "right";
	}
}
