package am.andranik.simplelist.models;

import am.andranik.simplelist.utils.StringUtils;

/**
 * Created by andranik on 9/18/17.
 */

public class Person {

    public static Person create(String fName, String lName){
        return new Person(StringUtils.capitalizeFirstLetter(fName), StringUtils.capitalizeFirstLetter(lName));
    }

    private String fName;
    private String lName;

    private Person(String fName, String lName) {
        this.fName = fName;
        this.lName = lName;
    }

    public String getFormattedName(){
        return String.format("%s, %s", lName, fName);
    }
}
