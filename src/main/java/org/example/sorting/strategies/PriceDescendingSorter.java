package org.example.sorting.strategies;

import org.example.model.Product;

import java.util.Comparator;

public class PriceDescendingSorter {
    public static Comparator<Product> comparator() {
        return Comparator.comparingDouble(Product::getPrice).reversed();
    }
}
