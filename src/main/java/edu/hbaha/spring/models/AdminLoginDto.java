package edu.hbaha.spring.models;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminLoginDto implements Serializable {
	@NotEmpty
	private String userName;
	@NotEmpty
	private String password;
	private Boolean rememberMe = false;
}
