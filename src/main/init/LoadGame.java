package main.init;

import main.game.Company;
import main.game.Game;
import main.game.news.NewsFeed_Frame;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Properties;

/**
 * Created by Shlomi on 26/02/2017.
 */
public final class LoadGame {

    private Properties prop;
    private InputStream input;

    public LoadGame() throws IOException {
        prop = new Properties();

        loadGameSettings();
        initCompanies();
        loadCompanies();
        loadStockChangeCharts();
        loadNewsSettings();
        initNews();
    }

    private void loadGameSettings() throws IOException {

        prop.load(getClass().getResourceAsStream("/game_settings.properties"));

        double amount;
        amount = Double.parseDouble(prop.getProperty("update_after"));
        BigDecimal bigDecimal = new BigDecimal("" + 1000000000L * amount);
        Game.update_after = bigDecimal.longValue();

        prop.clear();
    }

    private void initNews() {
        new NewsFeed_Frame();
    }

    private void initCompanies() {
        Global.companies.add(0, new Company("Shlomi", 1000, 100, 0));
        Global.companies.add(0, new Company("Shlomi 2", 1000, 100, 1));
        Global.companies.add(0, new Company("Shlomi 3", 1000, 100, 2));
        Global.companies.add(0, new Company("Shlomi 4", 1000, 100, 3));
    }

    private void loadCompanies() {
        for (Company c : Global.companies)
            Global.companies_table_frame.addCompany(new String[]{"" + c.id, c.name, "" + 0, "" + c.getNet_worth_string()});
    }

    private void loadNewsSettings() throws IOException {

        prop.load(getClass().getResourceAsStream("/news_settings.properties"));

        //Get properties as array of string. Convert the array of strings to array of ints.
        int[] array1, array2, array3;

        array1 = Arrays.asList(prop.getProperty("ad_campaign_duration_range_short")
                .split(",")).stream().mapToInt(Integer::parseInt).toArray();
        array2 = Arrays.asList(prop.getProperty("ad_campaign_duration_range_ok")
                .split(",")).stream().mapToInt(Integer::parseInt).toArray();
        array3 = Arrays.asList(prop.getProperty("ad_campaign_duration_range_long")
                .split(",")).stream().mapToInt(Integer::parseInt).toArray();

        /* DURATION EFFECT */
        Global.news_Effect_Duration_AdCampaign_Range_Short = array1;
        Global.news_Effect_Duration_AdCampaign_Range_Ok = array2;
        Global.news_Effect_Duration_AdCampaign_Range_Long = array3;






        /* STOCK % GROWTH */
        array1 = Arrays.asList(prop.getProperty("ad_campaign_CompanyNetWorthPresentageGrowth_range_small")
                .split(",")).stream().mapToInt(Integer::parseInt).toArray();
        array2 = Arrays.asList(prop.getProperty("ad_campaign_CompanyNetWorthPresentageGrowth_range_moderate")
                .split(",")).stream().mapToInt(Integer::parseInt).toArray();
        array3 = Arrays.asList(prop.getProperty("ad_campaign_CompanyNetWorthPresentageGrowth_range_big")
                .split(",")).stream().mapToInt(Integer::parseInt).toArray();

        /* STOCK % GROWTH */
        Global.news_Effect_StockPresentageGrowth_AdCampaign_Range_Small = array1;
        Global.news_Effect_StockPresentageGrowth_AdCampaign_Range_Moderate = array2;
        Global.news_Effect_StockPresentageGrowth_AdCampaign_Range_Big = array3;


        //Load next news interval range
        array1 = Arrays.asList(prop.getProperty("next_news_reset_timer_range")
                .split(",")).stream().mapToInt(Integer::parseInt).toArray();
        Global.news_Reset_Interval_Range = array1;

        prop.clear();
    }

    /**
     * Initialize Global.big_stocks and Global.little_stocks frequencies charts
     *
     * @throws IOException
     */
    private void loadStockChangeCharts() throws IOException {

        prop.load(getClass().getResourceAsStream("/stock_presentage,settings.properties"));

        String[] str_big = prop.getProperty("big_change").split(",");

        int p, latestIndex = 0;
        for (int i = 0; i < 10; i++) {
            p = Integer.parseInt(str_big[i]); //The value of big_chart (Represent %)

            Global.src_big_stocks_frequencies[i] = p;

            for (int j = 0; j < p; j++) {
                Global.big_stocks_frequencies[latestIndex + j] = p;
            }
            latestIndex += p;
        }
        prop.clear();

    }
}
