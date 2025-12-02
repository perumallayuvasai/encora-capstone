package com.example.product_service.entity;

import org.example.enums.Size;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "product_variants", uniqueConstraints = @UniqueConstraint(columnNames = { "product_id", "size" }))
public class ProductVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonBackReference
    private Product product;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Size size;

    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity = 0;

    // Constructors
    public ProductVariant() {
    }

    public ProductVariant(Product product, Size size, Integer stockQuantity) {
        this.product = product;
        this.size = size;
        this.stockQuantity = stockQuantity;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
