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
@Table(name="tb_Config")
public class Tb_Config implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;

	@Column(name="Name", columnDefinition = "nvarchar(50)")
	private String name;

	@Column(name="Status")
	private boolean status;

	@Column(name="Type", columnDefinition = "nvarchar(50)")
	private String type;

	@Column(name="Value", columnDefinition = "nvarchar(50)")
	private String value;

}