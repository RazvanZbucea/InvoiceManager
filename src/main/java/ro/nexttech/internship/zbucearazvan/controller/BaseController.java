package ro.nexttech.internship.zbucearazvan.controller;

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
import ro.nexttech.internship.zbucearazvan.service.InvoiceService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
@Transactional
@Order(1)
public class BaseController implements CommandLineRunner {

    private final CompanyRepository companyRepository;
    private final ProductRepository productRepository;
    private final InvoiceRepository invoiceRepository;

    public BaseController(CompanyRepository companyRepository, ProductRepository productRepository, InvoiceRepository invoiceRepository) {
        this.companyRepository = companyRepository;
        this.productRepository = productRepository;
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public void run(String... args) {
        boolean stop = false;
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");

        System.out.println("Choose [1] if you want to use the data from the database or [2] to generate new data.");
        int response = scanner.nextInt();

        if (response == 2) {
            companyRepository.deleteAll();
            productRepository.deleteAll();
            invoiceRepository.deleteAll();

            recreateData();
        }

        List<Invoice> allInvoices = invoiceRepository.findAll();

        while (!stop) {
            System.out.println("What do you want to do next?");
            System.out.println("\tStop: 0");
            System.out.println("\tAdd duplicate invoice: 1");
            System.out.println("\tSee the invoices in order: 2");
            System.out.println("\tText search: 3");
            System.out.println("\tPay invoice: 4");

            int invoiceChoice = scanner.nextInt();
            int invoicesSize = allInvoices.size();
            Optional<Invoice> invoiceById;

            switch (invoiceChoice) {
                case 1:
                    System.out.println("Please choose the ID of the invoice to duplicate (max value = " + invoicesSize + "): ");
                    int invoiceNumber = scanner.nextInt();
                    invoiceById = invoiceRepository.findById(invoiceNumber);
                    if (invoiceById.isPresent()) {
                        Invoice invoice = InvoiceService.cloneInvoice(invoiceById.get());
                        allInvoices.add(invoice);
                    } else {
                        System.out.println("Invalid size!");
                    }
                    break;
                case 2:
                    List<Invoice> invoicesOrdered = InvoiceGenerator.getInvoicesOrdered(allInvoices);
                    invoicesOrdered.forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("Enter the text to search for an invoice:");
                    String text = scanner.next();
                    List<Invoice> invoices = InvoiceService.searchByText(text, allInvoices);
                    invoices.forEach(System.out::println);
                    break;
                case 4:
                    System.out.println("Please choose the ID of the invoice to pay (max value = " + invoicesSize + "): ");
                    invoiceNumber = scanner.nextInt();
                    invoiceById = invoiceRepository.findById(invoiceNumber);
                    if (invoiceById.isPresent()) {
                        Invoice invoice = invoiceById.get();
                        if (!invoice.isPaid()) {
                            invoice.setPaid(true);
                            invoice.setPayDate(LocalDateTime.now());
                            invoiceRepository.save(invoice);
                            invoiceRepository.flush();
                        }
                    } else {
                        System.out.println("Invalid size!");
                    }
                    break;
                case 0:
                    stop = true;
            }
        }
    }


    private void recreateData() {
        List<Company> companies = CompanyGenerator.generateCompanies();
        List<Product> products = ProductGenerator.generateProducts();
        List<Invoice> invoices = InvoiceGenerator.generateInvoices(companies, products);

        companyRepository.saveAll(companies);
        productRepository.saveAll(products);
        invoiceRepository.saveAll(invoices);
        invoiceRepository.flush();
    }
}
