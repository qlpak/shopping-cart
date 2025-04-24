package org.example;

import org.example.cart.Promotion;
import org.example.cart.promotion.*;
import org.example.model.Product;
import org.example.sorting.ProductSorter;
import org.example.util.ProductUtils;
import org.junit.jupiter.api.Test;
import org.example.sorting.strategies.CombinedSorter;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class PromotionTest {


    @Test
    void noDiscountIfBelowThreshold() {
        List<Product> products = List.of(
                new Product("P1", "Item1", 100),
                new Product("P2", "Item2", 150)
        );
        Promotion promo = new PercentageDiscountPromotion();
        promo.apply(products);
        assertEquals(250, products.stream().mapToDouble(Product::getDiscountPrice).sum(), 0.01);
    }

    @Test
    void fivePercentDiscountIfAboveThreshold() {
        List<Product> products = List.of(
                new Product("P1", "Item1", 150),
                new Product("P2", "Item2", 200)
        );
        Promotion promo = new PercentageDiscountPromotion();
        promo.apply(products);
        double expected = (150 + 200) * 0.95;
        assertEquals(expected, products.stream().mapToDouble(Product::getDiscountPrice).sum(), 0.01);
    }

    @Test
    void exactThresholdNoDiscount() {
        List<Product> products = List.of(
                new Product("P1", "Item1", 100),
                new Product("P2", "Item2", 200)
        );
        Promotion promo = new PercentageDiscountPromotion();
        promo.apply(products);
        assertEquals(300, products.stream().mapToDouble(Product::getDiscountPrice).sum(), 0.01);
    }


    @Test
    void mugAddedIfAboveThreshold() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("P1", "Item1", 210));
        Promotion promo = new FreeMugPromotion();
        promo.apply(products);
        assertTrue(products.stream().anyMatch(p -> p.getName().equals("JavaMarkt Mug")));
    }

    @Test
    void mugNotAddedIfBelowThreshold() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("P1", "Item1", 199));
        Promotion promo = new FreeMugPromotion();
        promo.apply(products);
        assertFalse(products.stream().anyMatch(p -> p.getName().equals("JavaMarkt Mug")));
    }


    @Test
    void thirdCheapestProductFree() {
        // Arrange
        List<Product> products = new ArrayList<>();
        products.add(new Product("P1", "A", 300));
        products.add(new Product("P2", "B", 100));
        products.add(new Product("P3", "C", 200)); // P3 to drugi najtańszy

        Promotion promo = new BuyTwoGetOneFreePromotion();

        promo.apply(products);

        Product p1 = products.stream()
                .filter(p -> p.getCode().equals("P1"))
                .findFirst()
                .orElseThrow();

        assertEquals(0.0, p1.getDiscountPrice(), 0.01);
    }



    @Test
    void noFreeProductIfLessThanThree() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("P1", "Item1", 100));
        products.add(new Product("P2", "Item2", 150));
        Promotion promo = new BuyTwoGetOneFreePromotion();
        promo.apply(products);
        assertEquals(250, products.stream().mapToDouble(Product::getDiscountPrice).sum(), 0.01);
    }


    @Test
    void couponAppliesIfProductExists() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("P1", "Item1", 100));
        products.add(new Product("P2", "Item2", 150));
        Promotion promo = new SingleProductCouponPromotion("P2");
        promo.apply(products);
        assertEquals(100 + 150 * 0.7, products.stream().mapToDouble(Product::getDiscountPrice).sum(), 0.01);
    }

    @Test
    void couponIgnoredIfProductNotFound() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("P1", "Item1", 100));
        Promotion promo = new SingleProductCouponPromotion("P9");
        promo.apply(products);
        assertEquals(100, products.stream().mapToDouble(Product::getDiscountPrice).sum(), 0.01);
    }

    @Test
    void couponOnlyAffectsFirstMatchingProduct() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("P1", "Item1", 200));
        products.add(new Product("P1", "Item1", 200));
        Promotion promo = new SingleProductCouponPromotion("P1");
        promo.apply(products);
        long discountedCount = products.stream().filter(p -> p.getDiscountPrice() < p.getPrice()).count();
        assertEquals(1, discountedCount);
    }
    @Test
    void combinedPromotionsApplyCorrectly() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("P1", "Item1", 300));
        products.add(new Product("P2", "Item2", 100));
        products.add(new Product("P3", "Item3", 50));

        List<Promotion> promotions = List.of(
                new PercentageDiscountPromotion(),
                new FreeMugPromotion(),
                new BuyTwoGetOneFreePromotion()
        );

        for (Promotion p : promotions) {
            p.apply(products);
        }

        double sum = products.stream().mapToDouble(Product::getDiscountPrice).sum();
        assertTrue(sum < 450);
    }

    @Test
    void testFullPromotionFlowWithCommand() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("P1", "Laptop", 2000));
        products.add(new Product("P2", "Mouse", 50));
        products.add(new Product("P3", "Keyboard", 100));
        products.add(new Product("P4", "USB Hub", 75));

        ProductSorter sorter = new ProductSorter();
        sorter.sort(products, CombinedSorter.byPriceDescendingNameAlphabetically());

        List<Promotion> promotions = new ArrayList<>();
        promotions.add(new PercentageDiscountPromotion());
        promotions.add(new BuyTwoGetOneFreePromotion());
        promotions.add(new FreeMugPromotion());
        promotions.add(new SingleProductCouponPromotion("P3"));


        PromotionOptimizer optimizer = new PromotionOptimizer();
        List<Product> optimized = optimizer.applyBestCombination(promotions, products);

        double finalPrice = optimized.stream()
                .filter(p -> !p.getName().equals("JavaMarkt Mug"))
                .mapToDouble(Product::getDiscountPrice)
                .sum();

        assertTrue(finalPrice < 2225);
        assertEquals(5, optimized.size());
        assertTrue(optimized.stream().anyMatch(p -> p.getName().equals("JavaMarkt Mug")));
    }

    @Test
    void testFindMostExpensiveProductAfterPromotions() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("P1", "Monitor", 500));
        products.add(new Product("P2", "Mouse", 150));
        products.add(new Product("P3", "Keyboard", 100));

        List<Promotion> promos = List.of(new PercentageDiscountPromotion());
        promos.forEach(p -> p.apply(products));

        Product mostExpensive = ProductUtils.findMostExpensive(products);
        assertEquals("Monitor", mostExpensive.getName());
    }




}