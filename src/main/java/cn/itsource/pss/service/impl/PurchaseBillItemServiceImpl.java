package cn.itsource.pss.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itsource.pss.domain.PurchaseBillItem;
import cn.itsource.pss.query.PurchaseBillItemQuery;
import cn.itsource.pss.repository.PurchaseBillItemRepository;
import cn.itsource.pss.service.IPurchaseBillItemService;

@Service
public class PurchaseBillItemServiceImpl extends BaseServiceImpl<PurchaseBillItem, Long> implements IPurchaseBillItemService {
	
	@Autowired
	private PurchaseBillItemRepository purchaseBillItemRepository;

	@Override
	public List<Object[]> findGroupByByQuery(PurchaseBillItemQuery query) {
		String jpql = "select "+query.getGroupBy()+",count(*),sum(o.num),sum(o.amount) "
				+ "from PurchaseBillItem o "
				+ "join o.bill "+query.getWhereJpql()+" "
				+ "group by "+query.getGroupBy();
		return purchaseBillItemRepository.findByJpql(jpql, query.getParams().toArray());
	}

	@Override
	public List<PurchaseBillItem> findItemsByQuery(PurchaseBillItemQuery query, Object value) {
		String jpql = "select o from PurchaseBillItem o join o.bill  "
				+ "where "+query.getGroupBy()+"=? "+query.getWhereJpql().replaceFirst("where","and");
		List<Object> params = new ArrayList<Object>();
		params.add(value);
		params.addAll(query.getParams());
		return purchaseBillItemRepository.findByJpql(jpql, params.toArray());
		
	}

	@Override
	public List<Object[]> findGroupByByQuery2(PurchaseBillItemQuery query) {
		String jpql = "select "+query.getGroupBy()+",count(*) "
				+ "from PurchaseBillItem o "
				+ "join o.bill "+query.getWhereJpql()+" "
				+ "group by "+query.getGroupBy();
		return purchaseBillItemRepository.findByJpql(jpql, query.getParams().toArray());
	}
	
}
