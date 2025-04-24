package org.example.sorting;

import org.example.model.Product;

import java.util.Comparator;
import java.util.List;

public class ProductSorter {
    public void sort(List<Product> products, Comparator<Product> comparator) {
        products.sort(comparator);
    }
}