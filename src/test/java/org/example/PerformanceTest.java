package org.example;

import org.example.cart.promotion.PromotionOptimizer;
import org.example.cart.promotion.SingleProductCouponPromotion;
import org.example.model.Product;
import org.example.cart.Promotion;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PerformanceTest {

    @Test
    void testPerformanceWith100Promotions() {
        // products
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            products.add(new Product("P" + i, "Item" + i, 100 + i * 10));
        }

        // promotions
        List<Promotion> promotions = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            String productCode = "P" + (i % 5); // taking one of the 5 products cyclically
            promotions.add(new SingleProductCouponPromotion(productCode));
        }

        // optimalization
        PromotionOptimizer optimizer = new PromotionOptimizer();

        long start = System.currentTimeMillis();

        optimizer.applyBestCombination(promotions, products);

        long end = System.currentTimeMillis();

        System.out.println("Execution time for 100 promotions: " + (end - start) + " ms");
    }
}