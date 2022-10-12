package edu.hbaha.spring.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto implements Serializable{
	private Integer productID;
	private Integer brandID;
	private Integer cateID;
	@NotEmpty
	private String description;
	@NotEmpty
	private String detail;
	private String image;
	private MultipartFile imageFile;
	@NotEmpty
	private String name;
	@NotNull
	private BigDecimal price;
	private BigDecimal promotionPrice;
	@NotNull
	private int quantity;
	private boolean status=true;
	private int supplierID;
	private int viewCount;
	@NotNull
	private int warranty;
	private Boolean isEdit=false;
}
