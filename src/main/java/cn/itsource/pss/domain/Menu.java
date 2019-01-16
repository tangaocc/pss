package cn.itsource.pss.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "menu")
public class Menu extends BaseDomain {
	private String name;
	private String url;
	private String icon;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")   
	private Menu parent;
	@Transient//告诉jpa放弃对该字段进行管理
	private List<Menu> children  = new ArrayList<Menu>();  
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Menu getParent() {
		return parent;
	}
	public void setParent(Menu parent) {
		this.parent = parent;
	}
	public List<Menu> getChildren() {
		return children;
	}
	public void setChildren(List<Menu> children) {
		this.children = children;
	}
	@Override
	public String toString() {
		return "Menu [name=" + name + ", url=" + url + ", icon=" + icon + "]";
	}
	
	
	
}
