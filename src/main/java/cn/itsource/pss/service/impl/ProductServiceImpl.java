package cn.itsource.pss.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itsource.pss.domain.Product;
import cn.itsource.pss.repository.ProductRepository;
import cn.itsource.pss.service.IProductService;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product, Long> implements IProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public boolean checkName(Long id,String name) {
		
		String jpql = "select count(o) from Product o where o.name=?";
		if(id == null){//证明是新增
			List list = productRepository.findByJpql(jpql, name);
			Long count = (Long)list.get(0);
			return count == 0;
			
		}else{//证明是修改
			//通过id查询到对应的product对象
			Product product = super.queryOne(id);
			if(name.equals(product.getName())){
				return true;// 应该直接放行    true:代表是没重复， false：代表重复了
			}else{
				List list = productRepository.findByJpql(jpql, name);
				Long count = (Long)list.get(0);
				return count == 0;
			}
		}
	}
}
