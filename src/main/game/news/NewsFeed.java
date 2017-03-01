package main.game.news;

import javax.swing.*;

/**
 * Created by Shlomi on 01/03/2017.
 */
public class NewsFeed extends JPanel {

    public NewsFeed() {
        super();

        JTextArea textArea = new JTextArea("This is shlomi");
        JScrollPane scrollPane = new JScrollPane(textArea);

        add(textArea);

        setVisible(true);
    }

}
