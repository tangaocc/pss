package cn.itsource.pss.web.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

import cn.itsource.pss.domain.Employee;
import cn.itsource.pss.domain.PurchaseBill;
import cn.itsource.pss.domain.PurchaseBillItem;
import cn.itsource.pss.page.PageList;
import cn.itsource.pss.query.PurchaseBillQuery;
import cn.itsource.pss.service.IEmployeeService;
import cn.itsource.pss.service.IPurchaseBillService;
import cn.itsource.pss.service.ISupplierService;

@Controller
@Scope("prototype")
public class PurchaseBillAction extends CRUDAction<PurchaseBill>{
	
	
	@Autowired
	private IPurchaseBillService purchaseBillService;
	@Autowired
	private ISupplierService supplierService;
	@Autowired
	private IEmployeeService employeeService;
	
	private PageList<PurchaseBill>  pageList;
	private PurchaseBillQuery baseQuery = new  PurchaseBillQuery();
	private PurchaseBill purchaseBill;
	
	
	
	//对象栈还是应该放到map栈中
	//规范： 如果你当前Action操作的实体domian是本类的，那就放到对象栈中， 如果是操作的其他实体类，那统一都放到map栈中
	@Override
	protected void list() throws Exception {
		//查询员工列表
		pageList = purchaseBillService.findPageByQuery(baseQuery);
	}
	

	/**
	 * 新增或者修改的时候，跳转页面  input.jsp
	 */
	@Override
	public String input() throws Exception {
		//查询供应商列表
		putContext("suppliers", supplierService.queryAll());
		//查询采购员
		putContext("employees", employeeService.queryAll());
		//存储当前时间
		putContext("now", new Date());
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
		//多方知道一方
		List<PurchaseBillItem> items = purchaseBill.getItems();
		//总金额=所有明细的小计
		BigDecimal totalAmount = new BigDecimal("0");
		//总数量=所有明细的数量
		BigDecimal totalNum = new BigDecimal("0");
		for (PurchaseBillItem purchaseBillItem : items) {
			//多方知道一方
			purchaseBillItem.setBill(purchaseBill);
			//计算小计    小计=数量*价格
			BigDecimal amount = purchaseBillItem.getPrice().multiply(purchaseBillItem.getNum());
			//设置小计
			purchaseBillItem.setAmount(amount);
			
			//累加小计
			totalAmount = totalAmount.add(purchaseBillItem.getAmount());
			//累加数量
			totalNum = totalNum.add(purchaseBillItem.getNum());
		}
		purchaseBill.setTotalAmount(totalAmount);
		purchaseBill.setTotalNum(totalNum);
		if(id == null){//证明是新增
			purchaseBill.setInputUser((Employee)ActionContext.getContext().getSession().get(USER_IN_SESSION));
		}
		if(purchaseBill.getId() == null){
			//如果是新增，我就给baseQuery设置最大页数
			baseQuery.setCurrentPage(Integer.MAX_VALUE);
		}
		purchaseBillService.save(purchaseBill);// 当purchaseBill保存之后，purchaseBill就变为是一个持久状态，持久状态有id
		return RELOAD;
	}
	
	public String delete() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		try {
			purchaseBillService.delete(id);
			response.getWriter().print("{\"success\":true,\"message\":\"恭喜你!删除成功\"}");
		} catch (Exception e) {
			response.getWriter().print("{\"success\":false,\"message\":\"亲，真的报错了，你手斗了，"+e.getMessage()+"\"}");
		}
		return NONE;
	}
	
	

	
	public void prepareInput() throws Exception {
		if(id != null){
			purchaseBill = purchaseBillService.queryOne(id);
		}
	}
	
	public void prepareSave(){
		if(id == null){
			purchaseBill = new PurchaseBill();
		}else{
			purchaseBill = purchaseBillService.queryOne(id);
			purchaseBill.getItems().clear();
			//清空采购员
			purchaseBill.setBuyer(null);
			//清空供应商
			purchaseBill.setSupplier(null);
			
		}
	
	}
	
	

	@Override
	public PurchaseBill getModel() {
		return purchaseBill;
	}
	


	public PurchaseBill getPurchaseBill() {
		return purchaseBill;
	}

	public PurchaseBillQuery getBaseQuery() {
		return baseQuery;
	}

	public PageList<PurchaseBill> getPageList() {
		return pageList;
	}
	

}
