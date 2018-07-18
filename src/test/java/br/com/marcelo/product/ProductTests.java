package br.com.marcelo.product;


import br.com.marcelo.entities.Product;
import br.com.marcelo.repositories.ProductRepository;
import br.com.marcelo.services.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.BDDMockito.given;

/**
 * A small sample showing the tests at controller and service level using mock on repository
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductTests {

    private static final String URL = "http://localhost:";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;


    /**
     * Teste at controller level
     */
    @Test
    public void createProduct() {

        Product product = new Product();
        product.setDescription("Description xpto");
        product.setName("Product xpto");
        product.setPrice(1500.9);

        given(this.productRepository.save(product)).willReturn(product);

        ResponseEntity<Void> resp = this.restTemplate.postForEntity(URL + port + "/v1/products", product, Void.class);

        Assert.assertEquals(HttpStatus.CREATED, resp.getStatusCode());

    }

    /**
     * Test at service level using mock on repository
     */
    @Test
    public void findProductById() {
        Product prod = new Product();
        prod.setId(100);
        prod.setName("TV");
        prod.setPrice(2500.9);
        prod.setDescription("The awesome TV");
        //mock the repository
        given(productRepository.findById(100)).willReturn(Optional.of(prod));

        Assert.assertEquals("TV", productService.findById(100).getName());
    }
}
