package cn.itsource.pss.service;

import cn.itsource.pss.domain.${upperCase};

public interface I${upperCase}Service extends IBaseService<${upperCase}, Long> {
	/**
	 * 判断名字是否重复
	 * @param id  主键id
	 * @param name	需要判断的姓名
	 * @return  false:表示重复， true：表示不重复
	 * 	
	 */
	public boolean checkName(Long id,String name);
}
