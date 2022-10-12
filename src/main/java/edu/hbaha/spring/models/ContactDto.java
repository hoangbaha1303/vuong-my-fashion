package edu.hbaha.spring.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContactDto {
	private Integer id;
	@NotEmpty
	private String address;
	@NotEmpty
	private String map;
	@NotEmpty(message = "Số Điện Thoại không được để trống")
	@Length(max = 10, min = 10, message = "Số điện thoại chỉ được đúng 10 số")
	private String phone;
	@NotEmpty
	private String workTime;
	@Email(message = "Định dạng Email không đúng")
	@NotEmpty(message = "Email không được để trống")
	private String email;
}
