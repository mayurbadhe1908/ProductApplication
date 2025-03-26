package com.product.backend.mapper;

import com.product.backend.dto.ProductDTO;
import com.product.backend.entity.Category;
import com.product.backend.entity.Product;

public class ProductMapper {
	
	public static ProductDTO toProductDTO(Product product) {
		
		return new ProductDTO(
				product.getId(),
				product.getProductName(),
				product.getProductDescription(),
				product.getPrice(),
				product.getCategory().getId()
		);
	}
	
	public static Product toProductEntity(ProductDTO productDTO, Category category) {
		Product product = new Product();
		product.setProductName(productDTO.getProductName());
		product.setProductDescription(productDTO.getProductDescription());
		product.setPrice(productDTO.getPrice());
		product.setCategory(category);
		
		return product;
	}

}
