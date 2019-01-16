package cn.itsource.pss.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itsource.pss.domain.PurchaseBillItem;
import cn.itsource.pss.query.PurchaseBillQuery;
import cn.itsource.pss.repository.PurchaseBillRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class PurchaseBillServiceTest {
	@Autowired
	private PurchaseBillRepository purchaseBillRepository;
	
	@Test
	public void testQueryAll(){ 
		//第一条jpql语句，分组查询
		//根据供应商进行分组
		String groupBy = "b.supplier.name";
		//采购员分组
		groupBy = "b.buyer.username";
		//月份分组
		groupBy = "month(b.vdate)";
		String jpql = "select "+groupBy+",count(*) from PurchaseBillItem o join o.bill b  group by "+groupBy;
		System.out.println("分组jpql:"+jpql);
		List<Object[]> list = purchaseBillRepository.findByJpql(jpql);
		for (Object[] objects : list) {
			System.out.println(Arrays.toString(objects));
			//查看明细jpql
			jpql = "select o from PurchaseBillItem o join o.bill b where "+groupBy+"=?";
			System.out.println("明细jpql:"+jpql);
			//查看明细
			List<PurchaseBillItem> items = purchaseBillRepository.findByJpql(jpql, objects[0]);
			for (PurchaseBillItem item : items) {
				System.out.println(item);
			}
			
		}
	}
	
	
	@Test
	public void testConditionQuery(){
		
		PurchaseBillQuery billQuery = new  PurchaseBillQuery();
		billQuery.setStatus(1);
//		billQuery.setBeginTime(new Date());
//		billQuery.setEndTime(new Date());
		//供应商分组
		String groupBy = "b.supplier.name";
		//根据采购员分组
		groupBy = "b.buyer.username";
		//月份分组
		groupBy = "month(b.vdate)";
		String jpql = "select "+groupBy+",count(*) from PurchaseBillItem o join o.bill b "
				+billQuery.getWhereJpql()+" group by "+groupBy;
		
		System.out.println(jpql);
		System.out.println(billQuery.getParams());
		List<Object[]> groups = purchaseBillRepository.findByJpql(jpql, billQuery.getParams().toArray());
		
		for (Object[] objects : groups) {
			System.out.println(Arrays.toString(objects));
		}
	
		
	}
}
