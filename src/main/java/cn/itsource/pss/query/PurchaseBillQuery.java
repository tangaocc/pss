package cn.itsource.pss.query;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import cn.itsource.pss.domain.PurchaseBill;

/**
 * 接收前端传递过来的参数
 * 
 * @author Administrator
 *
 */
public class PurchaseBillQuery extends BaseQuery {

	private Integer status;
	//交易开始时间
	private Date beginTime;
	//交易结束时间
	private Date endTime;

	public PurchaseBillQuery() {
		super(PurchaseBill.class);
	}

	/**
	 * 添加条件
	 */
	@Override
	public void addCondition() {
		//-2是一个请选择
		if(status != null && status != -2){
			super.addWhere("status=?", status);
		}
		if(beginTime != null){
			super.addWhere("vdate>=?", beginTime);
		}
		if(endTime!=null){
			//方式1：new Date(endTime.getTime()+24*60*60*1000); 这种方式太过于麻烦了
			//方式2：日历对象 可以的，但是太过于麻烦了
			//方式3： 使用第三方的工具包，别人已经给我们实现好了的
			//加天数
			Date etime = DateUtils.addDays(endTime, 1);
			super.addWhere("vdate<?", etime);
		}

	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
