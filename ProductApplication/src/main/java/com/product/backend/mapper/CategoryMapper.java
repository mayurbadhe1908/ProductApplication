package com.product.backend.mapper;

import com.product.backend.dto.CategoryDTO;
import com.product.backend.entity.Category;

public class CategoryMapper {
	
	public static Category toCategoryEntity(CategoryDTO categoryDTO) {
		
		Category category = new Category();
		category.setCategoryName(categoryDTO.getCategoryName());
		return category;	
	}
	
	public static CategoryDTO toCategoryDTO(Category category) {
		if (category == null) {
			return null;
		}
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(category.getId());
		categoryDTO.setCategoryName(category.getCategoryName());
		categoryDTO.setProducts(category.getProducts()
				.stream().
				map(ProductMapper::toProductDTO).toList());
		return categoryDTO;
		
	}

}
