package com.tma.api.service;


import com.tma.api.domain.Product;
import com.tma.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.Past;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Iterable<Product> list() {
        return productRepository.findAll();
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Iterable<Product> save(List<Product> products) {
        return productRepository.saveAll(products);
    }

    public Optional<Product> find(long id) {
        return productRepository.findById(id);
    }

    public Product create(Product insertProduct) {
        Product copy = new Product(
                insertProduct.getId(),
                insertProduct.getProductName(),
                insertProduct.getPrice(),
                insertProduct.getRanking(),
                insertProduct.getFavorite(),
                insertProduct.getProductImage(),
                insertProduct.getProductType(),
                insertProduct.getRam(),
                insertProduct.getSsd(),
                insertProduct.getDisplay(),
                insertProduct.getCpu(),
                insertProduct.getGpu(),
                insertProduct.getDiff()
        );
        return productRepository.save(copy);
    }

    public Optional<Product> update(Long id, Product newProduct) {
        return productRepository.findById(id)
                .map(old -> {
                    Product updated = old.updateWith(newProduct);
                    return productRepository.save(updated);
                });
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

}
