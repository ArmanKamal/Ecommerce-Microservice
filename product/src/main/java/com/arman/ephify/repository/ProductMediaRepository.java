package com.arman.ephify.repository;

import com.arman.ephify.model.ProductMedia;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMediaRepository extends MongoRepository<ProductMedia, String>{
}

