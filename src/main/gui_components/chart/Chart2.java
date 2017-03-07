package main.gui_components.chart;

import main.game.Company;
import main.init.Global;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shlomi on 02/03/2017.
 */
public final class Chart2 {

    private final ChartYDataType current_data_type;
    private JFreeChart chart;
    private ChartPanel chartPanel;
    private XYSeriesCollection xySeriesCollection;
    private List<Company> companies_displaying;

    /**
     * @param data_to_display Choose which data type to display in the chart.
     */
    public Chart2(ChartYDataType data_to_display) {
        init();

        current_data_type = data_to_display;

        createDataset();
    }

    private XYDataset createDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series1 = new XYSeries("Object 1");
        XYSeries series2 = new XYSeries("Object 2");
        XYSeries series3 = new XYSeries("Object 3");

        series1.add(1.0, 2.0);
        series1.add(2.0, 3.0);
        series1.add(3.0, 2.5);
        series1.add(3.5, 2.8);
        series1.add(4.2, 6.0);

        series2.add(2.0, 1.0);
        series2.add(2.5, 2.4);
        series2.add(3.2, 1.2);
        series2.add(3.9, 2.8);
        series2.add(4.6, 3.0);

        series3.add(1.2, 4.0);
        series3.add(2.5, 4.4);
        series3.add(3.8, 4.2);
        series3.add(4.3, 3.8);
        series3.add(4.5, 4.0);

        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.addSeries(series3);

        return dataset;
    }

    private void init() {
        xySeriesCollection = new XYSeriesCollection();
        chart = ChartFactory.createXYLineChart(
                "", "", "", xySeriesCollection, PlotOrientation.HORIZONTAL, true, true, false);
        chartPanel = new ChartPanel(chart);


        companies_displaying = new ArrayList<Company>();
    }

    private void addSeries(Company c) {
        MyXYSeries series = new MyXYSeries(c);

        if (current_data_type == ChartYDataType.Company_Value)
            series.add(Global.current_update_counter, c.current_company_value);

        //TODO: else if other data types.
        xySeriesCollection.addSeries(series);
        //xySeriesCollection.ad

        CategoryPlot plot = chart.getCategoryPlot();
        CategoryItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesVisible(0, false);

    }

    private XYSeries createSeries_CompanyValue() {
        XYSeries series_company_value = new XYSeries(ChartYDataType.Company_Value);
        series_company_value.add(1, 1);
        series_company_value.add(2, 2);
        series_company_value.add(3, 4);

        return series_company_value;
    }

    private XYSeries createDataset_StockValue() {
        XYSeries series_stock_value = new XYSeries(ChartYDataType.Stock_Value);
        series_stock_value.add(1, -1);
        series_stock_value.add(1, -2);
        series_stock_value.add(1, -4);

        return series_stock_value;
    }

    private void removeSeries(int company_id) {
        /*
        int series_id = findSeriesIDByCompanyID(company_id);
        if(series_id > -1)
            xySeriesCollection.removeSeries(series_id);
            */
    }

    private Company findCompany(int company_id) {
        for (int i = 0; i < companies_displaying.size(); i++) {
            if (company_id == companies_displaying.get(i).id)
                return companies_displaying.get(i);
        }
        return null;
    }

    /**
     * Adds company and displays it in the chart.
     */
    public void addCompany(Company c) {
        if (c == null)
            return;
        addSeries(c);
    }

    /**
     * Remove company from chart and update it.
     *
     * @param c
     */
    public void removeCompany(Company c) {
        if (c != null)
            removeSeries(c.id);
    }

    private void findSeriesIDByCompanyID(int comp_id) {
        for (int i = 0; i < xySeriesCollection.getSeriesCount(); i++) {
            //if(xySeriesCollection.getSeries(i).getKey())
        }
    }

    /**
     * Representing chart data type.
     */
    public enum ChartYDataType {
        Company_Value, Stock_Value
    }
}


