package cn.itsource.pss.query;

import org.apache.commons.lang.StringUtils;

import cn.itsource.pss.domain.${upperCase};

/**
 * 接收前端传递过来的参数
 * 
 * @author Administrator
 *
 */
public class ${upperCase}Query extends BaseQuery {

	private String name;

	public ${upperCase}Query() {
		super(${upperCase}.class);
	}

	/**
	 * 添加条件
	 */
	@Override
	public void addCondition() {
		if (StringUtils.isNotBlank(name)) {
			super.addWhere("o.name like ?", "%" + name + "%");
		}
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}


}
