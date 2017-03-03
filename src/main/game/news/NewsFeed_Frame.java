package main.game.news;

import javax.swing.*;

/**
 * Created by Shlomi on 01/03/2017.
 */
public final class NewsFeed_Frame extends JInternalFrame {


    public NewsFeed_Frame() {
        super("News", true, false, true, true);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        add(scrollPane);

        setVisible(true);

        News news = new News(textArea);
    }


}
