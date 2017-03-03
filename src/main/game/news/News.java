package main.game.news;

import main.game.Company;
import main.init.Global;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Shlomi on 01/03/2017.
 */
public class News {

    /*
Integer that decreases by 1 each game update.
When it reaches 0, the next NEWS FEED will get news.
*/
    private static int nextNewsIn;
    public String head_line;
    public String description;
    public Company[] affected_companies;
    private JTextArea textArea_ForNews;

    public News(JTextArea textArea) {
        Global.news = this;
        this.textArea_ForNews = textArea;
        DefaultCaret caret = (DefaultCaret) textArea_ForNews.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    }

    private static void resetNextNewsTimer() {
        nextNewsIn = Global.random.nextInt(Global.news_Reset_Interval_Range[1])
                + Global.news_Reset_Interval_Range[0];

        //Generate random number between the range.
    }

    public String getNewsHeadline_Contract(Company first_comapny, Company second_company) {
        return "Company " + first_comapny + " has signed a contract with company " + second_company;
    }

    public String getNewsHeadline_AdCampaign(Company company) {
        return "Company " + company.name + " is preparing for advertisment campaign.";
    }

    public void update() {
        if (nextNewsIn == 0) {
            String[] strings = getNextNews();
            textArea_ForNews.append("\n\n" + getCurrentTime());
            for (int i = 0; i < strings.length; i++) {
                textArea_ForNews.append("\n" + strings[i]);
            }
            textArea_ForNews.append("\n\n");
            resetNextNewsTimer();
        } else
            nextNewsIn--;
    }

    private String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(cal.getTime());
    }

    private String[] getNextNews() {
        NewsEffect effect = new NewsEffect(News.NewsImpact.getRandomImpact());
        return effect.getNewsDescription_AdCampaign(getRandomCompany());
    }

    private Company getRandomCompany() {
        return Global.companies_table_frame.getRandomCompany();
    }

    enum NewsImpact {
        Small("Small"), Moderate("Moderate"), Big("Big");

        public String string;

        NewsImpact(String string) {
            this.string = string;
        }

        static NewsImpact getRandomImpact() {
            int r = Global.random.nextInt(3);
            if (r == 0)
                return Small;
            else if (r == 1)
                return Moderate;
            return Big;
        }
    }

    enum NewsKind {
        AdvertisingCampaign, SignedContract


    }
}
