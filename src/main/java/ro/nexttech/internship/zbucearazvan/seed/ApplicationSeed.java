package ro.nexttech.internship.zbucearazvan.seed;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.nexttech.internship.zbucearazvan.generator.CompanyGenerator;
import ro.nexttech.internship.zbucearazvan.generator.InvoiceGenerator;
import ro.nexttech.internship.zbucearazvan.generator.ProductGenerator;
import ro.nexttech.internship.zbucearazvan.model.Company;
import ro.nexttech.internship.zbucearazvan.model.Invoice;
import ro.nexttech.internship.zbucearazvan.model.Product;
import ro.nexttech.internship.zbucearazvan.repository.CompanyRepository;
import ro.nexttech.internship.zbucearazvan.repository.InvoiceRepository;
import ro.nexttech.internship.zbucearazvan.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
@Order(0)
public class ApplicationSeed implements CommandLineRunner {

    private final CompanyRepository companyRepository;
    private final ProductRepository productRepository;
    private final InvoiceRepository invoiceRepository;

    public ApplicationSeed(CompanyRepository companyRepository, ProductRepository productRepository, InvoiceRepository invoiceRepository) {
        this.companyRepository = companyRepository;
        this.productRepository = productRepository;
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Company> companies = CompanyGenerator.generateCompanies();
        List<Product> products = ProductGenerator.generateProducts();
        List<Invoice> invoices = InvoiceGenerator.generateInvoices(companies, products);

        if (companyRepository.findAll().isEmpty()) {
            companyRepository.saveAll(companies);
        }

        if (productRepository.findAll().isEmpty()) {
            productRepository.saveAll(products);
        }

        if (invoiceRepository.findAll().isEmpty()) {
            invoiceRepository.saveAll(invoices);
        }
    }
}
