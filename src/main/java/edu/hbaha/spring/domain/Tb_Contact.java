package edu.hbaha.spring.domain;

import java.io.Serializable;


import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_Contact")
public class Tb_Contact implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;

	@Column(name="Phone", columnDefinition = "varchar(20)")
	private String phone;

	@Column(name="Address", columnDefinition = "nvarchar(500)")
	private String address;
	
	@Column(name="WorkTime", columnDefinition = "nvarchar(200)")
	private String workTime;
	
	@Column(name="Map", columnDefinition = "ntext")
	private String map;
	
	@Column(name="Email", columnDefinition = "varchar(100)")
	private String email;
	
}