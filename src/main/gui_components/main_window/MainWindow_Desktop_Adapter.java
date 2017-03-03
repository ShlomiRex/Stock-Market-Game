package main.gui_components.main_window;

import main.game.news.NewsFeed_Frame;
import main.gui_components.chart.Chart_InternalFrame;
import main.init.Global;

import javax.swing.*;

/**
 * Created by Shlomi on 03/03/2017.
 */
public class MainWindow_Desktop_Adapter extends JDesktopPane {

    public MainWindow_Desktop_Adapter() {
        super();

        addInternalFrame_CompaniesList();
        addInternalFrame_Chart();
        addInternalFrame_NewsFeed();

        setVisible(true);
    }

    private void addInternalFrame_NewsFeed() {
        //News feed frame
        JInternalFrame newsFeed = new NewsFeed_Frame();
        add(newsFeed);
        newsFeed.pack();
        newsFeed.setBounds(0, 455, 500, 500);
    }

    private void addInternalFrame_Chart() {
        //Chart frame
        Chart_InternalFrame chart_internalFrame = new Chart_InternalFrame();
        add(chart_internalFrame);
        chart_internalFrame.chart.loadCompany_ValueHistory(Global.companies.get(0));
        chart_internalFrame.pack();
        chart_internalFrame.setLocation(480, 0);
    }

    private void addInternalFrame_CompaniesList() {
        //Companies list frame
        JInternalFrame companies_list_internal_frame = Global.companies_table_frame;
        add(companies_list_internal_frame);
        companies_list_internal_frame.pack();
        companies_list_internal_frame.setLocation(0, 0);

    }
}
