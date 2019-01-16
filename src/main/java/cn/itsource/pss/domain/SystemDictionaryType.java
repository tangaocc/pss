package cn.itsource.pss.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 * 数据字典表
 * @author Administrator
 *
 */
//eclipse快捷键变为小写   ctrl+shift+y
//eclipse快捷键变为大写   ctrl+shift+x
@Entity
@Table(name = "systemdictionarytype")
public class SystemDictionaryType extends BaseDomain {
	
	//品牌
	public static final String BRAND = "productBrand";
	//单位
	public static final String UNIT = "productUnit";
	
	// 数据字典的编号(必须是独一无二的，并且是不能修改的)
	@JoinColumn(updatable = false)
	private String sn;
	// 数据字典类型的名字
	private String name;

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
