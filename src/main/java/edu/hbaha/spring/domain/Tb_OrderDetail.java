package edu.hbaha.spring.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_OrderDetail")
public class Tb_OrderDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Tb_OrderDetailPK id;

	@Column(name="Price")
	private BigDecimal price;

	@Column(name="ProductName", columnDefinition = "nvarchar(50)")
	private String productName;

	@Column(name="Quantity")
	private int quantity;
}