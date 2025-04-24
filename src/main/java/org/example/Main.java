package org.example;

import org.example.cart.ShoppingCart;
import org.example.cart.promotion.BuyTwoGetOneFreePromotion;
import org.example.cart.promotion.FreeMugPromotion;
import org.example.cart.promotion.PercentageDiscountPromotion;
import org.example.cart.Promotion;
import org.example.cart.promotion.PromotionOptimizer;
import org.example.cart.promotion.SingleProductCouponPromotion;
import org.example.model.Product;
import org.example.sorting.ProductSorter;
import org.example.sorting.strategies.CombinedSorter;
import org.example.command.Command;
import org.example.command.PromotionCommand;
import org.example.command.PromotionInvoker;
import org.example.util.ProductUtils;
import org.example.sorting.strategies.PriceDescendingSorter;
import org.example.sorting.strategies.NameAlphabeticalSorter;


import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.add(new Product("001", "Laptop", 2000));
        cart.add(new Product("002", "Mouse", 50));
        cart.add(new Product("003", "Keyboard", 100));
        cart.add(new Product("004", "USB Hub", 75));

        List<Product> products = cart.getProducts();

        ProductSorter sorter = new ProductSorter();
        sorter.sort(products, CombinedSorter.byPriceDescendingNameAlphabetically());

        List<Promotion> promotions = new ArrayList<>();
        promotions.add(new PercentageDiscountPromotion());
        promotions.add(new BuyTwoGetOneFreePromotion());
        promotions.add(new FreeMugPromotion());
        promotions.add(new SingleProductCouponPromotion("003"));



        PromotionOptimizer optimizer = new PromotionOptimizer();
        List<Product> bestCombo = optimizer.applyBestCombination(promotions, products);

        System.out.println("Najlepsza kombinacja promocji:");
        bestCombo.forEach(System.out::println);
        System.out.printf("Finalna cena: %.2f zł%n", bestCombo.stream().mapToDouble(Product::getDiscountPrice).sum());


        System.out.println("Najtańszy produkt: " + ProductUtils.findCheapest(bestCombo));
        System.out.println("Najdroższy produkt: " + ProductUtils.findMostExpensive(bestCombo));
        System.out.printf("Suma cen (z promocjami): %.2f zł%n", ProductUtils.sumPrices(bestCombo));

//      sortuje według ceny bazowej (price), nie zniżkowej (discountPrice)
        System.out.println("\nSortowanie po cenie malejąco:");
        sorter.sort(bestCombo, PriceDescendingSorter.comparator());
        bestCombo.forEach(System.out::println);

        System.out.println("\nSortowanie alfabetyczne:");
        sorter.sort(bestCombo, NameAlphabeticalSorter.comparator());
        bestCombo.forEach(System.out::println);
    }
}
