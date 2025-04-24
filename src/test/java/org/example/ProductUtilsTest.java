package org.example;

import org.example.model.Product;
import org.example.util.ProductUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductUtilsTest {

    @Test
    void testFindCheapestProduct() {
        List<Product> products = List.of(
                new Product("P1", "Phone", 2000),
                new Product("P2", "Case", 100),
                new Product("P3", "Charger", 150)
        );
        Product cheapest = ProductUtils.findCheapest(products);
        assertEquals("Case", cheapest.getName());
    }

    @Test
    void testFindMostExpensiveProduct() {
        List<Product> products = List.of(
                new Product("P1", "Phone", 2000),
                new Product("P2", "Case", 100),
                new Product("P3", "Charger", 150)
        );
        Product mostExpensive = ProductUtils.findMostExpensive(products);
        assertEquals("Phone", mostExpensive.getName());
    }

    @Test
    void testFindNCheapestProducts() {
        List<Product> products = List.of(
                new Product("P1", "Phone", 2000),
                new Product("P2", "Case", 100),
                new Product("P3", "Charger", 150),
                new Product("P4", "Cable", 50)
        );
        List<Product> cheapestTwo = ProductUtils.findNCheapest(products, 2);
        assertEquals(2, cheapestTwo.size());
        assertEquals("Cable", cheapestTwo.get(0).getName());
        assertEquals("Case", cheapestTwo.get(1).getName());
    }

    @Test
    void testFindNMostExpensiveProducts() {
        List<Product> products = List.of(
                new Product("P1", "Phone", 2000),
                new Product("P2", "Case", 100),
                new Product("P3", "Charger", 150),
                new Product("P4", "Laptop", 3000)
        );
        List<Product> topTwo = ProductUtils.findNMostExpensive(products, 2);
        assertEquals(2, topTwo.size());
        assertEquals("Laptop", topTwo.get(0).getName());
        assertEquals("Phone", topTwo.get(1).getName());
    }

    @Test
    void testSumPrices() {
        List<Product> products = List.of(
                new Product("P1", "Item1", 100),
                new Product("P2", "Item2", 200)
        );
        double total = ProductUtils.sumPrices(products);
        assertEquals(300.0, total);
    }
}
