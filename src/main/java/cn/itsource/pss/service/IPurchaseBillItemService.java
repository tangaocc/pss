package cn.itsource.pss.service;

import java.util.List;

import cn.itsource.pss.domain.PurchaseBillItem;
import cn.itsource.pss.query.PurchaseBillItemQuery;

public interface IPurchaseBillItemService extends IBaseService<PurchaseBillItem, Long> {
	
	/**
	 * 根据条件进行分组
	 * 包含统计总数量，总价格
	 * @param query
	 * @return
	 */
	public List<Object[]> findGroupByByQuery(PurchaseBillItemQuery query);
	
	/**
	 * 根据条件进行分组（不包含统计总数量，总价格）
	 * @param query
	 * @return
	 */
	public List<Object[]> findGroupByByQuery2(PurchaseBillItemQuery query);
	
	/**
	 * 查询采购订单明细
	 * @param query  查询条件
	 * @param value  数组中的第一个值
	 * @return
	 */
	public List<PurchaseBillItem> findItemsByQuery(PurchaseBillItemQuery query,Object value);
}
