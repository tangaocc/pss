package cn.itsource.pss.service;

import cn.itsource.pss.domain.Employee;
import cn.itsource.pss.domain.StockIncomeBill;

public interface IStockIncomeBillService extends IBaseService<StockIncomeBill, Long> {
	
	/**
	 * 审核入库单
	 * @param billId  入库单id
	 * @param audit	  审核人员
	 */
	public void audit(long billId,Employee audit);

}
