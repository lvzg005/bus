package com.company.bus.loaned.dbaccess.entity;

import java.math.BigDecimal;

public class SetRgroupRroleKey {
    private BigDecimal rgroupId;

    private BigDecimal resourceRoleId;

    public BigDecimal getRgroupId() {
        return rgroupId;
    }

    public void setRgroupId(BigDecimal rgroupId) {
        this.rgroupId = rgroupId;
    }

    public BigDecimal getResourceRoleId() {
        return resourceRoleId;
    }

    public void setResourceRoleId(BigDecimal resourceRoleId) {
        this.resourceRoleId = resourceRoleId;
    }
}