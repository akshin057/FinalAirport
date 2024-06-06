package com.example.finalairport.usersData;

import java.util.HashMap;
import java.util.Map;

public class Info {
    private Map<String, Integer> info;

    public Info() {
        info = new HashMap<>();
    }

    public Map<String, Integer> getInfo() {
        return info;
    }

    public void setInfo(Map<String, Integer> info) {
        this.info = info;
    }

    public void addInfo(String string, Integer amount) {
        info.put(string, amount);
    }

    public String[] getStringInfo() {
        String[] strings = new String[info.size()];
        int i = 0;
        for (Map.Entry<String, Integer> entry : info.entrySet()) {
            strings[i++] = String.valueOf(entry.getValue());
        }
        return strings;
    }


}
