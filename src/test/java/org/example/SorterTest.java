package org.example;

import org.example.model.Product;
import org.example.sorting.strategies.PriceDescendingSorter;
import org.example.sorting.strategies.NameAlphabeticalSorter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SorterTest {

    @Test
    void testSortByPriceDescending() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("P1", "Graphics Card", 1200));
        products.add(new Product("P2", "SSD", 300));
        products.add(new Product("P3", "Monitor", 800));

        products.sort(PriceDescendingSorter.comparator());

        assertEquals("Graphics Card", products.get(0).getName());
        assertEquals("Monitor", products.get(1).getName());
        assertEquals("SSD", products.get(2).getName());
    }

    @Test
    void testSortByNameAlphabetically() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("P1", "Router", 250));
        products.add(new Product("P2", "CPU", 1000));
        products.add(new Product("P3", "Ethernet Cable", 50));

        products.sort(NameAlphabeticalSorter.comparator());

        assertEquals("CPU", products.get(0).getName());
        assertEquals("Ethernet Cable", products.get(1).getName());
        assertEquals("Router", products.get(2).getName());
    }
}
