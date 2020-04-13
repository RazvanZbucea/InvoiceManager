package ro.nexttech.internship.zbucearazvan.generator;

import org.springframework.transaction.annotation.Transactional;
import ro.nexttech.internship.zbucearazvan.Constants;
import ro.nexttech.internship.zbucearazvan.model.Company;

import java.util.ArrayList;
import java.util.List;

@Transactional
public class CompanyGenerator {
    private static final String[] names = {"Romanian", "European", "Food", "Electricity", "Incorporated", "Eastern", "Aviation", "Oil"};
    private static List<String> companyNames = new ArrayList<>();

    private static void generateCompanyName() {
        for (int i = 0; i < names.length; i++) {
            for (int j = i + 1; j < names.length; j++) {
                for (int k = j + 1; k < names.length; k++) {
                    companyNames.add(names[i] + " " + names[j] + " " + names[k]);
                }
            }
        }
    }

    public static List<Company> generateCompanies() {
        List<Company> companies = new ArrayList<>();
        generateCompanyName();
        for (int i = 0; i < Constants.COMPANIES_NUMBER; i++) {
            Company currentCompany = new Company(companyNames.get(i));
            companies.add(currentCompany);
        }

        return companies;
    }
}
