package cn.itsource.pss.domain;

import javax.persistence.Table;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "systemdictionarydetail")
public class SystemDictionaryDetail extends BaseDomain {
	private String name;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "types_id")
	private SystemDictionaryType systemDictionaryType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SystemDictionaryType getSystemDictionaryType() {
		return systemDictionaryType;
	}

	public void setSystemDictionaryType(SystemDictionaryType systemDictionaryType) {
		this.systemDictionaryType = systemDictionaryType;
	}

}
