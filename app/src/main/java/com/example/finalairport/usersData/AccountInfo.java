package com.example.finalairport.usersData;

import java.util.Map;

public class AccountInfo extends Info{
    public AccountInfo(Map<String, Integer> map) {
        setInfo(map);
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
