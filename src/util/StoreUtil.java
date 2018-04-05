package util;

import java.text.DecimalFormat;

/**
 * Author: Adam
 * Utility class for the photography store.
 */
public class StoreUtil {

    /**
     * Checks if a given code is a valid product code
     * @param code the product code to be checked
     * @return true if the parameter is a valid product code
     */
    public static boolean isValidProductCode(String code) {
        return code != null && code.length() == 4 && code.startsWith("00");
    }

    /**
     * Converts a given double to a two-decimal place format
     * @param d the double to be converted
     * @return the parameter rounded to two decimal places
     */
    public static double toMoneyFormat(double d) {
        DecimalFormat money = new DecimalFormat("#.##");
        return Double.valueOf(money.format(d));
    }
}
