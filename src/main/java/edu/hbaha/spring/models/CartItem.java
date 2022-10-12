package edu.hbaha.spring.models;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
	private Integer productID;
	private Integer cateID;
	private String name;
	private int quantity;
	private String image;
	private BigDecimal price;
	private BigDecimal promotionPrice;
}
