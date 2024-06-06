package com.example.finalairport.usersData;

import java.util.Map;

public class PassengersInfo extends Info{
    private Map<String, Integer> passengers;

    public PassengersInfo(Map<String, Integer> passengers) {
        this.passengers = passengers;
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
    public void addInfo(String time, Integer passengers) {
        super.addInfo(time, passengers);
    }
    @Override
    public String[] getStringInfo() {
        return super.getStringInfo();
    }
}
