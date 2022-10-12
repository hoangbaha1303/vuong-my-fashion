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
@Table(name="tb_Menu")

public class Tb_Menu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;

	@Column(name="Description", columnDefinition = "nvarchar(500)")
	private String description;

	@Column(name="Link", columnDefinition = "nvarchar(250)")
	private String link;

	@Column(name="Name", columnDefinition = "nvarchar(250)")
	private String name;

	@Column(name="Position")
	private int position;

	@Column(name="Status")
	private boolean status;

	@Column(name="Target", columnDefinition = "nvarchar(50)")
	private String target;

}