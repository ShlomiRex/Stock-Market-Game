package main.init;

import main.game.Company;
import main.game.news.News;
import main.gui_components.Companies_Table_Frame;
import main.gui_components.chart.Chart;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Shlomi on 26/02/2017.
 */
final public class Global {

    public static int current_update_counter = 0;

    /**
     * Just random variable. Use it as you wish. (It's for not creating many random variables everywhere and messy..)
     */
    public static Random random = new Random();
    /**
     * Array of size 100. Represent frequencies of %.
     */
    public static int[] big_stocks_frequencies = new int[100];
    public static List<Company> companies = new ArrayList<Company>();
    public static PlayerProfile profile;
    /**
     * Array of size 10. Represent origional resource data.
     */
    public static int[] src_big_stocks_frequencies = new int[10];
    public static DecimalFormat format_stock_change = new DecimalFormat("#.#");
    public static Companies_Table_Frame companies_table_frame = new Companies_Table_Frame();

    /* @@@@@@@@@@@@@@@@@@@@@@@ AD CAMPAIGN @@@@@@@@@@@@@@@@@@@@@@@ */

    // stock_growth / duration_effect = stock_growth_per_update

    /* DURATION EFFECT */
    public static int[] news_Effect_Duration_AdCampaign_Range_Short = new int[2];
    public static int[] news_Effect_Duration_AdCampaign_Range_Ok = new int[2];
    public static int[] news_Effect_Duration_AdCampaign_Range_Long = new int[2];

    /* STOCK % GROWTH */
    public static int[] news_Effect_StockPresentageGrowth_AdCampaign_Range_Small = new int[2];
    public static int[] news_Effect_StockPresentageGrowth_AdCampaign_Range_Moderate = new int[2];
    public static int[] news_Effect_StockPresentageGrowth_AdCampaign_Range_Big = new int[2];

    /* NEXT NEWS INTERVAL */
    public static int[] news_Reset_Interval_Range = new int[2];

    public static Chart chart;

    public static News news;
}
