package cn.itsource.pss.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itsource.pss.domain.StockIncomeBillItem;
import cn.itsource.pss.repository.StockIncomeBillItemRepository;
import cn.itsource.pss.service.IStockIncomeBillItemService;

@Service
public class StockIncomeBillItemServiceImpl extends BaseServiceImpl<StockIncomeBillItem, Long> implements IStockIncomeBillItemService {
	
	@Autowired
	private StockIncomeBillItemRepository stockIncomeBillItemRepository;
	
	
}
