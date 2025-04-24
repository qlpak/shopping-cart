package org.example.command;

import org.example.cart.Promotion;
import org.example.model.Product;

import java.util.List;

public class PromotionCommand implements Command {
    private final Promotion promotion;
    private final List<Product> products;

    public PromotionCommand(Promotion promotion, List<Product> products) {
        this.promotion = promotion;
        this.products = products;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    @Override
    public void execute() {
        promotion.apply(products);
    }
}
