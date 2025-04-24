package org.example.cart.promotion;


import org.example.cart.Promotion;
import org.example.model.Product;

import java.util.List;

public class PercentageDiscountPromotion implements Promotion {
    @Override
    public void apply(List<Product> products) {
        double total = products.stream().mapToDouble(Product::getDiscountPrice).sum();
        if (total > 300) {
            products.forEach(p -> p.setDiscountPrice(p.getDiscountPrice() * 0.95));
        }
    }

    @Override
    public String getName() {
        return "5% discount over 300 zł";
    }
}