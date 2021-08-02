package com.tma.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
@Table(name = "Product", schema = "Test")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int    id;

    @Column(name = "product_name")
//    @Min(value = 8, message = "About name of product must to be more 8 characters")
    @NotNull(message = "Please provide a name")
    private String  productName;

    @Column(name = "product_price")
    @NotNull(message = "Please provide a price")
//    @Min(value = 5, message="min=6")
//    @Max(value = 4, message = "About price of product must not to be more 8 characters")
    private String  price;

    @Column(name = "product_ranking")
    @NotNull(message = "Please provide a ranking")
//    @Size(min = 1, max = 2, message
//            = "About Ranking must be between 1 and 2 characters")
    private String  ranking;

    @Column(name = "product_favorite")
    @NotNull(message = "Please provide a favorite")
    private String favorite;

    @Column(name = "product_image")
    @NotNull(message = "Please provide a image")
    private String  productImage;

    @Column(name = "product_type")
    @NotNull(message = "Please provide a type")
    private String  productType;

    @Column(name = "product_ram")
    @NotNull(message = "Please provide a ram")
    private String  ram;

    @Column(name = "product_ssd")
    @NotNull(message = "Please provide a ssd")
    private String  ssd;

    @Column(name = "product_display")
    @NotNull(message = "Please provide a display")
    private String  display;

    @Column(name = "product_cpu")
    @NotNull(message = "Please provide a cpu")
    private String  cpu;

    @Column(name = "product_gpu")
    @NotNull(message = "Please provide a gpu")
    private String  gpu;

    @Column(name = "product_diff")
    @NotNull(message = "Please provide a ")
    private String  diff;


    public Product updateWith(Product item) {
        return new Product(
                this.id,
                item.productName,
                item.price,
                item.ranking,
                item.favorite,
                item.productImage,
                item.productType,
                item.ram,
                item.ssd,
                item.display,
                item.cpu,
                item.gpu,
                item.diff
        );
    }

}
