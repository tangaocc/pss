package cn.itsource.pss.page;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.chainsaw.Main;
/*PageList currentpage 与 pageSize 是要展示给用户看的*/
public class PageList<T> {
	//当前页
	private Integer currentPage = 1;
	//每页10条
	private Integer pageSize = 10;
	//总共条数
	private Integer totalCount;
	//总共页数
	private Integer totalPage;
	//每页的数据
	private List<T> data = new ArrayList<T>();
	
	
	
	public PageList() {
	}
	
	/**
	 * @param currentPage  当前页    前端页面传递过来的 
	 * @param pageSize	 每页条数  前端页面传递过来
	 * @param totalCount  通过jpql语句查询出来的
	 */
	public PageList(Integer currentPage, Integer pageSize, Integer totalCount) {
		//如果当前页是一个负数，那我们就让它默认值是第一页
		this.currentPage = currentPage < 1 ? this.currentPage : currentPage ;
		//如果每页的条数是小于0的，那我们就给它默认值为10条，否则的话，就按照我们传递过来的值来作为条数
		this.pageSize = pageSize <= 0 ?  this.pageSize : pageSize;
		this.totalCount = totalCount;
		//计算总共页数  方式1：
//		this.totalPage = this.totalCount%this.pageSize==0 ? this.totalCount/this.pageSize : this.totalCount/this.pageSize+1;
		//计算总共页数 方式2：  使用方法的方式
		this.totalPage = (int)Math.ceil(Double.valueOf(this.totalCount)/Double.valueOf(this.pageSize));
		//当前页,如果当前页是大于总页数了之后，当前页应该是总页数
		this.currentPage =  this.currentPage > this.totalPage ? this.totalPage : this.currentPage;
		
	}
	
	//分页条展示5个数据
	private static final int SHOWPAGECONUNT = 5;
	
	/**
	 * 获取开始条数
	 */
	public int getBegin(){
		return totalCount==null ? 0:(this.currentPage-1)*this.pageSize+1;
	}
	
	public int getEnd(){
		return totalCount == null ? 0 :(this.currentPage*this.pageSize > totalCount ? totalCount : this.currentPage*this.pageSize );
	}
	
	/**
	 * 返回分页条
	 * @return
	 */
	public String getPagination(){
		StringBuilder sb = new StringBuilder();
		sb.append("<ul class='pagination'>");
		if(this.currentPage == 1){// 如果当前页是首页的话，我们不想让它继续进行点击(首页，上一页)
			sb.append("<li  class='prev disabled'><a href='javascript:void(0);'>首页</a></li>");
			sb.append("<li class='prev disabled'><a href='javascript:void(0);'>上一页</a></li>");
		}else{
			sb.append("<li><a href='javascript:goPage(1);'>首页</a></li>");
			sb.append("<li><a href='javascript:goPage("+(this.currentPage-1==0?this.currentPage:this.currentPage-1)+")'>上一页</a></li>");
		}
		/*
		 * totalIndexCount: 我的分页条展示几个数据
		 * currentPage: 当前页
		 * totalPage：总共页数
		 */
		PageIndex pageIndex = PageIndex.getPageIndex(SHOWPAGECONUNT, currentPage, totalPage);
		
		//pageIndex.getBeginIndex() 开始页码
		//pageIndex.getEndIndex()  结束页码
		//显示具体的页数
		for (int i = pageIndex.getBeginIndex(); i <= pageIndex.getEndIndex(); i++) {
			if(i == this.currentPage){
				sb.append("<li class='active'><a href='javascript:void(0);'>"+i+"</a></li>");
			}else{
				sb.append("<li><a href='javascript:goPage("+i+")'>"+i+"</a></li>");
			}
		}
		
		if(this.currentPage == totalPage){// 如果当前页是一个尾页，我们不想让它继续进行一个点击
			sb.append("<li class='prev disabled'><a href='javascript:void(0);'>下一页</a></li>");
			sb.append("<li class='prev disabled'><a href='javascript:void(0);'>尾页</a></li>");
		}else{
			sb.append("<li><a href='javascript:goPage("+(this.currentPage+1>this.totalPage?this.totalPage:this.currentPage+1)+")'>下一页</a></li>");
			sb.append("<li><a href='javascript:goPage("+this.totalPage+")'>尾页</a></li>");
		}
		sb.append("</ul>");
		return sb.toString();
//		<ul class="pagination">
//		<li class="prev disabled"><a href="#"><i
//				class="icon-double-angle-left"></i></a></li>
//		<li class="active"><a href="#">1</a></li>
//		<li><a href="#">2</a></li>
//		<li><a href="#">3</a></li>
//		<li class="next"><a href="#"><i
//				class="icon-double-angle-right"></i></a></li>
//	</ul>
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
	public Integer getTotalCount() {
		return totalCount == null ?  0 : totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "PageList [currentPage=" + currentPage + ", pageSize=" + pageSize + ", totalCount=" + totalCount
				+ ", totalPage=" + totalPage + ", data=" + data.size() + "]";
	}

	
	
	
}
