package main.gui_components.chart;

import javax.swing.*;

/**
 * Created by Shlomi on 03/03/2017.
 */
public class Chart_InternalFrame2 extends JInternalFrame {

    public Chart2 chart;

    public Chart_InternalFrame2() {
        super();

        //chart = new Chart2();

        add(chart.getChartPanel());

        this.setVisible(true);
        this.pack();
        this.setClosable(false);
        this.setMinimumSize(this.getSize());
        this.setMaximumSize(this.getSize());


        //Global.chart = chart;
    }

}
