package main.gui_components.main_window;

import main.game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by Shlomi on 03/03/2017.
 */
public class MainWindow_JFrame extends JFrame {

    public MainWindow_JFrame() {


        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                Game.running = false;
            }
        });

        MainWindow_Desktop_Adapter desktop_adapter = new MainWindow_Desktop_Adapter();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(desktop_adapter, BorderLayout.CENTER);


        createMenuBar();


        //Maximize JFrame
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();


        JMenu menu_file = new JMenu("File");
        JMenuItem item_new_game = new JMenuItem("New game");
        JMenuItem item_save_game = new JMenuItem("Save game");
        JMenuItem item_load_game = new JMenuItem("Load save");
        menu_file.add(item_new_game);
        menu_file.add(item_save_game);
        menu_file.addSeparator();
        menu_file.add(item_load_game);

        JMenu menu_options = new JMenu("Options");
        JMenuItem item_look_and_feel = new JMenuItem(
                new AbstractAction("Change program's look and feel") {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String[] buttons_string = {"Cross platform", "System defaults"};
                            int returnValue = JOptionPane.showOptionDialog(null, "Choose look and feel",
                                    "User's choice", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons_string, buttons_string[0]);
                            //System.out.println("Return = " + returnValue);
                            if (returnValue == 0)
                                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                            else if (returnValue == 1)
                                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Error, can't do that action.");
                        }
                    }
                });

        menu_options.add(item_look_and_feel);


        JButton btn_about = new JButton("About");


        menuBar.add(menu_file);
        menuBar.add(menu_options);
        menuBar.add(btn_about);

        setJMenuBar(menuBar);
    }

}
