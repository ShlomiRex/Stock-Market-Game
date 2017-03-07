package main.gui_components.chart;

import main.init.Global;

import javax.swing.*;

/**
 * Created by Shlomi on 03/03/2017.
 */
public class Chart_InternalFrame extends JInternalFrame {

    public Chart chart;

    public Chart_InternalFrame() {
        super();

        chart = new Chart();

        Chart3 chart3 = new Chart3();

        add(chart.chartPanel);

        this.setVisible(true);
        this.pack();
        this.setClosable(true);

        Global.chart = chart;
    }

}
