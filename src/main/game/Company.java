package main.game;

import main.init.Global;

import java.text.NumberFormat;
import java.util.Locale;

public class Company {
    public final int id;
    public String name;
    public double share_price;
    public double stock_presentage_growth;
    private double net_worth;

    public Company(String name, int net_worth,int share_price, int id) {
        this.name = name;
        this.share_price = share_price;
        this.id = id;
        this.net_worth = net_worth;
        this.stock_presentage_growth = 0;
    }

    /**
     * Every game update, update the stock values n stuff
     */
    public void update() {
        stock_presentage_growth = StockMarket.getRandomStockChange();
        //System.out.println("Company [name,value,change]:     [" +name +","+net_worth+","+ stock_presentage_growth + "]");
        net_worth += net_worth * (stock_presentage_growth/100.0);
        //System.out.print("New company value: [" + net_worth + "]\n");

        updateTable();
    }

    /**
     * Update the JTable that shows the user the company
     */
    private void updateTable() {
        Global.companies_table.updateCompanyAtTable(this);
    }

    public String getNet_worth_string() {
        //return Double.parseDouble(Global.format_company_net_worth.format(net_worth));

        return NumberFormat.getNumberInstance(Locale.US).format(net_worth);
    }
}
