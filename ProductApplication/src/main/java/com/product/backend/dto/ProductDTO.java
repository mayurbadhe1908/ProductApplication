package com.product.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
		name = "Product",
		description = "It holds product information"
		)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	
	private Long id;
	private String productName;
	private String productDescription;
	private double price;
	
	private Long categoryId;

}
