package ro.nexttech.internship.zbucearazvan.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int invoiceNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company sellerCompany;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoice")
    private List<Product> products = new ArrayList<>();
    private double total;
    private LocalDateTime dueDate;
    private LocalDateTime payDate;
    private boolean isPaid;
    private boolean duplicate = false;
    private int daysLeft;
}
