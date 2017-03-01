package main.init;

import main.game.Company;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by Shlomi on 01/03/2017.
 */
public class test {
    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame();
        JPanel p = new JPanel();

        new LoadGame();

        p.add(Global.companies_table);
        frame.add(p);

        p.setVisible(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        loadCompanies();
    }

    private static void loadCompanies() {
        Global.companies.add(0, new Company("Shlomi", 1, 100, 0));
        Global.companies.add(0, new Company("Shlomi 2", 1, 100, 1));
        Global.companies.add(0, new Company("Shlomi 3", 1, 100, 2));
        Global.companies.add(0, new Company("Shlomi 4", 1, 100, 3));

        for (Company c : Global.companies)
            Global.companies_table.addCompany(new String[]{"" + c.id, c.name, "" + 0, "" + c.getNet_worth_string()});
    }
}
