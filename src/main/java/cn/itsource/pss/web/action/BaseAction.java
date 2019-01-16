package cn.itsource.pss.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {
	public static final String RELOAD = "reload";
	
	//json的返回逻辑视图
	public static final String JSONRESULT = "jsonResult";
	
	//session通常命名： XXX_IN_SESSION    
	public static final String USER_IN_SESSION = "user_in_session";
	
	/**
	 * 把值放到map栈
	 * @param key  
	 * @param value  
	 */
	public static void putContext(String key,Object value){
		ActionContext.getContext().put(key, value);
	}
	
	/**
	 * 把指定的值放到session中
	 * @param key
	 * @param value
	 */
	public static void putSession(String key,Object value){
		ActionContext.getContext().getSession().put(key, value);
	}
}
