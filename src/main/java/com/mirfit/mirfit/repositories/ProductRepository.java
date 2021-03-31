package com.mirfit.mirfit.repositories;

import com.mirfit.mirfit.models.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository {
    List<Product> getAllHealthyProducts();
}
