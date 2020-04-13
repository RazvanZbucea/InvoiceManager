package ro.nexttech.internship.zbucearazvan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.nexttech.internship.zbucearazvan.model.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}
