package core;

public class Company {
    public String name;
    public double share_price;
    public final int id;
    private double stock_presentage_growth;
    public double net_worth;

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
        stock_presentage_growth = HelpFunctionsAndAlgorithms.getRandomStockChange();
        //System.out.println("Company value: [" + net_worth + "] Stock change: [" + stock_presentage_growth + "]");
        net_worth += net_worth * (stock_presentage_growth/100.0);
        //System.out.print("New company value: [" + net_worth + "]\n");
    }
}
