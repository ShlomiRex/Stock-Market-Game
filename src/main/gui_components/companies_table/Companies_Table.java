package main.gui_components.companies_table;

import main.game.Company;
import main.init.Global;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Shlomi on 27/02/2017.
 */
public class Companies_Table extends JInternalFrame {
    private final int table_colID_at_compID = 0;
    private final int table_colID_at_compName = 1;
    private final int table_colID_at_stockPresentageGrowth = 2;
    private final int table_colID_at_compNetWorth = 3;
    //ALL TABLE VARIABLES
    protected JTable table;
    protected DefaultTableModel tmodel;
    private JPanel panel;

    public Companies_Table() {

        super("Companies", true, false, true, true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create the model which the JTable will use. This is the columns basically.
        tmodel = new DefaultTableModel(new String[][]{}, new String[]{
                "Comp. ID", "Com. Name", "Stock % Change", "Net Worth"});

        panel = new JPanel();
        table = new JTable(tmodel) {
            //User canno't edit the JTable.
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            //Each update, update the Stock % Change to either: White (0), Green(Positive), Red(Negative).
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

        //User canno't drag columns and customize the table.
        table.getTableHeader().setReorderingAllowed(false);

        //Add scrolling to table, if the table is too big or too small for data.
        JScrollPane jScrollPane = new JScrollPane(table,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane.setViewportView(table);

        this.add(jScrollPane);
        this.setVisible(true);
        this.table.setAutoCreateRowSorter(true);

        // add mouse listener
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                //if double click
                if (e.getClickCount() == 2) {
                    int rowClicked = table.rowAtPoint(e.getPoint());
                    Company companyToAdd = getCompanyByRow(rowClicked);

                    //add company to the chart.

                    //System.out.println("Clicked on company: " + companyToAdd.name);

                    if (companyToAdd != null)
                        Global.chart.addCompany(companyToAdd);
                }
            }
        });
    }

    /**
     * Add company to the JTable and update it,
     * @param c Array of strings which represent corresponding columns
     */
    public void addCompany(Company c) {
        //ADD COMPANY BY:
        //ID, NAME, STOCK % CHANGE, COMPANY VALUE
        //Stock % Change is 0 at the start.
        String[] data = new String[]{"" + c.id, c.name, "" + 0, "" + c.getNet_worth_string()};
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

    /**
     * @return Random integer from 0 to table size.
     */
    public int getRandomRowIndex() {
        return Global.random.nextInt(table.getRowCount());
    }

    /**
     *
     * @param id ID of Company. (ID = Variable in Company class)
     * @return Company object.
     */
    public Company getCompanyByID(int id) {
        return Global.companies.get(id);
    }

    /**
     *
     * @param row Row integer. (0 and above)
     * @return ID of company
     */
    public int getCompanyIDByRow(int row) {
        return Integer.parseInt((String) table.getValueAt(row, 0));
    }

    /**
     *
     * @return Company, if found. NULL if not found.
     */
    public Company getCompanyByRow(int row) {
        int compID = getCompanyIDByRow(row);
        for (int i = 0; i < Global.companies.size(); i++) {
            if (Global.companies.get(i).id == compID)
                return Global.companies.get(i);
        }
        return null;
    }

    public Company getRandomCompany() {
        int rowIndex = getRandomRowIndex();
        return getCompanyByID(rowIndex);

    }


}