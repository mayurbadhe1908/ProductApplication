package com.product.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.backend.dto.CategoryDTO;
import com.product.backend.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
		name = "Category REST API CRUD operations",
		description = "CREATE, READ, UPDATE and DELETE operations for Category REST API"
		)
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	// Add new category
	@Operation(
			summary = "Add New Category",
			description = "REST API To Add New Category"
			)
	@ApiResponse(
			responseCode = "201",
			description = "Category created successfully"
			)
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PostMapping("/add")
	public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO) {
		
			CategoryDTO savedCategory = categoryService.createCategory(categoryDTO);
		    return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
	}
	
	// get all categories
	@Operation(
			summary = "Get All Categories",
			description = "REST API To Fetch All Categories"
			)
	@GetMapping("/all")
	public List<CategoryDTO> getAllCategories(){
		return categoryService.getAllCategories();
	}
	
	// get category by id
	@Operation(
			summary = "Get Category By Id",
			description = "REST API To Fetch Category By Id"
			)
	@GetMapping("/{id}")
	public CategoryDTO getCategoryById(@PathVariable long id) {
		return categoryService.getCategoryById(id);
	}
	
	// delete category
	@Operation(
			summary = "Delete Category By Id",
			description = "REST API To Delete Category By Id"
			)
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	public String deleteCategory(@PathVariable Long id) {
		categoryService.deleteCategory(id);
		
		return "category deleted successfully"; 
	}
	
//	// update product by id
//	public CategoryDTO updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
//		
//	}
	
	
	
}
