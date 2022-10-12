package edu.hbaha.spring.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
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
	
	@NotEmpty(message = "Mật khẩu không được để trống")
	@Length(min = 8, message = "Mật khẩu không được ngắn hơn 8 ký tự")
	private String password;
	
	@NotEmpty(message = "Số điện thoại không được để trống")
	@Length(min = 10, max = 10, message = "Số điện thoại chỉ gồm 10 số")
	private String phone;
	
	private boolean status = true;
	
	@NotEmpty
	private String userName;
	private Boolean isEdit=false;
}
