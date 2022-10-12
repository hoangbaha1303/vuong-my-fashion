package edu.hbaha.spring.models;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordDto {
	@NotEmpty(message = "Mật khẩu cũ không được để trống")
	@Length(min = 8, message = "Mật khẩu không được ngắn hơn 8 ký tự")
	private String oldPassword;
	@NotEmpty(message = "Mật khẩu mới không được để trống")
	@Length(min = 8, message = "Mật khẩu không được ngắn hơn 8 ký tự")
	private String newPassword;
	@NotEmpty(message = "Xác nhận mật khẩu không được để trống")
	@Length(min = 8, message = "Mật khẩu không được ngắn hơn 8 ký tự")
	private String confirmPassword;
}
