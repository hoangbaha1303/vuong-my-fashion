package edu.hbaha.spring.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDto {
	private Integer id;
	@NotEmpty
	private String address;
	@NotEmpty(message = "Email không được để trống")
	@Email(message = "Định dạng email không đúng")
	private String email;
	@NotEmpty
	private String name;
	@NotEmpty
	private String phone;
	private Boolean isEdit= false;
}
