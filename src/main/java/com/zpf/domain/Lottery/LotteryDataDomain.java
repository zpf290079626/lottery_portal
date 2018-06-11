package com.zpf.domain.Lottery;

public class LotteryDataDomain {
    String expect;
    String opencode;
    String opentime;
    String opentimestamp;

    /**
     * 06,07,12,16,22,25+07
     * @return
     */
    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }

    public String getOpencode() {
        return opencode;
    }

    public void setOpencode(String opencode) {
        this.opencode = opencode;
    }

    public String getOpentime() {
        return opentime;
    }

    public void setOpentime(String opentime) {
        this.opentime = opentime;
    }

    public String getOpentimestamp() {
        return opentimestamp;
    }

    public void setOpentimestamp(String opentimestamp) {
        this.opentimestamp = opentimestamp;
    }
}
