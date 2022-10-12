package edu.hbaha.spring.models;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedBackDto {
	private Integer id;
	private String username;
	@NotEmpty
	@Length(max = 200, message = "Tiêu đề phản hồi không quá 200 ký tự")
	private String subject;
	@NotEmpty
	@Length(max = 1000, message = "Nội dung phản hồi không quá 1000 ký tự")
	private String content;
	private Date createDate = new Date();
}
