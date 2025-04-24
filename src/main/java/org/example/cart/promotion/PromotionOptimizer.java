package org.example.cart.promotion;

import org.example.cart.Promotion;
import org.example.command.PromotionCommand; // client
import org.example.command.PromotionInvoker; // client
import org.example.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PromotionOptimizer {
    public List<Product> applyBestCombination(List<Promotion> promotions, List<Product> original) {

        if (promotions == null || promotions.isEmpty()) {
            System.out.println("Brak promocji do przetestowania.");
            return original;
        }

        double bestPrice = Double.MAX_VALUE;
        List<Product> bestCombination = null;
        List<Promotion> bestPermutation = null;

        List<List<Promotion>> permutations = generatePermutations(promotions);
        for (List<Promotion> perm : permutations) {
            List<Product> clone = deepCopy(original);
            PromotionInvoker invoker = new PromotionInvoker();

            System.out.println("\nSprawdzam permutację:");
            for (Promotion p : perm) {
                System.out.println(" - " + p.getName());
                invoker.addCommand(new PromotionCommand(p, clone));
            }

            invoker.executeAll();

            double total = clone.stream().mapToDouble(Product::getDiscountPrice).sum();
            System.out.printf("Cena po tej permutacji: %.2f zł%n", total);

            if (total < bestPrice) {
                bestPrice = total;
                bestCombination = clone;
                bestPermutation = perm;
            }
        }

        if (bestPermutation != null) {
            System.out.println("\nNajlepsza kombinacja:");
            for (Promotion p : bestPermutation) {
                System.out.println(" - " + p.getName());
            }
            System.out.printf("Finalna cena: %.2f zł%n", bestPrice);
        } else {
            System.out.println("\nNie znaleziono żadnej poprawnej permutacji.");
        }

        return bestCombination;
    }


    private List<List<Promotion>> generatePermutations(List<Promotion> list) {
        List<List<Promotion>> result = new ArrayList<>();
        permute(list, 0, result);
        return result;
    }

    private void permute(List<Promotion> arr, int k, List<List<Promotion>> res) {
        if (k == arr.size()) {
            res.add(new ArrayList<>(arr));
        } else {
            for (int i = k; i < arr.size(); i++) {
                Collections.swap(arr, i, k);
                permute(arr, k + 1, res);
                Collections.swap(arr, k, i);
            }
        }
    }

    private List<Product> deepCopy(List<Product> original) {
        List<Product> copy = new ArrayList<>();
        for (Product p : original) {
            copy.add(new Product(p.getCode(), p.getName(), p.getPrice()));
        }
        return copy;
    }
}
