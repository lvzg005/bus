package com.company.bus.loaned.dbaccess.entity;

import java.util.Date;

public class SetRroleResource extends SetRroleResourceKey {
    private String lastUpdator;

    private Date lastUpdateTime;

    public String getLastUpdator() {
        return lastUpdator;
    }

    public void setLastUpdator(String lastUpdator) {
        this.lastUpdator = lastUpdator;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}