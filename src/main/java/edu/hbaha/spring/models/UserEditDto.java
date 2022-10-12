package edu.hbaha.spring.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEditDto {
	private Integer id;
	
	@NotEmpty
	private String address;
	
	@NotEmpty(message = "Email không được để trống")
	@Email(message = "Không đúng định dạng Email")
	private String email;
	
	private Integer groupID = 2;
	
	private String image;
	
	@NotEmpty
	private String name;
	
	@NotEmpty(message = "Số điện thoại không được để trống")
	@Length(min = 10, max = 10, message = "Số điện thoại chỉ gồm 10 số")
	private String phone;
	
	private boolean status = true;
	private Boolean isEdit=false;
}
