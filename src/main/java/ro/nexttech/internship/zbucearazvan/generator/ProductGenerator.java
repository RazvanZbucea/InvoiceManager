package ro.nexttech.internship.zbucearazvan.generator;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.nexttech.internship.zbucearazvan.Constants;
import ro.nexttech.internship.zbucearazvan.model.Product;

import java.util.ArrayList;
import java.util.List;

@Transactional
public class ProductGenerator {

    private static String generateProductName() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int character = (int) (Math.random() * Constants.letters.length());
            builder.append(Constants.letters.charAt(character));
        }

        return builder.toString();
    }

    public static List<Product> generateProducts() {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < Constants.PRODUCTS_NUMBER; i++) {
            Product currentProduct = new Product(generateProductName());
            products.add(currentProduct);
        }

        return products;
    }
}

