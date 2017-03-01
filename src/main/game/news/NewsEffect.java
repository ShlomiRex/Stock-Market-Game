package main.game.news;

import main.game.Company;
import main.game.news.News.NewsImpact;
import main.init.Global;

/**
 * Created by Shlomi on 01/03/2017.
 */

/**
 * This class contains useful data to read and write about news effect.
 */
public class NewsEffect {
    private final String[] AD_LOCATION = {"Television", "News paper", "Radio", "Buses", "Internet"};
    public Company[] companies_affected_positively;
    public NewsImpact impact_value;

    public NewsEffect(NewsImpact impact) {
        impact_value = impact;
    }

    public String[] getNewsDescription_AdCampaign(Company company) {
        String[] d = new String[3];
        d[0] = "Company " + company.name + " realised the profit potential" +
                " in advertising at " + getRandomAdLocation() + " .";
        d[1] = "News impact: " + impact_value.string;
        return d;
    }

    private String getRandomAdLocation() {
        return AD_LOCATION[Global.random.nextInt(AD_LOCATION.length)];
    }

    /**
     * Duration is amount of game updates. (1 update = 1 duration)
     *
     * @return
     */
    private int getRandomAdEffectDuration() {
        //Small
        if (impact_value == NewsImpact.Small) {

        }
        //Moderate
        else if (impact_value == NewsImpact.Moderate) {

        }
        //Big
        return 0;
    }

    private int getRandomValueFromArrayByRange(int[] array) {
        return 0;
    }

    //TODO: Hey, complete this 2 functions
}
