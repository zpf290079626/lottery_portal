package com.zpf.domain.Lottery;

import java.util.List;

public class LotteryDomain {
    int rows;
    String code;
    String info;
    List<LotteryDataDomain> data;

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<LotteryDataDomain> getData() {
        return data;
    }

    public void setData(List<LotteryDataDomain> data) {
        this.data = data;
    }
}
