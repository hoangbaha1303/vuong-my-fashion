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
@Table(name="tb_Slide")
public class Tb_Slide implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;

	@Column(name="Image", columnDefinition = "nvarchar(50)")
	private String image;

	@Column(name="Link", columnDefinition = "nvarchar(50)")
	private String link;

	@Column(name="Name", columnDefinition = "nvarchar(50)")
	private String name;

	@Column(name="Sort")
	private int sort;

	@Column(name="Status")
	private boolean status;
}