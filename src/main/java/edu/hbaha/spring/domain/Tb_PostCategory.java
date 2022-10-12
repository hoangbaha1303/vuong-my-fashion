package edu.hbaha.spring.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_PostCategory")

public class Tb_PostCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Cate_ID")
	private int cateID;

	@Column(name="CreateBy")
	private int createBy;

	@Column(name="CreateDate")
	private Timestamp createDate;

	@Column(name="MetaDescriptions", columnDefinition = "nvarchar(250)")
	private String metaDescriptions;

	@Column(name="MetaKeywords", columnDefinition = "nvarchar(250)")
	private String metaKeywords;

	@Column(name="Name", columnDefinition = "nvarchar(250)")
	private String name;

	@Column(name="ParrentID")
	private int parrentID;

	@Column(name="SeoTitle", columnDefinition = "nvarchar(250)")
	private String seoTitle;

	@Column(name="Sort")
	private int sort;

	@Column(name="Status")
	private boolean status;

	@Column(name="UpdateBy")
	private int updateBy;

	@Column(name="UpdateDate")
	private Timestamp updateDate;
}