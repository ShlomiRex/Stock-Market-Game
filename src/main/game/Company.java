package main.game;

import main.init.Global;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Company {
    public final int id;
    public String name;
    public double share_price;
    public double stock_presentage_growth;
    public double current_company_value;
    public List<Double> company_value_history;

    public Company(String name, int net_worth,int share_price, int id) {
        this.name = name;
        this.share_price = share_price;
        this.id = id;
        this.current_company_value = net_worth;
        this.stock_presentage_growth = 0;
        this.company_value_history = new ArrayList<>();
    }

    /**
     * Every game update, update the stock values n stuff
     */
    public void update() {
        stock_presentage_growth = StockMarket.getRandomStockChange();
        //System.out.println("Company [name,value,change]:     [" +name +","+net_worth+","+ stock_presentage_growth + "]");
        current_company_value += current_company_value * (stock_presentage_growth / 100.0);
        //System.out.print("New company value: [" + net_worth + "]\n");

        company_value_history.add(current_company_value);

        updateTable();
    }

    /**
     * Update the JTable that shows the user the company
     */
    private void updateTable() {
        Global.companies_table_frame.updateCompanyAtTable(this);

    }

    /**
     * Returns <b>current</b> company's value.
     **/
    public String getNet_worth_string() {
        //return Double.parseDouble(Global.format_company_net_worth.format(net_worth));

        return NumberFormat.getNumberInstance(Locale.US).format(current_company_value);
    }
}
