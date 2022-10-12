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
@Table(name="tb_InvoiceDetail")
public class Tb_InvoiceDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Tb_InvoiceDetailPK id;

	@Column(name="ProductName", columnDefinition = "nvarchar(250)")
	private String productName;

	@Column(name="Quantity")
	private BigDecimal quantity;

}