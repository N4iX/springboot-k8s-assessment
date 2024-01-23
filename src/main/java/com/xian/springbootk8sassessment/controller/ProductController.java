package com.xian.springbootk8sassessment.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xian.springbootk8sassessment.model.Product;
import com.xian.springbootk8sassessment.repo.ProductRepo;

@RestController
public class ProductController {

	@Autowired
	private ProductRepo productRepo;
	
	// Get all products
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts() {
		try {
			// get the product list and return array in response, if no product then will return empty array
			return new ResponseEntity<>(productRepo.findAll(), HttpStatus.OK);
		} catch (Exception ex) { // exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Create a new product
	@PostMapping("/products")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		try {
			// save the product data and return the saved product object in response
			return new ResponseEntity<>(productRepo.save(product), HttpStatus.CREATED);
		} catch (Exception ex) { // exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Get a product by ID
	@GetMapping("/products/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
		try {
			// find the product by ID
			Optional<Product> product = productRepo.findById(productId);
			
			if (product.isPresent()) {
				 // product exists, return product object in response
				return new ResponseEntity<>(product.get(), HttpStatus.OK);
			} else {
				// product does not exist, return 404
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception ex) { // exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Update a product by ID
	@PutMapping("/products/{productId}")
	public ResponseEntity<Product> updateProductById(@PathVariable Long productId, @RequestBody Product newProduct) {
		try {
			// find the product by ID
			Optional<Product> oldProduct = productRepo.findById(productId);
			
			if (oldProduct.isPresent()) {
				// product exists, set new data value
				Product product = oldProduct.get();
				product.setName(newProduct.getName()); // set name
				product.setDescription(newProduct.getDescription()); // set description
				
				// update the product in database and return the updated product object in response
				return new ResponseEntity<>(productRepo.save(product), HttpStatus.OK);
			} else {
				// product does not exist, return 404
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception ex) { // exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Delete a product by ID
	@DeleteMapping("/products/{productId}")
	public ResponseEntity<HttpStatus> deleteProductById(@PathVariable Long productId) {
		try {
			if (productRepo.existsById(productId)) {
				// product exists, delete the product and return 204
				productRepo.deleteById(productId);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				// product does not exist, return 404
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception ex) { // exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
