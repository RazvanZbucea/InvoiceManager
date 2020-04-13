package ro.nexttech.internship.zbucearazvan.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private long phoneNumber;

    public Company(String name) {
        this.name = name;
        generatePhoneNumber();
    }

    private void generatePhoneNumber() {
        this.phoneNumber = (long) (Math.random() * 100000000000000L);
    }
}
