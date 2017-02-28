package main.init;

import core.Company;
import main.game.Companies_Stock_Change_Window;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

/**
 * Created by Shlomi on 26/02/2017.
 */
public class LoadGame {

    private Properties prop;
    private InputStream input;

    public LoadGame() throws IOException {
        prop = new Properties();



        //Try to read from resources
        initalizeResourcesAndLoad();

        loadCompanies();

    }

    private void loadCompanies() {
        Global.companies.add(0, new Company("Shlomi",1000000000, 100,0));

        Global.stock_change_window_gui = new Companies_Stock_Change_Window();

        for(Company c : Global.companies)
            Global.stock_change_window_gui.addCompany(new String[] {""+c.id, c.name,""+0, ""+c.getNet_worth_string() } );
    }

    private void initalizeResourcesAndLoad() throws IOException {
        //GLOBAL VARIABLES

        Global.random = new Random();
        Global.big_stocks_frequencies = new int[100];
        Global.companies = new ArrayList<Company>();
        Global.src_big_stocks_frequencies = new int[10];
        Global.format_stock_change = new DecimalFormat("#.#");
        Global.format_company_net_worth = new DecimalFormat("###,###.###");

        //RESOURCES AND PROPERTIES



        loadStockChangeCharts();

    }

    /**
     * Initialize Global.big_stocks and Global.little_stocks frequencies charts
     * @throws IOException
     */
    private void loadStockChangeCharts() throws IOException {

        prop.load(getClass().getResourceAsStream("/stock_chart_freq.properties"));



        String[] str_big = prop.getProperty("big_change").split(",");


        int p, latestIndex = 0;
        for(int i = 0; i < 10; i++) {
            p = Integer.parseInt(str_big[i]); //The value of big_chart (Represent %)

            Global.src_big_stocks_frequencies[i] = p;

            for(int j = 0; j < p; j++) {
                Global.big_stocks_frequencies[latestIndex + j] = p;
            }
            latestIndex += p;
        }


    }
}
