package ro.nexttech.internship.zbucearazvan.generator;

import org.springframework.transaction.annotation.Transactional;
import ro.nexttech.internship.zbucearazvan.Constants;
import ro.nexttech.internship.zbucearazvan.comparators.InvoiceChainedComparator;
import ro.nexttech.internship.zbucearazvan.comparators.InvoiceDueDateComparator;
import ro.nexttech.internship.zbucearazvan.comparators.InvoicePaidComparator;
import ro.nexttech.internship.zbucearazvan.comparators.InvoicePayDateComparator;
import ro.nexttech.internship.zbucearazvan.model.Company;
import ro.nexttech.internship.zbucearazvan.model.Invoice;
import ro.nexttech.internship.zbucearazvan.model.Product;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Transactional
public class InvoiceGenerator {

    public static List<Invoice> generateInvoices(List<Company> companies, List<Product> products) {
        int minDate = 1;
        int maxDate = 5;
        int minPayDate = 0;
        int maxPayDate = 5;
        List<Invoice> invoices = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Invoice invoice = new Invoice();
            invoice.setInvoiceNumber(i);
            invoice.setDueDate(LocalDate.now().plusDays((long) (minDate + Math.random() * (maxDate - minDate))).atTime(LocalTime.now()));
            //invoice.setPayDate(LocalDate.now().minusDays((long) randomUnpaidDate()).atTime(LocalTime.now()));
            //invoice.setPayDate(LocalDate.now().plusDays((long)(invoice.getDueDate().getDayOfMonth() - LocalDate.now().getDayOfMonth() + Math.random() * (30 - invoice.getDueDate().getDayOfMonth() - LocalDate.now().getDayOfMonth()))).atTime(LocalTime.now()));
            invoice.setPayDate(null);
            invoice.setPaid(false);
            invoice.setDaysLeft(invoice.getDueDate().getDayOfMonth() - LocalDate.now().getDayOfMonth());
            invoices.add(invoice);
        }
        for (int i = 5; i < Constants.INVOICES_NUMBER; i++) {
            Invoice invoice = new Invoice();
            invoice.setInvoiceNumber(i);
            invoice.setDueDate(LocalDate.now().plusDays((long) (minDate + Math.random() * (maxDate - minDate))).atTime(LocalTime.now()));
            //invoice.setPayDate(LocalDate.now().minusDays((long) (minPayDate + Math.random() * (maxPayDate - minPayDate))).atTime(LocalTime.now()));
            invoice.setPayDate(LocalDate.now().plusDays((long) (minPayDate + Math.random() * ((invoice.getDueDate().getDayOfMonth() - LocalDate.now().getDayOfMonth()) - minPayDate))).atTime(LocalTime.now()));
            invoice.setPaid(true);
            invoices.add(invoice);
        }

        for (int i = 0; i <= Constants.INVOICES_NUMBER / Constants.COMPANIES_NUMBER; i++) {
            for (int j = i * Constants.COMPANIES_NUMBER; j < companies.size() + i * Constants.COMPANIES_NUMBER; j++) {
                if (j < Constants.INVOICES_NUMBER) {
                    invoices.get(j).setSellerCompany(companies.get(j - i * Constants.COMPANIES_NUMBER));
                }
            }
        }

        for (int i = 0; i <= Constants.INVOICES_NUMBER / Constants.PRODUCTS_NUMBER; i++) {
            for (int j = i * Constants.PRODUCTS_NUMBER; j < products.size() + i * Constants.PRODUCTS_NUMBER; j++) {
                if (j < Constants.INVOICES_NUMBER) {
                    invoices.get(j).getProducts().add(products.get(j - i * Constants.PRODUCTS_NUMBER));
                    invoices.get(j).setTotal(products.get(j - i * Constants.PRODUCTS_NUMBER).getPrice());
                }
            }
        }

        return invoices;
    }

    public static void isDuplicate(List<Invoice> invoices) {
        for (int i = 0; i < invoices.size(); i++) {
            for (int j = i + 1; j < invoices.size(); j++) {
                if (invoices.get(i).getSellerCompany() == invoices.get(j).getSellerCompany() &&
                        invoices.get(i).getProducts() == invoices.get(j).getProducts() &&
                        invoices.get(i).getTotal() == invoices.get(j).getTotal() &&
                        invoices.get(i).getDueDate() == invoices.get(j).getDueDate() &&
                        invoices.get(i).getPayDate() == invoices.get(j).getPayDate()) {
                    invoices.get(i).setDuplicate(true);
                }
            }
        }
    }

    public static List<Invoice> getInvoicesOrdered(List<Invoice> invoices) {
        List<Invoice> copyList = new ArrayList<>(invoices);
        copyList.sort(new InvoiceChainedComparator(
                new InvoicePaidComparator(),
                new InvoiceDueDateComparator(),
                new InvoicePayDateComparator().reversed()));

        return copyList;
    }

    public static void payInvoice(int i, List<Invoice> invoices) {
        invoices.get(i).setPaid(true);
        invoices.get(i).setPayDate(LocalDateTime.now());
    }

    public static int randomUnpaidDate(long dueDate) {
        double x;
        do {
            x = Math.random() * 10;
        } while (x < dueDate && x > 360);
        return (int) x;
    }
}

