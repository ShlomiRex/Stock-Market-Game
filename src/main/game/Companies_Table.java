package main.game;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by Shlomi on 27/02/2017.
 */
public final class Companies_Table extends JPanel {

    private static DefaultTableModel tmodel = new DefaultTableModel(new String[][]{}, new String[]{"Comp. ID", "Com. Name", "Change %", "Net Worth"});
    private final int table_colID_at_compID = 0;
    private final int table_colID_at_compName = 1;
    private final int table_colID_at_stockPresentageGrowth = 2;
    private final int table_colID_at_compNetWorth = 3;
    //ALL TABLE VARIABLES
    public JTable table;
    public JScrollPane jScrollPane;

    public Companies_Table() {
        super();

        table = new JTable() {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }


            public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int columnIndex) {
                Component componenet = super.prepareRenderer(renderer, rowIndex, columnIndex);

                if (columnIndex == table_colID_at_stockPresentageGrowth) {
                    double value = new Double(getValueAt(rowIndex, columnIndex).toString());
                    if (value < 0)
                        componenet.setBackground(Color.RED);
                    else if (value == 0)
                        componenet.setBackground(Color.WHITE);
                    else
                        componenet.setBackground(Color.GREEN);
                } else
                    componenet.setBackground(Color.WHITE);

                return componenet;
            }

        };

        //table.setAutoCreateRowSorter(true);

        jScrollPane = new JScrollPane(table,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setViewportView(table);

        add(table);
        setVisible(true);

        JFrame frame = new JFrame("Companies_Table");
        frame.add(this);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();

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

