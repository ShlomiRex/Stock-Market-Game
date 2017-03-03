package main.gui_components;

import main.game.Company;
import main.init.Global;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by Shlomi on 27/02/2017.
 */
public class Companies_Table_Frame extends JInternalFrame {
    private final int table_colID_at_compID = 0;
    private final int table_colID_at_compName = 1;
    private final int table_colID_at_stockPresentageGrowth = 2;
    private final int table_colID_at_compNetWorth = 3;
    private JPanel panel;
    //ALL TABLE VARIABLES
    private JTable table;
    private DefaultTableModel tmodel;

    public Companies_Table_Frame() {

        super("Companies", true, false, true, true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tmodel = new DefaultTableModel(new String[][]{}, new String[]{"Comp. ID", "Com. Name", "Change", "Net Worth"});

        panel = new JPanel();
        table = new JTable(tmodel) {
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

        table.setAutoCreateRowSorter(true);
        table.getTableHeader().setReorderingAllowed(false);

        JScrollPane jScrollPane = new JScrollPane(table,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane.setViewportView(table);

        //DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        //TableColumn tc = new TableColumn(table_colID_at_stockPresentageGrowth);
        //tc.getCellRenderer().getTableCellRendererComponent().setBackground(Color.GREEN);

        //panel.add(jScrollPane);
        this.add(jScrollPane);
        this.setVisible(true);
        this.table.setAutoCreateRowSorter(true);

    }

    /**
     * @param data Array of strings which represent corresponding columns
     */
    public void addCompany(String[] data) {
        tmodel.addRow(data);
    }

    /**
     * Update the JTable with company's values: Stock prices, net worth, ect.
     *
     * @param company
     */
    public void updateCompanyAtTable(Company company) {
        /*
        1. Find the ROW of the company in JTable
        2. Update the ROW with all values
        3. Update the COLOR of the 'change in stock amount' to red/green
         */

        for (int i = 0; i < tmodel.getRowCount(); i++) {
            //Search for spesific ROW at table, search table for company with same id
            if (Integer.parseInt((String) tmodel.getValueAt(i, table_colID_at_compID)) == company.id) {
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

    public int getRandomRowIndex() {
        return Global.random.nextInt(table.getRowCount());
    }

    public Company getCompanyByID(int id) {
        return Global.companies.get(id);
    }

    public int getCompanyIDByRowIndex(int row) {
        return Integer.parseInt((String) table.getValueAt(row, 0));
    }

    public Company getRandomCompany() {
        int rowIndex = getRandomRowIndex();
        return getCompanyByID(rowIndex);

    }
}