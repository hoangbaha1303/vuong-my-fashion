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
@Table(name="tb_User")
public class Tb_User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;

	@Column(name="Address", columnDefinition = "nvarchar(250)")
	private String address;

	@Column(name="CreateBy")
	private int createBy;

	@Column(name="CreateDate")
	private Date createDate;

	@Column(name="Email", columnDefinition = "nvarchar(50)")
	private String email;

	@Column(name="Group_ID")
	private Integer groupID;

	@Column(name="Image", columnDefinition = "nvarchar(50)")
	private String image;

	@Column(name="Name", columnDefinition = "nvarchar(250)")
	private String name;

	@Column(name="Password", columnDefinition = "varchar(60)")
	private String password;

	@Column(name="Phone", columnDefinition = "nvarchar(50)")
	private String phone;

	@Column(name="Status")
	private boolean status;

	@Column(name="UpdateBy")
	private int updateBy;

	@Column(name="UpdateDate")
	private Date updateDate;

	@Column(name="UserName", columnDefinition = "nvarchar(50)")
	private String userName;
}