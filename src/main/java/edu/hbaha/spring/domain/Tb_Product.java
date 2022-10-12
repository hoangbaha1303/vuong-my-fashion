package edu.hbaha.spring.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_Product")
public class Tb_Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Product_ID")
	private Integer productID;

	@Column(name="Brand_ID")
	private Integer brandID;

	@Column(name="Cate_ID")
	private Integer cateID;

	@Column(name="CreateBy")
	private int createBy;

	@Column(name="CreateDate")
	private Date createDate;

	@Column(name="Description",columnDefinition = "nvarchar(500)")
	private String description;

	@Column(name="Detail",columnDefinition = "ntext")
	private String detail;

	@Column(name="Hot")
	private Date hot;

	@Column(name="Image",columnDefinition = "nvarchar(250)")
	private String image;

	//Xml tạm thời để string
	@Column(name="ListImages",columnDefinition = "xml")
	private String listImages;

	@Column(name="MetaDescriptions",columnDefinition = "nvarchar(250)")
	private String metaDescriptions;

	@Column(name="MetaKeywords",columnDefinition = "nvarchar(250)")
	private String metaKeywords;

	@Column(name="Name",columnDefinition = "nvarchar(250)")
	private String name;

	@Column(name="Price")
	private BigDecimal price;

	@Column(name="PromotionPrice")
	private BigDecimal promotionPrice;

	@Column(name="Quantity")
	private int quantity;

	@Column(name="SeoTitle",columnDefinition = "nvarchar(250)")
	private String seoTitle;

	@Column(name="Status")
	private boolean status;

	@Column(name="Supplier_ID")
	private Integer supplierID;

	@Column(name="UpdateBy")
	private int updateBy;

	@Column(name="UpdateDate")
	private Date updateDate;

	@Column(name="VAT")
	private boolean vat;
	
	@Column(name="ViewCount")
	private int viewCount;

	@Column(name="Warranty")
	private int warranty;

}