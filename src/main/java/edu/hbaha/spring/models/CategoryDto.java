package edu.hbaha.spring.models;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto implements Serializable{
	private Integer cateID;
	@NotEmpty
	private String name;
	@NotNull
	private Integer sort;
	private Boolean isEdit = false;
}
