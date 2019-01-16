package cn.itsource.pss.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itsource.pss.domain.PurchaseBillItem;
import cn.itsource.pss.query.PurchaseBillItemQuery;
import cn.itsource.pss.repository.PurchaseBillItemRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class PurchaseBillItemServiceTest {
	@Autowired
	private PurchaseBillItemRepository repository;
	
	@Autowired
	private IPurchaseBillItemService purchaseBillItemService;
	
	@Test
	public void testQueryAll() throws ParseException{
		PurchaseBillItemQuery itemQuery = new PurchaseBillItemQuery();
		itemQuery.setStatus(1);
//		itemQuery.setBeginTime(new SimpleDateFormat("yyyy-MM-dd").parse("2018-04-27"));
//		itemQuery.setEndTime(new SimpleDateFormat("yyyy-MM-dd").parse("2018-05-28"));
//		itemQuery.setGroupBy("month(o.bill.vdate)");
		
		//分组查询
		String jpql = "select "+itemQuery.getGroupBy()+", count(*) "
				+ "from PurchaseBillItem o join o.bill b "+itemQuery.getWhereJpql()+" group by "+itemQuery.getGroupBy();
		System.out.println("分组jpql==="+jpql);
		List<Object[]> groups = repository.findByJpql(jpql, itemQuery.getParams().toArray());
		//[东湾供应商   2，  成都供应商  2]
		for (Object[] objects : groups) {//循环2次
			System.out.println("分组后的结果值："+Arrays.toString(objects));
			//明细jpql语句--->你查询所有的明细
			jpql = "select  o  from  PurchaseBillItem o where "+itemQuery.getGroupBy()+"=? "+itemQuery.getWhereJpql().replaceFirst("where", "and");
			//select  o  from  PurchaseBillItem o where o.bill.supplier.name=?  and o.bill.status=?
			List<Object> params = new ArrayList<Object>();
			params.add(objects[0]);
			//把itemQuery.getParams()集合中所有的元素追加到params集合中
			params.addAll(itemQuery.getParams());
		
			List<PurchaseBillItem> items = repository.findByJpql(jpql, params.toArray());
			System.out.println("明细jpql========="+jpql);
			System.out.println(params);
			for (PurchaseBillItem item : items) {
				System.out.println(item);
			}
					
		}
	}
	@Test
	public void testGroupBy(){
		PurchaseBillItemQuery itemQuery = new PurchaseBillItemQuery();
		itemQuery.setStatus(1);
		itemQuery.setGroupBy("month(o.bill.vdate)");
		
		List<Object[]> findGroupByByQuery = purchaseBillItemService.findGroupByByQuery(itemQuery);
		for (Object[] objects : findGroupByByQuery) {
			System.out.println(Arrays.toString(objects));
		}
	}
	@Test
	public void testfindItems(){
		PurchaseBillItemQuery itemQuery = new PurchaseBillItemQuery();
		itemQuery.setStatus(1);
		itemQuery.setGroupBy("month(o.bill.vdate)");
		
		List<PurchaseBillItem> findItemsByQuery = purchaseBillItemService.findItemsByQuery(itemQuery, "4");
		for (PurchaseBillItem purchaseBillItem : findItemsByQuery) {
			System.out.println(purchaseBillItem);
		}
	}
}
