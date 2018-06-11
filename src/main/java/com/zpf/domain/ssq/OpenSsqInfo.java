package com.zpf.domain.ssq;

import java.util.Date;

public class OpenSsqInfo {

    private Long id;

    private Byte red1;

    private Byte red2;

    private Byte red3;

    private Byte red4;

    private Byte red5;

    private Byte red6;

    private Byte blue;

    private String expect;

    private String openTime;

    private Date created;

    private Date modified;

    private String operator;

    private Byte yn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getRed1() {
        return red1;
    }

    public void setRed1(Byte red1) {
        this.red1 = red1;
    }

    public Byte getRed2() {
        return red2;
    }

    public void setRed2(Byte red2) {
        this.red2 = red2;
    }

    public Byte getRed3() {
        return red3;
    }

    public void setRed3(Byte red3) {
        this.red3 = red3;
    }

    public Byte getRed4() {
        return red4;
    }

    public void setRed4(Byte red4) {
        this.red4 = red4;
    }

    public Byte getRed5() {
        return red5;
    }

    public void setRed5(Byte red5) {
        this.red5 = red5;
    }

    public Byte getRed6() {
        return red6;
    }

    public void setRed6(Byte red6) {
        this.red6 = red6;
    }

    public Byte getBlue() {
        return blue;
    }

    public void setBlue(Byte blue) {
        this.blue = blue;
    }

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect == null ? null : expect.trim();
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime == null ? null : openTime.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Byte getYn() {
        return yn;
    }

    public void setYn(Byte yn) {
        this.yn = yn;
    }

    @Override
    public String toString() {
        return
            "红球：" + red1 +
                ", " + red2 +
                ", " + red3 +
                ", " + red4 +
                ", " + red5 +
                ", " + red6 +
                " 蓝球：" + blue +
                " 开奖号：'" + expect + '\'' +
                " 开奖日期：'" + openTime + '\'';
    }
}