package cn.itsource.pss.web.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Root;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

import cn.itsource.pss.domain.Product;
import cn.itsource.pss.domain.ProductType;
import cn.itsource.pss.domain.SystemDictionaryDetail;
import cn.itsource.pss.domain.SystemDictionaryType;
import cn.itsource.pss.page.PageList;
import cn.itsource.pss.query.ProductQuery;
import cn.itsource.pss.service.IProductService;
import cn.itsource.pss.service.IProductTypeService;
import cn.itsource.pss.service.ISystemDictionaryDetailService;
import net.coobird.thumbnailator.Thumbnails;

@Controller
@Scope("prototype")
public class ProductAction extends CRUDAction<Product>{
	
	
	@Autowired
	private IProductService productService;
	@Autowired
	private ISystemDictionaryDetailService systemDictionaryDetailService;
	
	@Autowired
	private IProductTypeService typeService;
	
	private PageList<Product>  pageList;
	private ProductQuery baseQuery = new  ProductQuery();
	private Product product;
	//上传的附件
	private File upload;
	//附件名
	private String uploadFileName;
	
	
	

	//对象栈还是应该放到map栈中
	//规范： 如果你当前Action操作的实体domian是本类的，那就放到对象栈中， 如果是操作的其他实体类，那统一都放到map栈中
	@Override
	protected void list() throws Exception {
		//查询员工列表
		pageList = productService.findPageByQuery(baseQuery);
	}
	
	/**
	 * 采购订单选中的产品
	 * @return
	 */
	public String bill(){
		//查询员工列表
		pageList = productService.findPageByQuery(baseQuery);
		return "bill";
	}
	
	public String findChildTypeByPid(){
		//二级产品类型
		List<ProductType> twoLevels = typeService.findTwoLevelByPid(id);
		putContext("map", twoLevels);
		return JSONRESULT;
	}
	
	
	/**
	 * 新增或者修改的时候，跳转页面  input.jsp
	 */
	@Override
	public String input() throws Exception {
		//查询单位
		List<SystemDictionaryDetail> units = systemDictionaryDetailService.findSystemDictionaryDetailByTypeSn(SystemDictionaryType.UNIT);
		//查询品牌
		List<SystemDictionaryDetail> brands = systemDictionaryDetailService.findSystemDictionaryDetailByTypeSn(SystemDictionaryType.BRAND);
		List<ProductType> twoLevels = new ArrayList<ProductType>();
		if(id == null){//证明就是新增
			ProductType type = new ProductType();
			type.setName("--请选择--");
			twoLevels.add(type);
		}else{//证明就是修改
			twoLevels = typeService.findTwoLevelByPid(product.getType().getParent().getId());
		}
		//一级产品类型
		putContext("oneLevels", typeService.findOneLevelType());
		//二级产品类型
		putContext("twoLevels", twoLevels);
		//单位
		putContext("units", units);
		//品牌
		putContext("brands", brands);
	
		return INPUT;
	}
	
	/**
	 * 新增和修改的方法
	 * 	@InputConfig
	 * 				methodName： 如果在执行目标方法的时候，报错了，它就会去执行methodName对应的方法
	 * 				resultName：如果在执行目标方法的时候，报错了，它就会直接返回逻辑视图为resultName对应的值，如果没写默认值就是input
	 * 				
	 * @return
	 * @throws IOException 
	 */
	@InputConfig(methodName = INPUT)
	public String save() throws IOException{
		if(upload != null){//如果upload不为空，我们则上传
			ServletContext servletContext = ServletActionContext.getRequest().getServletContext();
			
			//证明就是图片的修改,把之前的图片删除掉，重新上传
			if(id != null && StringUtils.isNotBlank(product.getPic())){
				//获取大图
				String pic = product.getPic();
				
				File file = new File(servletContext.getRealPath("/"),pic);
				if(file.exists()){//如果图片存在，则删除
					file.delete();
				}
				//获取小图
				String smallPic = product.getSmallPic();
				file = new File(servletContext.getRealPath("/"),smallPic);
				if(file.exists()){//如果图片存在，则删除
					file.delete();
				}
			}
			//获取upload的绝对路径
			String realPath = servletContext.getRealPath("/upload");
			//随机产生附件名             199992292929292929.jpg
			long currentTimeMillis = System.currentTimeMillis();
			String fileName = currentTimeMillis+"."+FilenameUtils.getExtension(uploadFileName);
			String smallfileName = currentTimeMillis+"_small."+FilenameUtils.getExtension(uploadFileName);
			
			//目标文件（大图片）
			File destFile = new File(realPath,fileName);
			//目标文件(小图片)
			File smallDestFile = new File(realPath,smallfileName);
			//判断父文件是否存在，如果不存在，则创建父文件
			if(!destFile.getParentFile().exists()){
				destFile.getParentFile().mkdirs();
			}
			//上传的核心代码
			FileUtils.copyFile(upload, destFile);
			//设置大图片
			product.setPic("/upload/"+fileName);
			//压缩图片
			Thumbnails.of(destFile).scale(0.1f).toFile(smallDestFile);
			//设置小图片
			product.setSmallPic("/upload/"+smallfileName);
		}
		if(product.getId() == null){
			//如果是新增，我就给baseQuery设置最大页数
			baseQuery.setCurrentPage(Integer.MAX_VALUE);
		}
		productService.save(product);// 当product保存之后，product就变为是一个持久状态，持久状态有id
		return RELOAD;
	}
	
	public String delete() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		try {
			product = productService.queryOne(id);
			if(StringUtils.isNotBlank(product.getPic())){
				ServletContext servletContext = ServletActionContext.getRequest().getServletContext();
				String realPath = servletContext.getRealPath("/");
				//获取大图片file对象
				File file = new File(realPath,product.getPic());
				//如果大图片存在直接删除
				if(file.exists()){
					file.delete();
				}
				//获取小图片的file对象
				file = new File(realPath,product.getSmallPic());
				//小图片如果存在，直接删除
				if(file.exists()){
					file.delete();
				}
			}
			productService.delete(id);
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
	 * 		判断新增还是修改，我们可以通过product的主键id进行一个判断
	 * 				1. 新增
						  验证用户名是否存在
							1.拿到当前用户名去数据库中进行查询
							2.如果查询的总数>0,那证明就已经存在了
							3.如果查询的总数=0,那证明该用户名是不存在的
					
	 * 				2. 修改
	 * 					 通过id去把对应的product对象查询出来
	 * 						1.username与product对应的 username进行一个标胶
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
		response.getWriter().println("{\"valid\":"+productService.checkName(id,name)+",\"message\":\"用户名重复了\"}");
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
		if(StringUtils.isEmpty(product.getName())){
			this.addFieldError("name", "名字不能为空！！");
		}
	}


	
	public void prepareInput() throws Exception {
		if(id != null){
			product = productService.queryOne(id);
		}
	}
	
	public void prepareSave(){
		if(id == null){
			product = new Product();
		}else{
			product = productService.queryOne(id);
			//凡是有关联关系都要先清空
			product.setUnit(null);
			product.setBrand(null);
			product.setType(null);
		}
	
	}
	
	

	@Override
	public Product getModel() {
		return product;
	}
	

	public void setName(String name){
		this.name = name;
	}
	
	

	public Product getProduct() {
		return product;
	}

	public ProductQuery getBaseQuery() {
		return baseQuery;
	}

	public PageList<Product> getPageList() {
		return pageList;
	}

	
	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

}
