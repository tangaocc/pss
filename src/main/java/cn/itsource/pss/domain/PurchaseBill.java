package cn.itsource.pss.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 采购订单
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "purchasebill")
public class PurchaseBill extends BaseDomain {
	private Date vdate;// 交易时间 -> 需要手动录入
	private BigDecimal totalAmount; // 总金额 -> 系统自动计算( 明细计算)
	private BigDecimal totalNum; // 总数量 -> 系统自动计算 明细计算
	private Date inputTime = new Date(); // 录入时间 ->当前系统时间 系统自动生成的
	private Date auditorTime; // 审核时间 -> 可以为空,审核时自己生成
	/**
	 * 0待审,1已审，-1作废
	 */
	private Integer status = 0; // 单据状态 -> 默认待审
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "supplier_id")
	private Supplier supplier;// 多对一，非空 供应商(需要选择)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "auditor_id")
	private Employee auditor;// 多对一，可以为空 审核人员
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "inputUser_id")
	private Employee inputUser;// 多对一，非空 录入人 -> 登录用户就是录入人
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "buyer_id")
	private Employee buyer;// 多对一，非空 采购员 -> 需要
	// 一般组合关系使用List
	// cascade = CascadeType.ALL 级联保存，级联删除
	// mappedBy = "bill" 一方放弃关系的维护
	// orphanRemoval = true 一方可以删除多方，但是一方依然是能保留下来的 bill.getItems().clear();
	// 把多方删除掉，一方依然保留
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bill", fetch = FetchType.LAZY, orphanRemoval = true)
	private List<PurchaseBillItem> items = new ArrayList<PurchaseBillItem>();
	
	// 级联保存的时候，双方都要维护关系               1方要知道多方的目的，好做级联   ，你只需要保存1方，多方也可以保存
	//1方要知道多方的目的，好做级联   ，你只需要保存1方，多方也可以保存          为什么多方要知道一方呢？  因为多方要维护关系

	public Date getVdate() {
		return vdate;
	}

	public void setVdate(Date vdate) {
		this.vdate = vdate;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(BigDecimal totalNum) {
		this.totalNum = totalNum;
	}

	public Date getInputTime() {
		return inputTime;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public Date getAuditorTime() {
		return auditorTime;
	}

	public void setAuditorTime(Date auditorTime) {
		this.auditorTime = auditorTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Employee getAuditor() {
		return auditor;
	}

	public void setAuditor(Employee auditor) {
		this.auditor = auditor;
	}

	public Employee getInputUser() {
		return inputUser;
	}

	public void setInputUser(Employee inputUser) {
		this.inputUser = inputUser;
	}

	public Employee getBuyer() {
		return buyer;
	}

	public void setBuyer(Employee buyer) {
		this.buyer = buyer;
	}

	public List<PurchaseBillItem> getItems() {
		return items;
	}

	public void setItems(List<PurchaseBillItem> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "PurchaseBill [vdate=" + vdate + ", totalAmount=" + totalAmount + ", totalNum=" + totalNum
				+ ", inputTime=" + inputTime + ", auditorTime=" + auditorTime + ", status=" + status + "]";
	}

}