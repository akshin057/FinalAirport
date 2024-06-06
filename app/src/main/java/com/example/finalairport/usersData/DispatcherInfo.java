package com.example.finalairport.usersData;

import java.util.Map;

public class DispatcherInfo extends Info {
    private Map<String, Integer> planes;

    public DispatcherInfo(Map<String, Integer> planes) {
        this.planes = planes;
    }

    @Override
    public Map<String, Integer> getInfo() {
        return super.getInfo();
    }

    @Override
    public void setInfo(Map<String, Integer> info) {
        super.setInfo(info);
    }

    @Override
    public String[] getStringInfo() {
        return super.getStringInfo();
    }
}
