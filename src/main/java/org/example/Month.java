package org.example;

import java.util.HashMap;
import java.util.Map;

public class Month {
    private String nameMonth;
    private Map<String, Boolean> namesOfServicesCheck = new HashMap<>();

    public Month(String nameMonth) {
        this.nameMonth = nameMonth;
    }

    public void setNamesOfServicesCheck(String namesOfServices, Boolean condition) {
        namesOfServicesCheck.put(namesOfServices, condition);
    }

    public Boolean getNamesOfServicesCheck(String key) {
        return namesOfServicesCheck.get(key);
    }
}
