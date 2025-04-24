package org.example.sorting.strategies;

import org.example.model.Product;

import java.util.Comparator;

public class NameAlphabeticalSorter {
    public static Comparator<Product> comparator() {
        return Comparator.comparing(Product::getName);
    }
}
