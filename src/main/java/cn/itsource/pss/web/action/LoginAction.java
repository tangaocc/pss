package cn.itsource.pss.web.action;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

import cn.itsource.pss.domain.Employee;
import cn.itsource.pss.service.IEmployeeService;

@Controller
@Scope("prototype")
public class LoginAction extends BaseAction {

	private String username;
	private String password;

	@Autowired
	private IEmployeeService employeeService;

	/**
	 * 跳到登录界面
	 */
	@Override
	public String execute() throws Exception {
		return LOGIN;
	}

	/**
	 * 验证用户名密码是否正确
	 * 
	 * @return
	 */
	@InputConfig(resultName = LOGIN)
	public String check() {
		try {
			Employee employee = employeeService.checkUsernameAndPassword(username, password);
			putSession(USER_IN_SESSION, employee);
			return SUCCESS;
		} catch (RuntimeException e) {
			e.printStackTrace();
			this.addActionError(e.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError(e.getMessage());
		}
		return LOGIN;
	}
	
	/**
	 * 删除session中的用户，回到登录界面
	 * @return
	 */
	public String out(){
		ActionContext.getContext().getSession().clear();
		return LOGIN;
	}

	public void validateCheck() {
		if (StringUtils.isEmpty(username)) {
			this.addFieldError("username", "用户名必填！");
		}
		if (StringUtils.isEmpty(password)) {
			this.addFieldError("password", "密码必填！");
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
