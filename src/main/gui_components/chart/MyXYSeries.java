package main.gui_components.chart;

import main.game.Company;
import org.jfree.data.xy.XYSeries;

/**
 * Created by Shlomi on 04/03/2017.
 */
public class MyXYSeries extends XYSeries {

    private Company company;
    private Chart2 chart;

    public MyXYSeries(Company company) {
        super(company.id);
        this.company = company;
        //this.chart = chart;
    }

    @Override
    public void add(double x, double y) {
        super.add(x, y);
    }
}
