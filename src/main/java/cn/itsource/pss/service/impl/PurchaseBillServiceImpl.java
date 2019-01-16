package cn.itsource.pss.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itsource.pss.domain.PurchaseBill;
import cn.itsource.pss.repository.PurchaseBillRepository;
import cn.itsource.pss.service.IPurchaseBillService;

@Service
public class PurchaseBillServiceImpl extends BaseServiceImpl<PurchaseBill, Long> implements IPurchaseBillService {
	
	@Autowired
	private PurchaseBillRepository purchaseBillRepository;

}
