package ro.nexttech.internship.zbucearazvan.service;

import org.springframework.stereotype.Service;
import ro.nexttech.internship.zbucearazvan.model.Company;
import ro.nexttech.internship.zbucearazvan.model.Invoice;
import ro.nexttech.internship.zbucearazvan.model.Product;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class InvoiceService {
    public static Invoice cloneInvoice(Invoice invoice) {
        Invoice newInvoice = new Invoice();

        newInvoice.setInvoiceNumber(invoice.getInvoiceNumber());
        newInvoice.setSellerCompany(invoice.getSellerCompany());
        newInvoice.setProducts(invoice.getProducts());
        newInvoice.setTotal(invoice.getTotal());
        newInvoice.setDueDate(invoice.getDueDate());
        newInvoice.setPayDate(invoice.getPayDate());
        newInvoice.setPaid(invoice.isPaid());
        newInvoice.setDuplicate(true);
        newInvoice.setDaysLeft(invoice.getDaysLeft());

        return newInvoice;
    }

    public static List<Invoice> searchByText(String text, List<Invoice> invoices) {
        List<Invoice> copyInvoices = new ArrayList<>(invoices);
        List<Invoice> results = new ArrayList<>();

        if (text.length() >= 3) {
            Iterator<Invoice> iterator = copyInvoices.iterator();
            while (iterator.hasNext()) {
                Invoice copyInvoice = iterator.next();
                String[] words = copyInvoice.getSellerCompany().getName().split(" ");
                if (text.equalsIgnoreCase(words[0])) {
                    results.add(copyInvoice);
                    iterator.remove();
                }
            }
            iterator = copyInvoices.iterator();
            while (iterator.hasNext()) {
                Invoice copyInvoice = iterator.next();
                String[] words = copyInvoice.getSellerCompany().getName().split(" ");
                if (text.equalsIgnoreCase(words[1])) {
                    results.add(copyInvoice);
                    iterator.remove();
                }
            }
            iterator = copyInvoices.iterator();
            while (iterator.hasNext()) {
                Invoice copyInvoice = iterator.next();
                if (copyInvoice.getSellerCompany().getName().toLowerCase().contains(text.toLowerCase())) {
                    results.add(copyInvoice);
                    iterator.remove();
                }
            }
            iterator = copyInvoices.iterator();
            while (iterator.hasNext()) {
                Invoice copyInvoice = iterator.next();
                for (int i = 0; i < copyInvoice.getProducts().size(); i++) {
                    if (copyInvoice.getProducts().get(i).getName().equalsIgnoreCase(text)) {
                        results.add(copyInvoice);
                        copyInvoices.remove(copyInvoice);
                    }
                }
            }
        }
        if (results.size() <= 10) {
            return results;
        }

        return results.subList(0, 10);
    }
}

