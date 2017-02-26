package main.game;

/**
 * Created by Shlomi on 26/02/2017.
 */
import core.Company;
import core.Global;
import core.HelpFunctionsAndAlgorithms;
import main.init.Resource_Factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Game implements Runnable{

    public boolean running = false;



    public Game() {

        running = true;
        try {
            //Here we load ALL the resources, put in memory stuff, initalizing everything that the game needs to work.
            new Resource_Factory();
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
