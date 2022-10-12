package edu.hbaha.spring.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_FeedBack")
public class Tb_FeedBack implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="UserName")
	private String username;
	
	@Column(name="Subject", columnDefinition = "nvarchar(200)")
	private String subject;
	
	@Column(name="Content", columnDefinition = "nvarchar(1000)")
	private String content;
	
	@Column(name="CreateDate")
	private Date createDate;
	
	@Column(name="Status")
	private boolean status = false;
}