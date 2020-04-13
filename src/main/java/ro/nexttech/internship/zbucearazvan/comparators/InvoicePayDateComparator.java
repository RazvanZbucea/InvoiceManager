package ro.nexttech.internship.zbucearazvan.comparators;

import ro.nexttech.internship.zbucearazvan.model.Invoice;

import java.util.Comparator;

public class InvoicePayDateComparator implements Comparator<Invoice> {

    @Override
    public int compare(Invoice invoice1, Invoice invoice2) {
        if (invoice1.getPayDate() == null) {
            return (invoice2.getPayDate() == null) ? 0 : -1;
        }
        if (invoice2.getPayDate() == null) {
            return 1;
        }
        return invoice1.getPayDate().compareTo(invoice2.getPayDate());
    }
}

