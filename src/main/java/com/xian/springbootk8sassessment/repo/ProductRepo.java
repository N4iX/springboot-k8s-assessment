package com.xian.springbootk8sassessment.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xian.springbootk8sassessment.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

}
