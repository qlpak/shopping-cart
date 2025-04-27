package org.example;

import org.example.cart.ShoppingCart;
import org.example.cart.promotion.BuyTwoGetOneFreePromotion;
import org.example.cart.promotion.FreeMugPromotion;
import org.example.cart.promotion.PercentageDiscountPromotion;
import org.example.cart.promotion.PromotionOptimizer;
import org.example.cart.promotion.SingleProductCouponPromotion;
import org.example.cart.Promotion;
import org.example.model.Product;
import org.example.sorting.ProductSorter;
import org.example.sorting.strategies.CombinedSorter;
import org.example.sorting.strategies.PriceDescendingSorter;
import org.example.sorting.strategies.NameAlphabeticalSorter;
import org.example.util.ProductUtils;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Initialize shopping cart
        ShoppingCart cart = new ShoppingCart();
        cart.add(new Product("001", "Laptop", 2000));
        cart.add(new Product("002", "Mouse", 50));
        cart.add(new Product("003", "Keyboard", 100));
        cart.add(new Product("004", "USB Hub", 75));

        List<Product> products = cart.getProducts();

        // Sort products by price descending and then by name alphabetically
        ProductSorter sorter = new ProductSorter();
        sorter.sort(products, CombinedSorter.byPriceDescendingNameAlphabetically());

        // Define available promotions
        List<Promotion> promotions = new ArrayList<>();
        promotions.add(new PercentageDiscountPromotion());
        promotions.add(new BuyTwoGetOneFreePromotion());
        promotions.add(new FreeMugPromotion());
        promotions.add(new SingleProductCouponPromotion("003")); // 30% discount on Keyboard

        // Find the best combination of promotions
        PromotionOptimizer optimizer = new PromotionOptimizer();
        List<Product> bestCombo = optimizer.applyBestCombination(promotions, products);

        // Display results
        System.out.println("\nBest promotion combination:");
        bestCombo.forEach(System.out::println);
        System.out.printf("Final total price: %.2f pln%n", ProductUtils.sumPrices(bestCombo));

        // Find and display cheapest and most expensive product
        System.out.println("\nCheapest product: " + ProductUtils.findCheapest(bestCombo));
        System.out.println("Most expensive product: " + ProductUtils.findMostExpensive(bestCombo));
        System.out.printf("Sum of prices (after promotions): %.2f pln%n", ProductUtils.sumPrices(bestCombo));

        double originalTotalPrice = bestCombo.stream()
                .mapToDouble(Product::getPrice)
                .sum();

        double discountedTotalPrice = bestCombo.stream()
                .mapToDouble(Product::getDiscountPrice)
                .sum();

        System.out.printf("\nOriginal total price (without promotions): %.2f pln%n", originalTotalPrice);
        System.out.printf("Discounted total price (after promotions): %.2f pln%n", discountedTotalPrice);
        System.out.printf("You saved: %.2f pln%n", originalTotalPrice - discountedTotalPrice);

        // Sort by base price descending (price field, not discountPrice)
        System.out.println("\nSorting by base price descending:");
        sorter.sort(bestCombo, PriceDescendingSorter.comparator());
        bestCombo.forEach(System.out::println);

        // Sort alphabetically by product name
        System.out.println("\nSorting by product name alphabetically:");
        sorter.sort(bestCombo, NameAlphabeticalSorter.comparator());
        bestCombo.forEach(System.out::println);
    }
}
