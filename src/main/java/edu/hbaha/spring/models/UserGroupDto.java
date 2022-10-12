package edu.hbaha.spring.models;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGroupDto implements Serializable {
	private Integer id;
	@NotEmpty
	private String name;
	private Boolean isEdit = false;
}
