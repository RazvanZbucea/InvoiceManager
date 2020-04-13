package ro.nexttech.internship.zbucearazvan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.nexttech.internship.zbucearazvan.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
