package com.product.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.backend.dto.ProductDTO;
import com.product.backend.entity.Category;
import com.product.backend.entity.Product;
import com.product.backend.exceptions.CategoryNotFoundException;
import com.product.backend.exceptions.ProductNotFoundException;
import com.product.backend.mapper.ProductMapper;
import com.product.backend.repository.CategoryRepository;
import com.product.backend.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	// add product
	public ProductDTO createProduct(ProductDTO productDTO) {
		Category category = categoryRepository.findById(productDTO.getCategoryId())
					.orElseThrow(() -> new CategoryNotFoundException("category id " +productDTO.getCategoryId()+ " not found"));
		Product product = ProductMapper.toProductEntity(productDTO, category);
	    product = productRepository.save(product);
	    
	    return ProductMapper.toProductDTO(product);
	}
	
	// get all products
	public List<ProductDTO> getAllProducts(){
		
		return productRepository.findAll().stream().map(ProductMapper::toProductDTO).toList();
	}
	
	// get product by id
	public ProductDTO getProductByid(Long id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product with "+id+" Not Found"));
		
		return ProductMapper.toProductDTO(product);
	}
	
	// delete product by id
	public void deleteProductById(Long id) {
		productRepository.deleteById(id);
	}
	
	//update product
	public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product Not Found"));	
		Category category = categoryRepository.findById(productDTO.getCategoryId())
		        .orElseThrow(() -> new CategoryNotFoundException("Category id "+productDTO.getCategoryId()+" not found"));
		product.setProductName(productDTO.getProductName());
		product.setProductDescription(productDTO.getProductDescription());
		product.setPrice(productDTO.getPrice());
		product.setCategory(category);
		productRepository.save(product);
		
		return ProductMapper.toProductDTO(product);
	}

}
