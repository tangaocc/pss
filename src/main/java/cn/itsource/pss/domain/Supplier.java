package cn.itsource.pss.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 供应商
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "supplier")
public class Supplier extends BaseDomain {
	private String name;

	public Supplier() {
	}
	public Supplier(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
