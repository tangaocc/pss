package cn.itsource.pss.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itsource.pss.domain.Depot;
import cn.itsource.pss.domain.Employee;
import cn.itsource.pss.domain.Product;
import cn.itsource.pss.domain.StockIncomeBill;
import cn.itsource.pss.domain.StockIncomeBillItem;
import cn.itsource.pss.domain.Supplier;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class StockIncomeBillItemServiceTest {
	@Autowired
	private IStockIncomeBillItemService stockIncomeBillItemService;
	
	@Autowired
	private IStockIncomeBillService stockIncomebillService;
	
	@Test
	public void testQueryAll(){
		System.out.println(stockIncomeBillItemService.queryAll());
	}
	
	
	@Test
	public void save() throws Exception {
		//新增入库单
		StockIncomeBill bill = new StockIncomeBill();
		bill.setDepot(new Depot(1L));
		bill.setInputUser(new Employee(1L));
		bill.setKeeper(new Employee(2L));
		bill.setSupplier(new Supplier(2L));
		bill.setVdate(new Date());

		// 2个入库明细
		List<StockIncomeBillItem> items = new ArrayList<StockIncomeBillItem>();
		StockIncomeBillItem billItem = new StockIncomeBillItem();
		billItem.setDescs("备注3");
		billItem.setNum(new BigDecimal(1));
		billItem.setPrice(new BigDecimal(1));
		billItem.setProduct(new Product(1L));
		items.add(billItem);

		StockIncomeBillItem billItem2 = new StockIncomeBillItem();
		billItem2.setDescs("备注4");
		billItem2.setNum(new BigDecimal(2));
		billItem2.setPrice(new BigDecimal(2));
		billItem2.setProduct(new Product(2L));
		items.add(billItem2);

		BigDecimal totalAmount = new BigDecimal(0);// 总金额
		BigDecimal totalNum = new BigDecimal(0);// 总数量
		for (StockIncomeBillItem item : items) {
			// 设置多方到一方的关系
			item.setBill(bill);
			// 计算小计
			item.setAmount(item.getPrice().multiply(item.getNum()));
			// 累加
			totalAmount = totalAmount.add(item.getAmount());
			totalNum = totalNum.add(item.getNum());
		}
		// 设置总金额,总数量
		bill.setTotalAmount(totalAmount);
		bill.setTotalNum(totalNum);

		// 设置一方到多方的关系
		bill.setItems(items);

		// 级联保存
		stockIncomebillService.save(bill);
	}
	
	
	@Test
	public void audit(){
		Employee employee = new Employee(1L);
		stockIncomebillService.audit(2L, employee);
	}
}
