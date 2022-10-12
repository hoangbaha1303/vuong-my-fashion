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
@Table(name="tb_PostComment")

public class Tb_PostComment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Comment_ID")
	private int commentID;

	@Column(name="CreateBy")
	private int createBy;

	@Column(name="CreateDate")
	private Timestamp createDate;

	@Column(name="Detail", columnDefinition = "nvarchar(500)")
	private String detail;

	@Column(name="Email", columnDefinition = "nvarchar(150)")
	private String email;

	@Column(name="Name", columnDefinition = "nvarchar(150)")
	private String name;

	@Column(name="PostID")
	private int postID;

	@Column(name="Status")
	private boolean status;

	@Column(name="UpdateBy")
	private int updateBy;

	@Column(name="UpdateDate")
	private Timestamp updateDate;
}