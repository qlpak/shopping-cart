package org.example;

import org.example.cart.ShoppingCart;
import org.example.model.Product;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartTest {

    @Test
    void testEmptyCartTotalIsZero() {
        ShoppingCart cart = new ShoppingCart();

        double total = cart.getTotalPrice();

        assertEquals(0.0, total);
    }

    @Test
    void testSingleProductTotal() {
        ShoppingCart cart = new ShoppingCart();
        cart.add(new Product("P1", "Item1", 100));

        double total = cart.getTotalPrice();

        assertEquals(100.0, total);
    }

    @Test
    void testMultipleProductsTotal() {
        ShoppingCart cart = new ShoppingCart();
        cart.add(new Product("P1", "Item1", 100));
        cart.add(new Product("P2", "Item2", 200));

        double total = cart.getTotalPrice();

        assertEquals(300.0, total);
    }

    @Test
    void testProductsWithZeroPrice() {
        ShoppingCart cart = new ShoppingCart();
        cart.add(new Product("P1", "Free Item", 0));
        cart.add(new Product("P2", "Item2", 150));

        double total = cart.getTotalPrice();

        assertEquals(150.0, total);
    }

    @Test
    void testProductWithModifiedDiscountPrice() {
        ShoppingCart cart = new ShoppingCart();
        Product p = new Product("P1", "Item1", 200);
        p.setDiscountPrice(50);
        cart.add(p);

        double total = cart.getTotalPrice();

        assertEquals(50.0, total);
    }

    @Test
    void testAddMultipleSameProducts() {
        ShoppingCart cart = new ShoppingCart();
        cart.add(new Product("P1", "Apple", 100));
        cart.add(new Product("P1", "Apple", 100));

        List<Product> products = cart.getProducts();

        assertEquals(2, products.size());
        assertEquals(200.0, cart.getTotalPrice());
    }

    @Test
    void testGetProductsReturnsCorrectList() {
        ShoppingCart cart = new ShoppingCart();
        Product p1 = new Product("P1", "Item1", 123.45);
        cart.add(p1);

        List<Product> products = cart.getProducts();

        assertEquals(1, products.size());
        assertEquals("Item1", products.get(0).getName());
    }
}
