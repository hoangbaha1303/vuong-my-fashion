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
@Table(name="tb_Footer")
public class Tb_Footer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	private int id;

	@Column(name="CreateBy")
	private int createBy;

	@Column(name="CreateDate")
	private Timestamp createDate;

	@Column(name="Detail", columnDefinition = "ntext")
	private String detail;

	@Column(name="Name", columnDefinition = "nvarchar(50)")
	private String name;

	@Column(name="Status")
	private boolean status;


}