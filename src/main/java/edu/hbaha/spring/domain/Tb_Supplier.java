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
@Table(name="tb_Supplier")
public class Tb_Supplier implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;

	@Column(name="Address", columnDefinition = "nvarchar(500)")
	private String address;

	@Column(name="Email", columnDefinition = "nvarchar(50)")
	private String email;

	@Column(name="Name", columnDefinition = "nvarchar(50)")
	private String name;

	@Column(name="Phone", columnDefinition = "nvarchar(50)")
	private String phone;

}