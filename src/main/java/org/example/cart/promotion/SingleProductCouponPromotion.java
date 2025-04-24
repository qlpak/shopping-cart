package org.example.cart.promotion;

import org.example.cart.Promotion;
import org.example.model.Product;

import java.util.List;

public class SingleProductCouponPromotion implements Promotion {
    private final String productCode;

    public SingleProductCouponPromotion(String productCode) {
        this.productCode = productCode;
    }

    @Override
    public void apply(List<Product> products) {
        products.stream()
                .filter(p -> p.getCode().equals(productCode))
                .findFirst()
                .ifPresent(p -> p.setDiscountPrice(p.getDiscountPrice() * 0.7));
    }

    @Override
    public String getName() {
        return "30% coupon for product " + productCode;
    }
}
