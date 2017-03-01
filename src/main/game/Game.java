package main.game;

/**
 * Created by Shlomi on 26/02/2017.
 */
import main.init.Global;
import main.init.LoadGame;

import java.io.IOException;

public final class Game implements Runnable {

    public static boolean running;



    public Game() {

        running = true;
        try {
            //Here we load ALL the resources, put in memory stuff, initalizing everything that the game needs to work.
            new LoadGame();
            //new MainWindow();
        } catch (IOException e) {
            e.printStackTrace();
            running = false;
        }

        run();
    }

    @Override
    public void run() {
        //I want 1 update every 5 seconds. 1 nanosecond = 10^(-9) That's 9 zeros which is billion
        final long updateAfter = 5000000000L;
        long nano = System.nanoTime(), deltaNano;

        while(running) {
            deltaNano = System.nanoTime() - nano;
            if( ! (deltaNano > updateAfter) ) {

                try {
                    //We don't need to check every so often the delta of nano seconds. If its low, sleep 800 miliseconds.
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                continue;
            }
            nano = System.nanoTime();
            //System.out.println("Update. Delta: " + deltaNano);
            updateGame();

        }

        prepareToExit();
        exitGame();
    }

    /**
     * Do stuff before exiting the game
     */
    private void prepareToExit() {
    }

    private void updateGame() {

        for(Company c : Global.companies)
            c.update();
    }

    private void exitGame() {
    }






}
