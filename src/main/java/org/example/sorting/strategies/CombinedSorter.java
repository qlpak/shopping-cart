package org.example.sorting.strategies;

import org.example.model.Product;
import java.util.Comparator;

public class CombinedSorter {
    public static Comparator<Product> byPriceDescendingNameAlphabetically() {
        return Comparator.comparingDouble(Product::getPrice).reversed()
                .thenComparing(Product::getName);
    }
}
