
package cn.itsource.pss.web.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

import cn.itsource.pss.domain.PurchaseBillItem;
import cn.itsource.pss.page.PageList;
import cn.itsource.pss.query.PurchaseBillItemQuery;
import cn.itsource.pss.service.IPurchaseBillItemService;

@Controller
@Scope("prototype")
public class PurchaseBillItemAction extends CRUDAction<PurchaseBillItem>{
	
	
	@Autowired
	private IPurchaseBillItemService purchaseBillItemService;
	
	private PageList<PurchaseBillItem>  pageList;
	private PurchaseBillItemQuery baseQuery = new  PurchaseBillItemQuery();
	private PurchaseBillItem purchaseBillItem;
	
	
	
	//对象栈还是应该放到map栈中
	//规范： 如果你当前Action操作的实体domian是本类的，那就放到对象栈中， 如果是操作的其他实体类，那统一都放到map栈中
	@Override
	protected void list() throws Exception {
		putContext("groups", purchaseBillItemService.findGroupByByQuery(baseQuery));
	}
	
	//查询订单明细
	public List<PurchaseBillItem> findItems(Object value){
		List<PurchaseBillItem> items = purchaseBillItemService.findItemsByQuery(baseQuery, value);
		return items;
	}
	
	/**
	 * 查询分组
	 * @return
	 */
	public String findGroups(){
		List<Object[]> groups = purchaseBillItemService.findGroupByByQuery2(baseQuery);
		List<Map<String,Object>> list = new ArrayList<>();
		for (Object[] objects : groups) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("name", objects[0]);
			map.put("y", objects[1]);
			list.add(map);
			
		}
		putContext("map", list);
		return JSONRESULT;
	}

	/**
	 * 新增或者修改的时候，跳转页面  input.jsp
	 */
	@Override
	public String input() throws Exception {
		return INPUT;
	}
	
	
	/**
	 * 报表3d图
	 * @return
	 */
	public String reports3d(){
		return "reports3d";
	}
	/**
	 * 报表2d图
	 * @return
	 */
	public String reports2d(){
		return "reports2d";
	}
	/**
	 * 报表柱状图
	 * @return
	 */
	public String reportsPercent(){
		return "reportsPercent";
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
		if(purchaseBillItem.getId() == null){
			//如果是新增，我就给baseQuery设置最大页数
			baseQuery.setCurrentPage(Integer.MAX_VALUE);
		}
		purchaseBillItemService.save(purchaseBillItem);// 当purchaseBillItem保存之后，purchaseBillItem就变为是一个持久状态，持久状态有id
		return RELOAD;
	}
	
	public String delete() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		try {
			purchaseBillItemService.delete(id);
			response.getWriter().print("{\"success\":true,\"message\":\"恭喜你!删除成功\"}");
		} catch (Exception e) {
			response.getWriter().print("{\"success\":false,\"message\":\"亲，真的报错了，你手斗了，"+e.getMessage()+"\"}");
		}
		return NONE;
	}
	

	

	


	
	public void prepareInput() throws Exception {
		if(id != null){
			purchaseBillItem = purchaseBillItemService.queryOne(id);
		}
	}
	
	public void prepareSave(){
		if(id == null){
			purchaseBillItem = new PurchaseBillItem();
		}else{
			purchaseBillItem = purchaseBillItemService.queryOne(id);
		}
	
	}
	
	

	@Override
	public PurchaseBillItem getModel() {
		return purchaseBillItem;
	}

	
	

	public PurchaseBillItem getPurchaseBillItem() {
		return purchaseBillItem;
	}

	public PurchaseBillItemQuery getBaseQuery() {
		return baseQuery;
	}

	public PageList<PurchaseBillItem> getPageList() {
		return pageList;
	}
	

}
