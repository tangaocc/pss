package cn.itsource.pss.query;

import java.util.ArrayList;
import java.util.List;

/*BaseQuery  currentPage，pageSize是前端用户传递给后端用户的*/
public abstract class BaseQuery {
	// 当前页
	private Integer currentPage = 1;
	// 每页条数
	private Integer pageSize = 10;
	//获取查询总共条数的jpql语句
	private StringBuilder countJpql;
	//获取分页数据的jpq语句
	private StringBuilder limitJpql;
	//拼接条件的jpql语句
	private StringBuilder whereJpql;
	//定义一个集合专门用来添加参数的
	private List<Object> params ;
	
	
	
	public BaseQuery(Class clz) {
		countJpql = new StringBuilder("select count(o) from "+clz.getName()+" o");
		limitJpql = new StringBuilder("select o from "+clz.getName()+" o");
		whereJpql = new StringBuilder();
		params = new ArrayList<Object>();
	}
	
	/**
	 * 新增一个方法，让子类去添加条件
	 */
	public abstract void addCondition();
	
	/**
	 * 拼接条件的jpql语句
	 * @param jpql  条件的jpql语句
	 * @param param  usernmae like ?   
	 * 
	 * select o from Employee o  where username like ? and o.password=?
	 */
	public void addWhere(String jpql,Object param){
		if(params.size() == 0){
			limitJpql.append(" where ").append(jpql);
			countJpql.append(" where ").append(jpql);
			whereJpql.append(" where ").append(jpql);
		}else{
			limitJpql.append(" and ").append(jpql);
			countJpql.append( " and ").append(jpql);
			whereJpql.append( " and ").append(jpql);
		}
		params.add(param);
	}
	

	
	//定义一个开关，开关默认是开启的，当开关开启的时候，我们是能执行addCondition();这个方法的，如果开关是关闭的，我们就不能执行它
	private boolean flag = true; 
	public void  builderWhere(){
		if(flag){
			//拼接了条件1次
			addCondition();
			//关闭开关
			flag = false;
		}
	}
	
	public String getCountJpql() {
		builderWhere();
		return countJpql.toString();
	}
	
	public String getWhereJpql(){
		builderWhere();
		return whereJpql.toString();
	}
	

	public List<Object> getParams() {
		builderWhere();
		return params;
	}

	public String getLimitJpql() {
		builderWhere();
		return limitJpql.toString();
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
