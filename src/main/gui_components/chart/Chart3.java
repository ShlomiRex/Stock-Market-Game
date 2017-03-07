package main.gui_components.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.DynamicTimeSeriesCollection;
import org.jfree.data.time.Second;
import org.jfree.data.xy.XYDataset;

import java.util.Random;

/**
 * Created by Shlomi on 05/03/2017.
 */
public class Chart3 {

    private static final int NUM_OF_DATA_POINTS_X = 120;
    private static final float MINMAX_Y = 100;
    private static final Random random = new Random();

    private static final String TITLE = "Dynamic Series";
    private static final String START = "Start";
    private static final String STOP = "Stop";
    private static final int COUNT = 2 * 60;

    public Chart3() {
        final DynamicTimeSeriesCollection dataset =
                new DynamicTimeSeriesCollection(1, NUM_OF_DATA_POINTS_X, new Second());

        dataset.setTimeBase(new Second(0, 0, 0, 1, 1, 2017));
        dataset.addSeries(randomData(), 0, "Company A");
        JFreeChart chart = createChart(dataset);

        t
    }

    private JFreeChart createChart(final XYDataset dataset) {
        final JFreeChart result = ChartFactory.createTimeSeriesChart(
                TITLE, "hh:mm:ss", "milliVolts", dataset, true, true, false);
        final XYPlot plot = result.getXYPlot();
        ValueAxis domain = plot.getDomainAxis();
        domain.setAutoRange(true);
        ValueAxis range = plot.getRangeAxis();
        range.setRange(-MINMAX_Y, MINMAX_Y);
        return result;
    }

    private float[] randomData() {
        float[] a = new float[NUM_OF_DATA_POINTS_X];
        for (int i = 0; i < a.length; i++) {
            a[i] = randomValue();
        }
        return a;
    }

    private float randomValue() {
        return (float) (random.nextGaussian() * MINMAX_Y / 3);
    }
}
