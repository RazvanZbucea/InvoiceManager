package ro.nexttech.internship.zbucearazvan.comparators;

import ro.nexttech.internship.zbucearazvan.model.Invoice;

import java.util.Comparator;

public class InvoiceDueDateComparator implements Comparator<Invoice> {

    @Override
    public int compare(Invoice invoice1, Invoice invoice2) {
        return invoice1.getDueDate().compareTo(invoice2.getDueDate());
    }
}
