package ro.nexttech.internship.zbucearazvan.comparators;

import ro.nexttech.internship.zbucearazvan.model.Invoice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class InvoiceChainedComparator implements Comparator<Invoice> {
    private List<Comparator<Invoice>> listComparators;

    @SafeVarargs
    public InvoiceChainedComparator(Comparator<Invoice>... comparators) {
        this.listComparators = Arrays.asList(comparators);
    }

    @Override
    public int compare(Invoice invoice1, Invoice invoice2) {
        for (Comparator<Invoice> comparator : listComparators) {
            int result = comparator.compare(invoice1, invoice2);
            if (result != 0) {
                return result;
            }
        }

        return 0;
    }
}

