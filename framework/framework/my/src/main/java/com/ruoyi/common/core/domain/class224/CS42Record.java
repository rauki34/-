package com.ruoyi.common.core.domain.class224;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//压浆材料配合比设计试验检测记录
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CS42Record extends BaseEntity {

    private int id;
    private String jcdw; // 检测单位
    private String jlbh; // 记录编号
    private String gcmc; // 工程名称
    private String htd; // 合同段
    private String sgdw; // 施工单位
    private String jldw; // 监理单位
    private String gcbw; // 工程部位/用途
    private String ypxx; // 样品信息
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date jcrq; // 检测日期
    // 试验条件 温度：  ℃ ，相对湿度：  %
    private String cstj; //试验条件
    //    private String temperature; //温度
    //    private String humidity; //相对湿度
    private String jcyj; // 检测依据
    private String pdyj; // 判定依据
    private String zyyqsbmc; // 主要仪器设备名称及其编号

    // 表3数据 试拌调整压浆材料拌合物性能
    private float sjb3; // 水胶比
    private float lddcs; // 流动度 初始
    private float ldd30; // 流动度 30min
    private float ldd60; // 流动度 60min
    private float msl3; // 泌水率 3h
    private float msl24; // 泌水率 24h
    private float zypzl3; // 自由膨胀率 3h
    private float zypzl24; // 自由膨胀率 24h
    private float ylmsl22; // 压力泌水率 0.22MPa
    private float ylmsl36; // 压力泌水率 0.36MPa
    private String cyd; // 充盈度
    private float njsjcn; // 凝结时间 初凝
    private float njsjzn; // 凝结时间 终凝
    private String bz; // 备注

    // 表4数据 检验强度
    private float sjb4; // 水胶比
    private int sjsl; // 试件数量
    private String yhfs; // 养护方式
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date cxrq; // 成型日期
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date yjrq7;// 压件日期 7d
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date yjrq28;// 压件日期 28d
    private float kzqd7; // 抗折强度 7d
    private float kzqd28; // 抗折强度 28d
    private float kyqd7; // 抗压强度 7d
    private float kyqd28; // 抗压强度28d

    // 表5数据 确定配合比：每m3压浆材料各种材料用量(kg)
    private String sjb5; // 水胶比
    private String sn; // 水泥
    private String s; // 水
    private String wjj; // 外加剂
    private String wclname; // 外掺料名
    private String wcl; // 外掺料

    // 表6数据 压浆材料配合比校正
    private String llmd; // 理论密度
    private String scmd; // 实测密度
    private String jzxs; // 校正系数
    private String fjsm; // 附加声明

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJcdw() {
        return jcdw;
    }

    public void setJcdw(String jcdw) {
        this.jcdw = jcdw;
    }

    public String getJlbh() {
        return jlbh;
    }

    public void setJlbh(String jlbh) {
        this.jlbh = jlbh;
    }

    public String getGcmc() {
        return gcmc;
    }

    public void setGcmc(String gcmc) {
        this.gcmc = gcmc;
    }

    public String getHtd() {
        return htd;
    }

    public void setHtd(String htd) {
        this.htd = htd;
    }

    public String getSgdw() {
        return sgdw;
    }

    public void setSgdw(String sgdw) {
        this.sgdw = sgdw;
    }

    public String getJldw() {
        return jldw;
    }

    public void setJldw(String jldw) {
        this.jldw = jldw;
    }

    public String getGcbw() {
        return gcbw;
    }

    public void setGcbw(String gcbw) {
        this.gcbw = gcbw;
    }

    public String getYpxx() {
        return ypxx;
    }

    public void setYpxx(String ypxx) {
        this.ypxx = ypxx;
    }

    public Date getJcrq() {
        return jcrq;
    }

    public void setJcrq(Date jcrq) {
        this.jcrq = jcrq;
    }

    public String getCstj() {
        return cstj;
    }

    public void setCstj(String cstj) {
        this.cstj = cstj;
    }

    public String getJcyj() {
        return jcyj;
    }

    public void setJcyj(String jcyj) {
        this.jcyj = jcyj;
    }

    public String getPdyj() {
        return pdyj;
    }

    public void setPdyj(String pdyj) {
        this.pdyj = pdyj;
    }

    public String getZyyqsbmc() {
        return zyyqsbmc;
    }

    public void setZyyqsbmc(String zyyqsbmc) {
        this.zyyqsbmc = zyyqsbmc;
    }

    public float getSjb3() {
        return sjb3;
    }

    public void setSjb3(float sjb3) {
        this.sjb3 = sjb3;
    }

    public float getLddcs() {
        return lddcs;
    }

    public void setLddcs(float lddcs) {
        this.lddcs = lddcs;
    }

    public float getLdd30() {
        return ldd30;
    }

    public void setLdd30(float ldd30) {
        this.ldd30 = ldd30;
    }

    public float getLdd60() {
        return ldd60;
    }

    public void setLdd60(float ldd60) {
        this.ldd60 = ldd60;
    }

    public float getMsl3() {
        return msl3;
    }

    public void setMsl3(float msl3) {
        this.msl3 = msl3;
    }

    public float getMsl24() {
        return msl24;
    }

    public void setMsl24(float msl24) {
        this.msl24 = msl24;
    }

    public float getZypzl3() {
        return zypzl3;
    }

    public void setZypzl3(float zypzl3) {
        this.zypzl3 = zypzl3;
    }

    public float getZypzl24() {
        return zypzl24;
    }

    public void setZypzl24(float zypzl24) {
        this.zypzl24 = zypzl24;
    }

    public float getYlmsl22() {
        return ylmsl22;
    }

    public void setYlmsl22(float ylmsl22) {
        this.ylmsl22 = ylmsl22;
    }

    public float getYlmsl36() {
        return ylmsl36;
    }

    public void setYlmsl36(float ylmsl36) {
        this.ylmsl36 = ylmsl36;
    }

    public String getCyd() {
        return cyd;
    }

    public void setCyd(String cyd) {
        this.cyd = cyd;
    }

    public float getNjsjcn() {
        return njsjcn;
    }

    public void setNjsjcn(float njsjcn) {
        this.njsjcn = njsjcn;
    }

    public float getNjsjzn() {
        return njsjzn;
    }

    public void setNjsjzn(float njsjzn) {
        this.njsjzn = njsjzn;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public float getSjb4() {
        return sjb4;
    }

    public void setSjb4(float sjb4) {
        this.sjb4 = sjb4;
    }

    public int getSjsl() {
        return sjsl;
    }

    public void setSjsl(int sjsl) {
        this.sjsl = sjsl;
    }

    public String getYhfs() {
        return yhfs;
    }

    public void setYhfs(String yhfs) {
        this.yhfs = yhfs;
    }

    public Date getCxrq() {
        return cxrq;
    }

    public void setCxrq(Date cxrq) {
        this.cxrq = cxrq;
    }

    public Date getYjrq7() {
        return yjrq7;
    }

    public void setYjrq7(Date yjrq7) {
        this.yjrq7 = yjrq7;
    }

    public Date getYjrq28() {
        return yjrq28;
    }

    public void setYjrq28(Date yjrq28) {
        this.yjrq28 = yjrq28;
    }

    public float getKzqd7() {
        return kzqd7;
    }

    public void setKzqd7(float kzqd7) {
        this.kzqd7 = kzqd7;
    }

    public float getKzqd28() {
        return kzqd28;
    }

    public void setKzqd28(float kzqd28) {
        this.kzqd28 = kzqd28;
    }

    public float getKyqd7() {
        return kyqd7;
    }

    public void setKyqd7(float kyqd7) {
        this.kyqd7 = kyqd7;
    }

    public float getKyqd28() {
        return kyqd28;
    }

    public void setKyqd28(float kyqd28) {
        this.kyqd28 = kyqd28;
    }

    public String getSjb5() {
        return sjb5;
    }

    public void setSjb5(String sjb5) {
        this.sjb5 = sjb5;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getWjj() {
        return wjj;
    }

    public void setWjj(String wjj) {
        this.wjj = wjj;
    }

    public String getWclname() {
        return wclname;
    }

    public void setWclname(String wclname) {
        this.wclname = wclname;
    }

    public String getWcl() {
        return wcl;
    }

    public void setWcl(String wcl) {
        this.wcl = wcl;
    }

    public String getLlmd() {
        return llmd;
    }

    public void setLlmd(String llmd) {
        this.llmd = llmd;
    }

    public String getScmd() {
        return scmd;
    }

    public void setScmd(String scmd) {
        this.scmd = scmd;
    }

    public String getJzxs() {
        return jzxs;
    }

    public void setJzxs(String jzxs) {
        this.jzxs = jzxs;
    }

    public String getFjsm() {
        return fjsm;
    }

    public void setFjsm(String fjsm) {
        this.fjsm = fjsm;
    }

    @Override
    public String toString() {
        return "CS42Record{" +
                "id=" + id +
                ", jcdw='" + jcdw + '\'' +
                ", jlbh='" + jlbh + '\'' +
                ", gcmc='" + gcmc + '\'' +
                ", htd='" + htd + '\'' +
                ", sgdw='" + sgdw + '\'' +
                ", jldw='" + jldw + '\'' +
                ", gcbw='" + gcbw + '\'' +
                ", ypxx='" + ypxx + '\'' +
                ", jcrq=" + jcrq +
                ", cstj='" + cstj + '\'' +
                ", jcyj='" + jcyj + '\'' +
                ", pdyj='" + pdyj + '\'' +
                ", zyyqsbmc='" + zyyqsbmc + '\'' +
                ", sjb3=" + sjb3 +
                ", lddcs=" + lddcs +
                ", ldd30=" + ldd30 +
                ", ldd60=" + ldd60 +
                ", msl3=" + msl3 +
                ", msl24=" + msl24 +
                ", zypzl3=" + zypzl3 +
                ", zypzl24=" + zypzl24 +
                ", ylmsl22=" + ylmsl22 +
                ", ylmsl36=" + ylmsl36 +
                ", cyd='" + cyd + '\'' +
                ", njsjcn=" + njsjcn +
                ", njsjzn=" + njsjzn +
                ", bz='" + bz + '\'' +
                ", sjb4=" + sjb4 +
                ", sjsl=" + sjsl +
                ", yhfs='" + yhfs + '\'' +
                ", cxrq=" + cxrq +
                ", yjrq7=" + yjrq7 +
                ", yjrq28=" + yjrq28 +
                ", kzqd7=" + kzqd7 +
                ", kzqd28=" + kzqd28 +
                ", kyqd7=" + kyqd7 +
                ", kyqd28=" + kyqd28 +
                ", sjb5='" + sjb5 + '\'' +
                ", sn='" + sn + '\'' +
                ", s='" + s + '\'' +
                ", wjj='" + wjj + '\'' +
                ", wclname='" + wclname + '\'' +
                ", wcl='" + wcl + '\'' +
                ", llmd='" + llmd + '\'' +
                ", scmd='" + scmd + '\'' +
                ", jzxs='" + jzxs + '\'' +
                ", fjsm='" + fjsm + '\'' +
                '}';
    }
}

