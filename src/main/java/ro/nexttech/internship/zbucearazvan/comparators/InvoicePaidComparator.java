package ro.nexttech.internship.zbucearazvan.comparators;

import ro.nexttech.internship.zbucearazvan.model.Invoice;

import java.util.Comparator;

public class InvoicePaidComparator implements Comparator<Invoice> {

    @Override
    public int compare(Invoice invoice1, Invoice invoice2) {
        return Boolean.compare(invoice1.isPaid(), invoice2.isPaid());
    }
}

