package cn.itsource.pss.web.interceptor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import cn.itsource.pss.domain.Employee;
import cn.itsource.pss.service.IPermissionService;
import cn.itsource.pss.web.action.BaseAction;

/**
 * 权限拦截器
 * @author Administrator
 *
 */
public class PermissionInterceptor extends AbstractInterceptor {

	
	@Autowired
	private IPermissionService permissionService;
	
	private String excludeActions;
	
	
    public void setExcludeActions(String excludeActions) {
        this.excludeActions = excludeActions;
    }
	    

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//只要是访问LoginAction都应该进行一个放行
		String action = invocation.getAction().getClass().getSimpleName();//拿到正在执行的action
		String method = invocation.getProxy().getMethod();
		//如果是LoginAction直接放行的
		if(excludeActions.contains(action)){
			return invocation.invoke();
		}
		Object object = ActionContext.getContext().getSession().get(BaseAction.USER_IN_SESSION);
		if(object == null){
			return Action.LOGIN;
		}
		//判断权限---前提：必须要先登录
		
		//1.获取所有的权限数据，判断哪些模块需要权限判断
		List<String> permissions = permissionService.findAllMethod();
		//2.获取当前正在执行的目标方法
		String url = action+"."+method;
		String allUrl = action+".ALL";
		//如果permission表中是包含你正在访问的url地址，就证明需要拦截下来，判断你用户是否拥有访问的权限
		if(permissions.contains(url)){
			//查看当前用户拥有哪些权限
			List<String> permissionList = permissionService.findPermissionByEmployee((Employee)object);
			if(permissionList.contains(url) || permissionList.contains(allUrl)){//如果包含直接放行
				return invocation.invoke();
			}else{//该员工是没有权限
				return "noAuth";
			}
		}else{//不包含就直接放行
			return invocation.invoke();
		}
		
		
	}

}
