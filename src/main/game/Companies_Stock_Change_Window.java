package main.game;

import core.Company;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;

/**
 * Created by Shlomi on 27/02/2017.
 */
public class Companies_Stock_Change_Window extends JFrame {
    private JPanel panel;
    //ALL TABLE VARIABLES
    private JTable table;
    private DefaultTableModel tmodel;
    private final int table_colID_at_compID = 0;
    private final int table_colID_at_compName = 1;
    private final int table_colID_at_stockPresentageGrowth = 2;
    private final int table_colID_at_compNetWorth = 3;

    public Companies_Stock_Change_Window() {

        super();

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                Game.running = false;
            }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tmodel = new DefaultTableModel( new String[][] {} ,new String[]{"Comp. ID","Com. Name","Change","Net Worth"} );

        panel = new JPanel();
        table = new JTable(tmodel){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.setAutoCreateRowSorter(true);

        JScrollPane jScrollPane = new JScrollPane(table);
        jScrollPane.setViewportView(table);

        //DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        //TableColumn tc = new TableColumn(table_colID_at_stockPresentageGrowth);
        //tc.getCellRenderer().getTableCellRendererComponent().setBackground(Color.GREEN);

        panel.add(jScrollPane);
        this.add(panel);
        this.pack();
        this.setVisible(true);


    }

    /**
     *
     * @param data Array of strings which represent corresponding columns
     */
    public void addCompany(String[] data) {
        tmodel.addRow( data );
    }

    /**
     * Update the JTable with company's values: Stock prices, net worth, ect.
     * @param company
     */
    public void updateCompanyAtTable(Company company) {
        /*
        1. Find the ROW of the company in JTable
        2. Update the ROW with all values
        3. Update the COLOR of the 'change in stock amount' to red/green
         */

        for(int i = 0; i < tmodel.getRowCount(); i++) {
            //Search for spesific ROW at table, search table for company with same id
            if(Integer.parseInt((String)tmodel.getValueAt(i,table_colID_at_compID)) == company.id) {
                //Values that don't change: ID,Name
                //Values that change: Stock prices, company net worth, stock presentage growth

                //Update stock % change
                tmodel.setValueAt(company.stock_presentage_growth, i, table_colID_at_stockPresentageGrowth);

                //Update company net worth
                tmodel.setValueAt(company.getNet_worth_string(), i, table_colID_at_compNetWorth);

                //Update color of cell
                //table.getCellRenderer(1,1).
            }

        }

    }


}

