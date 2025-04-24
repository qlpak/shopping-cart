package org.example.cart.promotion;


import org.example.cart.Promotion;
import org.example.model.Product;

import java.util.List;

public class FreeMugPromotion implements Promotion {
    @Override
    public void apply(List<Product> products) {
        double total = products.stream().mapToDouble(Product::getDiscountPrice).sum();
        if (total > 200) {
            products.add(new Product("MUG001", "JavaMarkt Mug", 0));
        }
    }

    @Override
    public String getName() {
        return "Free mug over 200 zł";
    }
}