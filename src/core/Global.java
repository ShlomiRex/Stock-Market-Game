package core;

import main.game.PlayerProfile;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

/**
 * Created by Shlomi on 26/02/2017.
 */
public class Global {
    /**
     * Just random variable. Use it as you wish. (It's for not creating many random variables everywhere and messy..)
     */
    public static Random random;
    /**
     * Array of size 100. Represent frequencies of %.
     */
    public static int[] big_stocks_frequencies;
    public static List<Company> companies;
    public static PlayerProfile profile;
    /**
     * Array of size 10. Represent origional resource data.
     */
    public static int[] src_big_stocks_frequencies;
    public static DecimalFormat stock_change_format;

}
