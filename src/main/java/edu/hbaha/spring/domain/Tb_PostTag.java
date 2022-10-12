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
@Table(name="tb_PostTag")
public class Tb_PostTag implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Tb_PostTagPK id;

}