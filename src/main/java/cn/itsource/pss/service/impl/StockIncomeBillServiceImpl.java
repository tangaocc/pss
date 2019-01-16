package cn.itsource.pss.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itsource.pss.domain.Depot;
import cn.itsource.pss.domain.Employee;
import cn.itsource.pss.domain.ProductStock;
import cn.itsource.pss.domain.StockIncomeBill;
import cn.itsource.pss.domain.StockIncomeBillItem;
import cn.itsource.pss.repository.ProductStockRepository;
import cn.itsource.pss.repository.StockIncomeBillRepository;
import cn.itsource.pss.service.IDepotService;
import cn.itsource.pss.service.IStockIncomeBillService;

@Service
public class StockIncomeBillServiceImpl extends BaseServiceImpl<StockIncomeBill, Long> implements IStockIncomeBillService {
	
	@Autowired
	private StockIncomeBillRepository stockIncomeBillRepository;
	@Autowired
	private IDepotService depotService;
	@Autowired
	private ProductStockRepository productStockRepository;
	
	
	
	
	/**
	 * 
	 */
	@Override
	@Transactional
	public void audit(long billId, Employee audit) {
		//通过入库单id把对象查询出来
		StockIncomeBill bill = super.queryOne(billId);
		if(bill == null){
			throw new RuntimeException("入库单不存在，不能进行一个审核");
		}else if(bill.getStatus() == 1){
			throw new RuntimeException("入库单已审核不能再次进行审批");
		}else if(bill.getStatus() == -1){
			throw new RuntimeException("入库单已驳回,不能再次进行审批");
		}
		//修改入库单状态，改为已审
		bill.setStatus(1);
		//修改审核时间
		bill.setAuditorTime(new Date());
		//修改审核人
		bill.setAuditor(audit);
		//修改入库单
		super.save(bill);
		//修改仓库数据
		Depot depot = bill.getDepot();
		//修改当前数量
		depot.setCurrentCapacity(depot.getCurrentCapacity().add(bill.getTotalNum()));
		//修改当前金额
		depot.setTotalAmount(depot.getTotalAmount().add(bill.getTotalAmount()));
		//修改仓库
		depotService.save(depot);
		//查询入库单明细
		List<StockIncomeBillItem> items = bill.getItems();
		for (StockIncomeBillItem item : items) {
			//查询当前商品在即时库存中是否存在
			String jpql = "select o from ProductStock o where o.product=? and o.depot=? ";
			List<ProductStock> stocks = productStockRepository.findByJpql(jpql, item.getProduct(),depot);
			//证明即时库存是没有的
			ProductStock stock = null;
			if(stocks.size() == 0){
				stock = new ProductStock();
				stock.setAmount(item.getAmount());
				stock.setDepot(depot);
				stock.setIncomeDate(new Date());
				stock.setNum(item.getNum());
				stock.setPrice(item.getPrice());
				stock.setProduct(item.getProduct());
			}else if(stocks.size() == 1){
				//把即时库存里面的数据取出来
				stock = stocks.get(0);
				stock.setAmount(stock.getAmount().add(item.getAmount()));
				stock.setNum(stock.getNum().add(item.getNum()));
				stock.setPrice(stock.getAmount().divide(stock.getNum() ,2, BigDecimal.ROUND_HALF_EVEN));
				stock.setIncomeDate(new Date());
			}else{
				throw new RuntimeException("程序员把代码整错了。。赶快联系一下！！");
			}
			//修改即时库存
			productStockRepository.save(stock);
		}
		
		
		
		
	}
	
}
