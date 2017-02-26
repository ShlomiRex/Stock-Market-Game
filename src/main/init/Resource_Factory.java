package main.init;

import core.Company;
import core.Global;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

/**
 * Created by Shlomi on 26/02/2017.
 */
public class Resource_Factory {

    private Properties prop;
    private InputStream input;

    public Resource_Factory() throws IOException {
        prop = new Properties();



        //Try to read from resources
        initalizeResourcesAndLoad();

        //Now everything is in memory. Initialize objects
        Global.companies.add(0, new Company("Shlomi",1000, 100,0));
    }

    private void initalizeResourcesAndLoad() throws IOException {
        //GLOBAL VARIABLES

        Global.random = new Random();
        Global.big_stocks_frequencies = new int[100];
        Global.companies = new ArrayList<Company>();
        Global.src_big_stocks_frequencies = new int[10];
        Global.stock_change_format = new DecimalFormat("##.####");


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
