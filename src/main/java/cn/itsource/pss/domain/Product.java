package cn.itsource.pss.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="product")
public class Product extends BaseDomain {
	//产品名
	private String name;
	//产品颜色
	private String color;
	//产品大图
	private String pic;
	//产品小图
	private String smallPic;
	//成本价
	private BigDecimal costPrice;
	//零售价
	private BigDecimal salePrice;//参考价
	
	//optional=false 该字段是不能为空的   产品类型
	@ManyToOne(fetch = FetchType.LAZY,optional=false )
	@JoinColumn(name="types_id")
	private ProductType type;
	
	//单位id
	@ManyToOne(fetch = FetchType.LAZY,optional=false )
	@JoinColumn(name="unit_id")
	private SystemDictionaryDetail unit; 
	
	//品牌id
	@ManyToOne(fetch = FetchType.LAZY,optional=false )
	@JoinColumn(name="brand_id")
	private SystemDictionaryDetail brand;

	public Product() {
	}
	public Product(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getSmallPic() {
		return smallPic;
	}

	public void setSmallPic(String smallPic) {
		this.smallPic = smallPic;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public ProductType getType() {
		return type;
	}

	public void setType(ProductType type) {
		this.type = type;
	}

	public SystemDictionaryDetail getUnit() {
		return unit;
	}

	public void setUnit(SystemDictionaryDetail unit) {
		this.unit = unit;
	}

	public SystemDictionaryDetail getBrand() {
		return brand;
	}

	public void setBrand(SystemDictionaryDetail brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", color=" + color + ", pic=" + pic + ", smallPic=" + smallPic + ", costPrice="
				+ costPrice + ", salePrice=" + salePrice + "]";
	} 
	
	
	
}
