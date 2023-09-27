package com.arman.ephify.repository;

import com.arman.ephify.model.ProductAttribute;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductAttributeRepository extends MongoRepository<ProductAttribute, String>{
}

