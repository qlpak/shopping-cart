package org.example.cart;

import org.example.model.Product;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<Product> products = new ArrayList<>();

    public void add(Product p) {
        products.add(p);
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getTotalPrice() {
        return products.stream().mapToDouble(Product::getDiscountPrice).sum();
    }
}
