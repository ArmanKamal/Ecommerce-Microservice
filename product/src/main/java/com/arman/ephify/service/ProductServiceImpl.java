package com.arman.ephify.service;

import com.arman.ephify.model.*;
import com.arman.ephify.dto.ProductRequest;
import com.arman.ephify.dto.ProductResponse;
import com.arman.ephify.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class ProductServiceImpl implements ProductService {

    public final ProductRepository productRepository;
    public final CategoryRepository categoryRepository;
    public final SupplierRepository supplierRepository;
    public final ProductVariationRepository productVariationRepository;
    public final ProductAttributeRepository productAttributeRepository;
    public final ProductMediaRepository productMediaRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, SupplierRepository supplierRepository, ProductVariationRepository productVariationRepository, ProductAttributeRepository productAttributeRepository, ProductMediaRepository productMediaRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.supplierRepository = supplierRepository;
        this.productVariationRepository = productVariationRepository;
        this.productAttributeRepository = productAttributeRepository;
        this.productMediaRepository = productMediaRepository;
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) throws Exception {
        try {

// Sample Categories
            Category parentCategory1 = new Category();
            parentCategory1.setName("Clothing");
            parentCategory1.setDescription("Category for clothing items");
            parentCategory1.setUrlSlug("clothing");
            categoryRepository.save(parentCategory1);

            Category parentCategory2 = new Category();
            parentCategory2.setName("Electronics");
            parentCategory2.setDescription("Category for electronic gadgets");
            parentCategory2.setUrlSlug("electronics");
            categoryRepository.save(parentCategory2);

            Category category1 = new Category();
            category1.setName("T-Shirts");
            category1.setDescription("Category for T-Shirts");
            category1.setUrlSlug("t-shirts");
            category1.setParentCategory(parentCategory1);
            categoryRepository.save(category1);

            Category category2 = new Category();
            category2.setName("Jeans");
            category2.setDescription("Category for Jeans");
            category2.setUrlSlug("jeans");
            category2.setParentCategory(parentCategory1);
            categoryRepository.save(category2);

            Category category3 = new Category();
            category3.setName("Smartphones");
            category3.setDescription("Category for smartphones");
            category3.setUrlSlug("smartphones");
            category3.setParentCategory(parentCategory2);
            categoryRepository.save(category3);

// Sample Suppliers
            Supplier supplier1 = new Supplier();
            supplier1.setName("Supplier A");
            supplier1.setContactAddress("123 Main St, City, Country");
            supplier1.setPhoneNumber("+1 (123) 456-7890");
            supplier1.setEmailAddress("supplierA@example.com");
            supplier1.setWebsite("www.supplierA.com");
            supplier1.setPaymentTerms("Net 30 days");
            supplier1.setLeadTime("2 weeks");
            supplier1.setMinOrderQuantity(100);
            supplier1.setDeliveryOptions("Express, Standard");
            supplier1.setSupplierRating(4.5);
            supplier1.setSupplierReviews("Excellent service and quality products.");
            supplier1.setProductRange("Electronics, Gadgets");
            supplier1.setCertifications("ISO 9001, RoHS");
            supplier1.setSupplierNotes("Preferred supplier for electronics.");
            supplierRepository.save(supplier1);

            Supplier supplier2 = new Supplier();
            supplier2.setName("Supplier B");
            supplier2.setContactAddress("999 Main St, City, Country");
            supplier2.setPhoneNumber("+1 (999) 456-7890");
            supplier2.setEmailAddress("supplierB@example.com");
            supplier2.setWebsite("www.supplierB.com");
            supplier2.setPaymentTerms("Net 30 days");
            supplier2.setLeadTime("2 weeks");
            supplier2.setMinOrderQuantity(100);
            supplier2.setDeliveryOptions("Express, Standard");
            supplier2.setSupplierRating(4.5);
            supplier2.setSupplierReviews("Excellent service and quality products.");
            supplier2.setProductRange("Electronics, Gadgets");
            supplier2.setCertifications("ISO 9001, RoHS");
            supplier2.setSupplierNotes("Preferred supplier for electronics.");
            supplierRepository.save(supplier2);

// Populate supplier2 with data...

// Sample Product Media
            ProductMedia media1 = new ProductMedia();
            media1.setMediaUrl("www.example.com/media/shirt-image-1.jpg");
            productMediaRepository.save(media1);

            ProductMedia media2 = new ProductMedia();
            media2.setMediaUrl("www.example.com/media/shirt-image-2.jpg");
            productMediaRepository.save(media2);

// Sample Product Attributes
            ProductAttribute attribute1 = new ProductAttribute();
            attribute1.setName("Material");
            attribute1.setValue("Cotton");
            productAttributeRepository.save(attribute1);

            ProductAttribute attribute2 = new ProductAttribute();
            attribute2.setName("Size");
            attribute2.setValue("Medium");
            productAttributeRepository.save(attribute2);

// Sample Product Variations
            ProductVariation variation1 = new ProductVariation();
            variation1.setColor("Red");
            variation1.setSize("Small");
            variation1.setWeight(BigDecimal.valueOf(0.3)); // Assuming weight is in kilograms
            productVariationRepository.save(variation1);

            ProductVariation variation2 = new ProductVariation();
            variation2.setColor("Blue");
            variation2.setSize("Large");
            variation2.setWeight(BigDecimal.valueOf(0.4));
            productVariationRepository.save(variation2);

// Sample Product
            Product shirt = new Product();
            shirt.setName("Men's Cotton T-Shirt");
            shirt.setDescription("Comfortable cotton T-shirt for men");
            shirt.setSku("TS123456");
            shirt.setPrice(BigDecimal.valueOf(19.99)); // Assuming the price is in dollars
            shirt.setCategory(category1);
            shirt.setSupplier(supplier1);
            shirt.setMediaList(Arrays.asList(media1, media2));
            shirt.setAttributes(Arrays.asList(attribute1, attribute2));
            shirt.setVariations(Arrays.asList(variation1, variation2));
            productRepository.save(shirt);

// Add the products, categories, and suppliers to your database or data repository
// Save the objects to your database using Spring Data JPA repositories or other data access mechanisms


            Product product = Product.builder()
                    .description(productRequest.getDescription())
                    .name(productRequest.getName())
                    .price(productRequest.getPrice())
                    .build();
            productRepository.save(product);
            log.info("Product id:{} has been saved successfully",product.getId());
            return getProductResponse(product);
        } catch (Exception e) {
            log.error("Unable to create product with request {}", productRequest);
            throw new Exception("Unable to create the product with exception:{}", e.getCause());
        }

    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(this::getProductResponse).toList();
    }

    private ProductResponse getProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .description(product.getDescription())
                .price(product.getPrice())
                .name(product.getName())
                .build();
    }
}
