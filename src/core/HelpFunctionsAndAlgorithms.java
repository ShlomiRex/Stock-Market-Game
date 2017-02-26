package core;

/**
 * Created by Shlomi on 26/02/2017.
 */
abstract public class HelpFunctionsAndAlgorithms {

    public static double getRandomStockChange() {
        int big_digit = StockMethods.getRandomBigStockChange();
        double small_double = StockMethods.getRandomLittleStockChange();
        //System.out.println("Big: " + big_digit + "  Little: " + small_double +"\n");
        double result = big_digit + small_double;

        return Double.parseDouble(Global.stock_change_format.format(result));
    }

    interface StockMethods {

        /**
         *
         * @return Format: integer, 0-9
         */
        public static int getRandomBigStockChange() {

            int index = Global.random.nextInt(100);
            int value = Global.big_stocks_frequencies[index];
            //Point to random value at random index.

            //Then find the DIGIT by VALUE (Digit represent index of src_big)

            //Find 'key' or 'index' of src_big_stocks to represent the DIGIT
            for(int i = 0; i < 10; i ++) {
                if(Global.src_big_stocks_frequencies[i] == value) {
                    value = i;
                    break;
                }
            }

            //Profit!
            if(Global.random.nextBoolean()) {
                return value;
            }

            //Looses
            else {
                return (-value);
            }
        }


        /**
         *
         * @return Format: double, 0.0 - 0.9
         */
        public static double getRandomLittleStockChange() {
            int value = Global.random.nextInt(10);

            //Profit!
            if(Global.random.nextBoolean()) {
                return (value/10.0);
            }

            //Looses
            else {
                return (-(value/10.0));
            }
        }
    }


}
