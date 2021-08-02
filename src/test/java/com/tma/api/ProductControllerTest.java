package com.tma.api;


import com.tma.api.controller.ProductController;
import com.tma.api.domain.Product;
import com.tma.api.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService productService;

    @Test
    public void testFindAll() throws Exception{
        Iterable<Product> allProducts = IntStream.range(0, 5)
                                                .mapToObj(i -> new Product( i,"name-" + i, "price-" + i, "ranking-" + i, "favorite-" +i, "image-" +i, "type-" +i, "ram-" +i, "ssd-"+i, "display-" +i, "cpu-"+i, "gpu-" +i, "dif-" +i))
                                                .collect(Collectors.toList());

        given(productService.list()).willReturn(allProducts);

        mvc.perform(get("/api/v1/product").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].id", is(0)));
    }
}
