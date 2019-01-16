package cn.itsource.pss.service;

import java.util.List;

import cn.itsource.pss.domain.SystemDictionaryDetail;

public interface ISystemDictionaryDetailService extends IBaseService<SystemDictionaryDetail, Long> {
	/**
	 * 判断名字是否重复
	 * @param id  主键id
	 * @param name	需要判断的姓名
	 * @return  false:表示重复， true：表示不重复
	 * 	
	 */
	public boolean checkName(Long id,String name);
	
	
	/**
	 * 查询数据字典明细，根据数据字典类型编号
	 * @param sn
	 * @return
	 */
	public List<SystemDictionaryDetail>  findSystemDictionaryDetailByTypeSn(String sn);
}
