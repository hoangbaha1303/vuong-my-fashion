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
@Table(name="tb_About")
public class Tb_About implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID", columnDefinition = "nvarchar(50)")
	private int id;

	@Column(name="CreateBy")
	private int createBy;

	@Column(name="CreateDate")
	private Timestamp createDate;

	@Column(name="Description", columnDefinition = "nvarchar(250)")
	private String description;

	@Column(name="Detail", columnDefinition = "ntext")
	private String detail;

	@Column(name="MetaDescriptions", columnDefinition = "nvarchar(250)")
	private String metaDescriptions;

	@Column(name="MetaKeywords", columnDefinition = "nvarchar(250)")
	private String metaKeywords;

	@Column(name="Name", columnDefinition = "nvarchar(250)")
	private String name;

	@Column(name="Status")
	private boolean status;

	@Column(name="UpdateBy")
	private int updateBy;

	@Column(name="UpdateDate")
	private Timestamp updateDate;

}