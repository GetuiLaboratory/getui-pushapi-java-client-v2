package com.getui.push.v2.sdk.dto.req;

import java.util.HashMap;

public class Strategy extends HashMap<String, Integer> {
    public final static String def = "default";
    public final static String ios = "ios";
    public final static String hw = "hw";
    public final static String xm = "xm";
    public final static String mz = "mz";
    public final static String op = "op";
    public final static String vv = "vv";
    public final static String st = "st";
    public final static String hx = "hx";
    public final static String hwq = "hwq";
    public final static String ho = "ho";
    public final static String hoshw = "hoshw";

    public Integer getDef() {
        return super.get(Strategy.def);
    }

    public void setDef(Integer def) {
        super.put(Strategy.def, def);
    }

    public Integer getIos() {
        return super.get(Strategy.ios);
    }

    public void setIos(Integer ios) {
        super.put(Strategy.ios, ios);
    }

    public Integer getHw() {
        return super.get(Strategy.hw);
    }

    public void setHw(Integer hw) {
        super.put(Strategy.hw, hw);
    }

    public Integer getXm() {
        return super.get(Strategy.xm);
    }

    public void setXm(Integer xm) {
        super.put(Strategy.xm, xm);
    }

    public Integer getMz() {
        return super.get(Strategy.mz);
    }

    public void setMz(Integer mz) {
        super.put(Strategy.mz, mz);
    }

    public Integer getOp() {
        return super.get(Strategy.op);
    }

    public void setOp(Integer op) {
        super.put(Strategy.op, op);
    }

    public Integer getVv() {
        return super.get(Strategy.vv);
    }

    public void setVv(Integer vv) {
        super.put(Strategy.vv, vv);
    }

    public Integer getSt() {
        return super.get(Strategy.st);
    }

    public void setSt(Integer st) {
        super.put(Strategy.st, st);
    }

    public Integer getHx() {
        return super.get(Strategy.hx);
    }

    public void setHx(Integer hx) {
        super.put(Strategy.hx, hx);
    }

    public Integer getHwq() {
        return super.get(Strategy.hwq);
    }

    public void setHwq(Integer hwq) {
        super.put(Strategy.hwq, hwq);
    }

    public Integer getHo() {
        return super.get(Strategy.ho);
    }

    public void setHo(Integer ho) {
        super.put(Strategy.ho, ho);
    }

    public Integer getHoshw() {
        return super.get(Strategy.hoshw);
    }

    public void setHoshw(Integer hoshw) {
        super.put(Strategy.hoshw, hoshw);
    }

}
