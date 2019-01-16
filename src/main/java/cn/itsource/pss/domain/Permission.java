package cn.itsource.pss.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "permission")
public class Permission extends BaseDomain {
	private String name;
	private String method;
	
	
	public Permission() {
	}
	public Permission(Long id) {
		this.id = id;
	}
	
	
	@Column(name = "descs")
	private String desc;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "Permission [name=" + name + ", method=" + method + ", desc=" + desc + "]";
	}

}
