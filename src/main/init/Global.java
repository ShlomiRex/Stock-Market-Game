package main.init;

import core.Company;
import main.game.Companies_Stock_Change_Window;
import main.init.PlayerProfile;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

/**
 * Created by Shlomi on 26/02/2017.
 */
public class Global {
    /**
     * Just random variable. Use it as you wish. (It's for not creating many random variables everywhere and messy..)
     */
    public static Random random;
    /**
     * Array of size 100. Represent frequencies of %.
     */
    public static int[] big_stocks_frequencies;
    public static List<Company> companies;
    public static PlayerProfile profile;
    /**
     * Array of size 10. Represent origional resource data.
     */
    public static int[] src_big_stocks_frequencies;
    public static DecimalFormat format_stock_change;
    public static DecimalFormat format_company_net_worth;
    public static Companies_Stock_Change_Window stock_change_window_gui;

}
