package ro.nexttech.internship.zbucearazvan.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private double price;

    @ManyToOne
    @JoinColumn(name = "invoice")
    private Invoice invoice;

    public Product(String name) {
        this.name = name;
        generateRandomPrice();
    }

    private void generateRandomPrice() {
        double min = 0.1;
        double max = 999.9;
        this.price = min + Math.random() * (max - min);
    }
}


