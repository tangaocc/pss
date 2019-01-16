package cn.itsource.pss.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itsource.pss.domain.Supplier;
import cn.itsource.pss.repository.SupplierRepository;
import cn.itsource.pss.service.ISupplierService;

@Service
public class SupplierServiceImpl extends BaseServiceImpl<Supplier, Long> implements ISupplierService {
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	@Override
	public boolean checkName(Long id,String name) {
		
		String jpql = "select count(o) from Supplier o where o.name=?";
		if(id == null){//证明是新增
			List list = supplierRepository.findByJpql(jpql, name);
			Long count = (Long)list.get(0);
			return count == 0;
			
		}else{//证明是修改
			//通过id查询到对应的supplier对象
			Supplier supplier = super.queryOne(id);
			if(name.equals(supplier.getName())){
				return true;// 应该直接放行    true:代表是没重复， false：代表重复了
			}else{
				List list = supplierRepository.findByJpql(jpql, name);
				Long count = (Long)list.get(0);
				return count == 0;
			}
		}
	}
}
