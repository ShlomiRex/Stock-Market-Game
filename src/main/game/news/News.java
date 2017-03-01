package main.game.news;

import main.game.Company;

/**
 * Created by Shlomi on 01/03/2017.
 */
public class News {

    public String head_line;
    public String description;
    public Company[] affected_companies;

    public News() {

    }

    public String getNewsHeadline_Contract(Company first_comapny, Company second_company) {
        return "Company " + first_comapny + " has signed a contract with company " + second_company;
    }

    public String getNewsHeadline_AdCampaign(Company company) {
        return "Company " + company.name + " is preparing for advertisment campaign.";
    }

    enum NewsImpact {
        Small("Small"), Moderate("Moderate"), Big("Big");

        public String string;

        NewsImpact(String string) {
            this.string = string;
        }
    }

    enum NewsKind {
        AdvertisingCampaign, SignedContract


    }
}
