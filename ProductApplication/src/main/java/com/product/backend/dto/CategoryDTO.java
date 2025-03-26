package com.product.backend.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
		name = "Category",
		description = "It holds category information along with products"
		)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
	
	private Long id;
	private String categoryName;
	
	private List<ProductDTO> products;

}
