package cn.itsource.pss.service;

import java.util.List;

import cn.itsource.pss.domain.ProductType;

public interface IProductTypeService extends IBaseService<ProductType, Long> {
	/**
	 * 判断名字是否重复
	 * @param id  主键id
	 * @param name	需要判断的姓名
	 * @return  false:表示重复， true：表示不重复
	 * 	
	 */
	public boolean checkName(Long id,String name);
	
	/**
	 * 查询一级产品类型
	 * @return
	 */
	public List<ProductType> findOneLevelType();
	
	
	/**
	 * 查询二级产品类型根据父id
	 * @param pid
	 * @return
	 */
	public List<ProductType> findTwoLevelByPid(Long pid);
}
