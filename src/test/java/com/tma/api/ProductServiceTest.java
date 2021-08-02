package com.tma.api;

import com.tma.api.domain.Product;
import com.tma.api.repository.ProductRepository;
import com.tma.api.service.ProductService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
public class ProductServiceTest {
    @TestConfiguration
    public static class ProductServiceConfigurationTest{
        @Bean
        ProductService productService(){
            return new ProductService();
        }

    }

    @MockBean
    ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Before
    public void setUp(){
        Mockito.when(productRepository.findAll())
                .thenReturn(IntStream.range(0, 5)
                                    .mapToObj(i -> new Product( i,"name-" + i, "price-" + i, "ranking-" + i, "favorite-" +i, "image-" +i, "type-" +i, "ram-" +i, "ssd-"+i, "display-" +i, "cpu-"+i, "gpu-" +i, "dif-" +i))
                                    .collect(Collectors.toList()));
    }

    @Test
    public void testCount(){
        Assert.assertEquals(5, productService.list());
    }






}
