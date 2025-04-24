package org.example.util;

import org.example.model.Product;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductUtils {

    public static Product findCheapest(List<Product> products) {
        return products.stream()
                .min(Comparator.comparingDouble(Product::getDiscountPrice))
                .orElse(null);
    }

    public static Product findMostExpensive(List<Product> products) {
        return products.stream()
                .max(Comparator.comparingDouble(Product::getDiscountPrice))
                .orElse(null);
    }

    public static List<Product> findNCheapest(List<Product> products, int n) {
        return products.stream()
                .sorted(Comparator.comparingDouble(Product::getDiscountPrice))
                .limit(n)
                .collect(Collectors.toList());
    }

    public static List<Product> findNMostExpensive(List<Product> products, int n) {
        return products.stream()
                .sorted(Comparator.comparingDouble(Product::getDiscountPrice).reversed())
                .limit(n)
                .collect(Collectors.toList());
    }

    public static double sumPrices(List<Product> products) {
        return products.stream().mapToDouble(Product::getDiscountPrice).sum();
    }
}