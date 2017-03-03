package main.gui_components.chart;

import main.game.Company;
import main.init.Global;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Created by Shlomi on 02/03/2017.
 */
public final class Chart {

    private final String KEY_ROW_COMPANY_VALUE = "Company Value";
    private final String KEY_ROW_STOCK_PRESENTAGE_GROWTH = "Stock % Growth";
    public ChartPanel chartPanel;
    private Character rowKey = 'r';
    private Character columnKey = 'c';
    private DefaultCategoryDataset categoryDataset;
    private JFreeChart jFreeChart;
    private CategoryPlot plot;
    private Company current_company;

    private DataType current_data_presentation;

    public Chart() {

        categoryDataset = new DefaultCategoryDataset();

        jFreeChart = ChartFactory.createLineChart(
                "Stocks over time chart", "Time (In game updates)"
                , "Company value(net worth)", categoryDataset
                , PlotOrientation.VERTICAL, true, true, false);

        chartPanel = new ChartPanel(jFreeChart);

        plot = jFreeChart.getCategoryPlot();


        current_data_presentation = DataType.Nothing;
    }

    public void loadCompany_ValueHistory(Company company) {
        categoryDataset.clear();
        double value;
        for (int i = 0; i < company.company_value_history.size(); i++) {
            value = company.company_value_history.get(i);
            categoryDataset.addValue(value, KEY_ROW_COMPANY_VALUE, "" + i);
        }

        jFreeChart.setTitle(company.name + " : " + "Company Value Over Time");
        plot.getDomainAxis().setLabel("Time (game updates)");

        //XYPlot plot = (XYPlot) jFreeChart.getPlot();
        // plot.getRangeAxis().setLabel("Company's Value");


        current_company = company;
        current_data_presentation = DataType.Company_Value_Over_Time;
    }

    public void update() {

        if (current_company == null) {
            return;
        }
        if (current_data_presentation == DataType.Nothing) {
            return;
        }
        if (current_data_presentation == DataType.Company_Value_Over_Time) {
            updateChart_Company_Value_History();
        } else if (current_data_presentation == DataType.Company_Stock_Presentage_Growth) {
            updateChart_Company_Stock_Presentage_Growth();
        }
    }

    private void updateChart_Company_Stock_Presentage_Growth() {
        //TODO:
    }

    private void updateChart_Company_Value_History() {
        categoryDataset.addValue(
                current_company.current_company_value,
                "" + current_company.name,
                "" + Global.current_update_counter);
    }

    enum DataType {
        Nothing, Company_Value_Over_Time, Company_Stock_Presentage_Growth, Company_Share_Value
    }
}
