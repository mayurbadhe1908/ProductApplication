package com.product.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.backend.dto.CategoryDTO;
import com.product.backend.entity.Category;
import com.product.backend.exceptions.CategoryAlreadyExistsException;
import com.product.backend.exceptions.CategoryNotFoundException;
import com.product.backend.mapper.CategoryMapper;
import com.product.backend.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	//create category
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {
		Optional<Category> optionalCategory = categoryRepository
					.findByCategoryName(categoryDTO.getCategoryName());
		if(optionalCategory.isPresent()) {
			throw new CategoryAlreadyExistsException("Category " +
						categoryDTO.getCategoryName() + " already exist");
		}
		Category category = CategoryMapper.toCategoryEntity(categoryDTO);
		category = categoryRepository.save(category);
		
		return CategoryMapper.toCategoryDTO(category);
	}
	
	// get all categories
	public List<CategoryDTO> getAllCategories(){
		
		return categoryRepository.findAll().stream().map(CategoryMapper::toCategoryDTO).toList();
	}
	
	// get category by id
	public CategoryDTO getCategoryById(Long id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new CategoryNotFoundException("category not found"));
		return CategoryMapper.toCategoryDTO(category);
	}
	
	// delete category
	public void deleteCategory(Long id) {
		categoryRepository.deleteById(id);
	}
	
//	// update category
//	public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
//		Category category = categoryRepository.findById(id)
//				.orElseThrow(() -> new RuntimeException("Category Not found"));
//		Category updCategory = category.setCategoryName(categoryDTO.getCategoryName().);;
//		categoryRepository.save(updCategory);
//		return CategoryMapper.toCategoryDTO(updCategory);
//	}

}
