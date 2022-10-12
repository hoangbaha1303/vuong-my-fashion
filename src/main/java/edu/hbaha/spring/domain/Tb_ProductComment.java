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
@Table(name="tb_ProductComment")
public class Tb_ProductComment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Comment_ID")
	private Integer commentId;

	@Column(name="CreateBy")
	private Integer createBy;

	@Column(name="CreateDate")
	private Date createDate;

	@Column(name="Detail", columnDefinition = "nvarchar(500)")
	private String detail;

	@Column(name="Email", columnDefinition = "nvarchar(150)")
	private String email;

	@Column(name="Name", columnDefinition = "nvarchar(150)")
	private String name;

	@Column(name="Product_ID")
	private Integer productID;

	@Column(name="Status")
	private boolean status = true;
	
	@Column(name="Vote")
	private byte vote;
	
	@Column(name="UpdateBy")
	private Integer updateBy;

	@Column(name="UpdateDate")
	private Date updateDate;
}