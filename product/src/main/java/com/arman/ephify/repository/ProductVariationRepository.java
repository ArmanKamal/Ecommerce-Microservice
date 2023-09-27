package com.arman.ephify.repository;

import com.arman.ephify.model.ProductVariation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductVariationRepository extends MongoRepository<ProductVariation, String>{
}

