package cn.itsource.pss.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itsource.pss.domain.ProductStock;
import cn.itsource.pss.repository.ProductStockRepository;
import cn.itsource.pss.service.IProductStockService;

@Service
public class ProductStockServiceImpl extends BaseServiceImpl<ProductStock, Long> implements IProductStockService {
	
	@Autowired
	private ProductStockRepository productStockRepository;
	
}
