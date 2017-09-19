package am.andranik.simplelist.utils;

/**
 * Created by andranik on 9/18/17.
 */

public class StringUtils {
    public static String capitalizeFirstLetter(String input){
        if(input.length() > 0){
            return input.substring(0, 1).toUpperCase() + input.substring(1);
        }
        return input;
    }
}
