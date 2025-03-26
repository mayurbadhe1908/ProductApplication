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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.backend.dto.ProductDTO;
import com.product.backend.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
		name = "Product REST API CRUD operations",
		description = "CREATE, READ, UPDATE and DELETE operations for Product REST API"
		)
@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	// add new product
	@Operation(
			summary = "Add New Product",
			description = "REST API To Add New Product"
			)
	@ApiResponse(
			responseCode = "201",
			description = "Product created successfully"
			)
	@PreAuthorize("hasAuthority('ROLE_SELLER')")
	@PostMapping("/add")
	public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
		
		ProductDTO createdProductDTO = productService.createProduct(productDTO);
		return new ResponseEntity<>(createdProductDTO,HttpStatus.CREATED);
		
	}
	
	// get all products
	@Operation(
			summary = "Get All Products",
			description = "REST API To Fetch All Products"
			)
	@GetMapping("/all")
	public List<ProductDTO> getAllProducts(){
		return productService.getAllProducts();
	}
	
	// get product by id
	@Operation(
			summary = "Get Product By Id",
			description = "REST API To Fetch Product By Id"
			)
	@GetMapping("/{id}")
	public ProductDTO getProductById(@PathVariable Long id) {
		return productService.getProductByid(id);
	}
	
	// delete product
	@Operation(
			summary = "Delete Product By Id",
			description = "REST API To Delete Product By Id"
			)
	@PreAuthorize("hasAuthority('ROLE_SELLER')")
	@DeleteMapping("/{id}")
	public String deleteProductById(@PathVariable Long id) {
		productService.deleteProductById(id);
		return "Product deleted successfull";
	}
			
	// update product
	@Operation(
			summary = "Update Product By Id",
			description = "REST API To Update Product By Id"
			)
	@PreAuthorize("hasAuthority('ROLE_SELLER')")
	@PutMapping("/{id}")
	public ProductDTO updateProduct(@PathVariable Long id,@RequestBody ProductDTO productDTO) {
		return productService.updateProduct(id, productDTO);
	}
	
	
	
}
