package com.company.bus.loaned.dbaccess.entity;

import java.math.BigDecimal;

public class SetUserRgroupKey {
    private BigDecimal userId;

    private BigDecimal rgroupId;

    public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    public BigDecimal getRgroupId() {
        return rgroupId;
    }

    public void setRgroupId(BigDecimal rgroupId) {
        this.rgroupId = rgroupId;
    }
}