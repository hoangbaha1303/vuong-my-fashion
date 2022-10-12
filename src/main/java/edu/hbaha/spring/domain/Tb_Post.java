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
@Table(name="tb_Post")
public class Tb_Post implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Post_ID")
	private int postID;

	@Column(name="Cate_ID")
	private int cateID;

	@Column(name="CreateBy")
	private int createBy;

	@Column(name="CreateDate")
	private Timestamp createDate;

	@Column(name="Description", columnDefinition = "nvarchar(500)")
	private String description;

	@Column(name="Detail", columnDefinition = "ntext")
	private String detail;

	@Column(name="Image", columnDefinition = "nvarchar(250)")
	private String image;

	@Column(name="MetaDescriptions", columnDefinition = "nvarchar(250)")
	private String metaDescriptions;

	@Column(name="MetaKeywords", columnDefinition = "nvarchar(250)")
	private String metaKeywords;

	@Column(name="Name", columnDefinition = "nvarchar(250)")
	private String name;

	@Column(name="SeoTitle", columnDefinition = "nvarchar(250)")
	private String seoTitle;

	@Column(name="Status")
	private boolean status;

	@Column(name="Tags", columnDefinition = "nvarchar(250)")
	private String tags;

	@Column(name="UpdateBy")
	private int updateBy;

	@Column(name="UpdateDate")
	private Timestamp updateDate;

	@Column(name="ViewCount")
	private int viewCount;
}