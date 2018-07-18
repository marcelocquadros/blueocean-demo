package br.com.marcelo.controllers;

import br.com.marcelo.entities.Product;
import br.com.marcelo.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;


@RestController
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/v1/products", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createProduct(@RequestBody @Valid Product product) {

        logger.info("Create product >>> {}", product);
        Product savedProduct = productService.createProduct(product);
        logger.info("Product saved successfully {}", savedProduct );

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProduct.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/v1/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Product>> retrieveProducts(Pageable pageable) {

        logger.info("Listing all products");
        Page<Product> products = productService.retrieveProducts(pageable);
        logger.info("Total of products found >>> "+products.getTotalElements());
        return ResponseEntity.ok(products);
    }

    @GetMapping(value="/v1/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> retrieveProduct(@PathVariable("id") Integer id){
        logger.info("Retrieve product id {}  ", id);
        Product product = productService.findById(id);
        logger.info("Product found >>> {} ", product );
        return ResponseEntity.ok(product);
    }


}
