package ro.nexttech.internship.zbucearazvan;

public class Constants {
    public static final int COMPANIES_NUMBER = 24;
    public static final int PRODUCTS_NUMBER = 48;
    public static final int INVOICES_NUMBER = 50;

    public static final String DB_NAME = "InvoiceManager.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\Razvi\\Desktop\\JavaPrograms\\ZbuceaRazvanInternshipNexttech\\" + DB_NAME;

    public static final String TABLE_COMPANY = "Company";
    public static final String COLUMN_COMPANY_ID = "_id";
    public static final String COLUMN_COMPANY_NAME = "name";
    public static final String COLUMN_COMPANY_PHONENUMBER = "phoneNumber";
    public static final int INDEX_COMPANY_ID = 1;
    public static final int INDEX_COMPANY_NAME = 2;
    public static final int INDEX_COMPANY_PHONENUMBER = 3;

    public static final String TABLE_PRODUCT = "Product";
    public static final String COLUMN_PRODUCT_ID = "_id";
    public static final String COLUMN_PRODUCT_NAME = "name";
    public static final String COLUMN_PRODUCT_PRICE = "price";
    public static final int INDEX_PRODUCT_ID = 1;
    public static final int INDEX_PRODUCT_NAME = 2;
    public static final int INDEX_PRODUCT_PRICE = 3;

    public static final String letters = "abcdefghijklmnopqrstuvwxyz";
}

