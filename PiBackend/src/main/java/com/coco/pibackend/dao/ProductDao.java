package com.coco.pibackend.dao;

import com.coco.pibackend.Entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends CrudRepository<Product, Integer>{

	public List<Product> findAll(Pageable pageable);
	
	public List<Product>  findByNameContainingIgnoreCaseOrBrandContainingIgnoreCase(
			String key1, String key2, Pageable pageable);
	

}
