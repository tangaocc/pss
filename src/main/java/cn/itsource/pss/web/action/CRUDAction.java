package cn.itsource.pss.web.action;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * 抽取公共的代码与结构
 * @author Administrator
 *
 * @param <T>
 */
public abstract  class CRUDAction<T> extends BaseAction implements Preparable,ModelDriven<T> {
	protected Long id;
	
	
	protected abstract void list() throws Exception;
	
	
	public String execute() throws Exception{
		list();
		return SUCCESS;
	} 
	
	
	/*跳转到新增或者修改界面*/
	public abstract  String input() throws Exception;
	
	/*新增方法*/
	public abstract String save() throws Exception;
	
	/*删除方法*/
	public abstract String delete()  throws Exception;
	
	/*在执行input方法之前，必须要执行该prepareInput方法*/
	public abstract void prepareInput() throws Exception;
	
	/*在执行save方法之前，必须要执行该prepareSave方法*/
	public  abstract void prepareSave() throws Exception;
	
	
	/*因为让编译能正常的通过，prepare方法里面一般不写代码的*/
	@Override
	public void prepare() throws Exception {
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	

}
