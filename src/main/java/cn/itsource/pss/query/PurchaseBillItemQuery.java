package cn.itsource.pss.query;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import cn.itsource.pss.domain.PurchaseBillItem;

/**
 * 接收前端传递过来的参数
 * 
 * @author Administrator
 *
 */
public class PurchaseBillItemQuery extends BaseQuery {
	
	private Date beginTime;
	private Date endTime;
	private Integer status;
	private String groupBy = "o.bill.supplier.name";

	

	public PurchaseBillItemQuery() {
		super(PurchaseBillItem.class);
	}

	/**
	 * 添加条件
	 */
	@Override
	public void addCondition() {
		if(status != null && status != -2){
			super.addWhere("o.bill.status=?", status);
		}
		if(beginTime != null){
			super.addWhere("o.bill.vdate>=?", beginTime);
		}
		if(endTime != null){
			super.addWhere("o.bill.vdate<?", DateUtils.addDays(endTime, 1));
		}
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}



}
