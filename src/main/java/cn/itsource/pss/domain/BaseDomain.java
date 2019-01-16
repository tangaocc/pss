package cn.itsource.pss.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/*@MappedSuperclass 放弃生成对应的表*/
@MappedSuperclass
public class BaseDomain {
	@Id
	@GeneratedValue
	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
