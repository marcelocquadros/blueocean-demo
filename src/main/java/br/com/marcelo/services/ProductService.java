package br.com.marcelo.services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.marcelo.entities.Product;

public interface ProductService {

    Page<Product> retrieveProducts(Pageable pageable);

    Product createProduct(Product product);

    Product findById(Integer id);
}
