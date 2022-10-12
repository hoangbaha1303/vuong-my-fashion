package edu.hbaha.spring.models;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCommentDto {
	private Integer commentId;
	private int createBy;
	private Date createDate;
	private String detail;
	private String email;
	private String name;
	private Integer productID;
	private boolean status;
	private int updateBy;
	private Date updateDate;
}
