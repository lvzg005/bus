package com.company.bus.loaned.dbaccess.entity;

import java.math.BigDecimal;

public class SetRroleResourceKey {
    private BigDecimal resourceRoleId;

    private BigDecimal resourceId;

    public BigDecimal getResourceRoleId() {
        return resourceRoleId;
    }

    public void setResourceRoleId(BigDecimal resourceRoleId) {
        this.resourceRoleId = resourceRoleId;
    }

    public BigDecimal getResourceId() {
        return resourceId;
    }

    public void setResourceId(BigDecimal resourceId) {
        this.resourceId = resourceId;
    }
}