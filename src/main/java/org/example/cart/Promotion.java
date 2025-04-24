package org.example.cart;

import java.util.List;
import org.example.model.Product;

public interface Promotion {
    void apply(List<Product> products);
    String getName();
}