package com.tma.api;


//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.tma.api.domain.Product;
//import com.tma.api.service.ProductService;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.List;

@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

//    @Bean
//    CommandLineRunner runner(ProductService productService){
//        return args -> {
//            ObjectMapper mapper = new ObjectMapper();
//            TypeReference<List<Product>> typeReference = new TypeReference<List<Product>>(){};
//            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/products.json");
//            try {
//                List<Product> products = mapper.readValue(inputStream, typeReference);
//                productService.save(products);
//                System.out.println("Products saved!");
//            }catch (IOException ioException){
//                System.out.println("Unable to  saved products: " + ioException.getMessage());
//            }
//        };
//    }
}
