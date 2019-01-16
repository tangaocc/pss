package cn.itsource.pss.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 产品类型
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "producttype")
public class ProductType extends BaseDomain {
	// 产品类型名
	private String name;
	// 产品类型的描述
	@Column(name = "descs")
	private String desc;

	// 父产品类型
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private ProductType parent;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public ProductType getParent() {
		return parent;
	}

	public void setParent(ProductType parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return "ProductType [name=" + name + ", desc=" + desc + "]";
	}

}
