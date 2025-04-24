package org.example.cart.promotion;

import org.example.cart.Promotion;
import org.example.model.Product;

import java.util.Comparator;
import java.util.List;

public class BuyTwoGetOneFreePromotion implements Promotion {
    @Override
    public void apply(List<Product> products) {
        if (products.size() >= 3) {
            List<Product> sorted = products.stream()
                    .sorted(Comparator.comparingDouble(Product::getPrice))
                    .toList();

            Product toMakeFree = sorted.get(2); // trzeci najtańszy

            for (Product p : products) {
                if (p.getCode().equals(toMakeFree.getCode())
                        && p.getName().equals(toMakeFree.getName())
                        && p.getPrice() == toMakeFree.getPrice()) {
                    p.setDiscountPrice(0);
                    break;
                }
            }
        }
    }

    @Override
    public String getName() {
        return "Buy 2 Get 1 Free";
    }
}
