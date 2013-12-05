package com.company.bus.loaned.dbaccess.entity;

import java.math.BigDecimal;

public class SetFroleFmenuKey {
    private BigDecimal funcRoleId;

    private BigDecimal funcMenuId;

    public BigDecimal getFuncRoleId() {
        return funcRoleId;
    }

    public void setFuncRoleId(BigDecimal funcRoleId) {
        this.funcRoleId = funcRoleId;
    }

    public BigDecimal getFuncMenuId() {
        return funcMenuId;
    }

    public void setFuncMenuId(BigDecimal funcMenuId) {
        this.funcMenuId = funcMenuId;
    }
}