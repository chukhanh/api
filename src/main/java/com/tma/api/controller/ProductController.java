package com.tma.api.controller;

import com.tma.api.domain.Product;
import com.tma.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ProductController{

    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/product",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            })
    @ResponseBody
    public Iterable<Product> list(){
        return productService.list();
    }

    @GetMapping(value = "/product/{id}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            })
    @ResponseBody
    public ResponseEntity<Product> find(@PathVariable("id") Long id, Model model){
        Optional<Product> item = productService.find(id);

        return ResponseEntity.of(item);
    }

    @PostMapping(value = "/product",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<Product> create(@Valid @RequestBody Product insertProduct){
        Product insert = productService.create(insertProduct);
        return ResponseEntity.ok(insert);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


    @PutMapping(value = "/product/{id}")
    public ResponseEntity<Product> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody Product updateProduct
    ){
        Optional<Product> updated = productService.update(id, updateProduct);
        return updated
                .map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> {
                    Product created = productService.create(updateProduct);
                    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(created.getId())
                            .toUri();
                    return ResponseEntity.created(location).body(created);
                });
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Product> delete(@PathVariable("id") Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
