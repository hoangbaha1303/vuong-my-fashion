package edu.hbaha.spring.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_Tag")
public class Tb_Tag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID", columnDefinition = "nvarchar(50)")
	private String id;

	@Column(name="Name", columnDefinition = "nvarchar(500)")
	private String name;
}