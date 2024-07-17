package com.citics.intern;

import com.opencsv.bean.CsvBindByName;

public class Instrument {
    // - I don't think the directory for the instrument is necessary
    @CsvBindByName(column = "\"I_CODE\"")
    private String iCode;
    @CsvBindByName(column = "\"A_TYPE\"")
    private String assetType;
    @CsvBindByName(column = "\"M_TYPE\"")
    private String marketType;
    @CsvBindByName(column = "\"B_NAME\"")
    private String name;
    @CsvBindByName(column = "\"B_COUPON\"")
    private String coupon;
    @CsvBindByName(column = "\"B_ISSUER\"")
    private String issuer;
    @CsvBindByName(column = "\"B_ISSUE_DATE\"")
    private String dateIssued; // - I don't think a new Date class needs to be used in the Instrument class.
                               // - It might be useful in the Transaction class though. Not sure.
    @CsvBindByName(column = "\"B_MTR_DATE\"")
    private String dateMatured;
    @CsvBindByName(column = "\"B_PAR_VALUE\"")
    private String faceValue;
    @CsvBindByName(column = "\"B_DAYCOUNT\"")
    private String interestRule;

    public Instrument() {
    }

    // public Instrument(String iCode, String assetType, String marketType, String
    // name, String coupon, String issuer,
    // String dateIssued, String dateMatured, String faceValue, String interestRule)
    // {
    // this.iCode = iCode;
    // this.assetType = assetType;
    // this.marketType = marketType;
    // this.name = name;
    // this.coupon = coupon;
    // this.issuer = issuer;
    // this.dateIssued = dateIssued;
    // this.dateMatured = dateMatured;
    // this.faceValue = faceValue;
    // this.interestRule = interestRule;
    // }

    @Override
    public String toString() {
        return "Instrument [iCode=" + iCode + ", assetType=" + assetType + ", marketType=" + marketType + ", name="
                + name + ", coupon=" + coupon + ", issuer=" + issuer + ", dateIssued=" + dateIssued + ", dateMatured="
                + dateMatured + ", faceValue=" + faceValue + ", interestRule=" + interestRule + "]";
    }

    public String getICode() {
        return iCode;
    }

    public void setICode(String iCode) {
        this.iCode = iCode;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getMarketType() {
        return marketType;
    }

    public void setMarketType(String marketType) {
        this.marketType = marketType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(String dateIssued) {
        this.dateIssued = dateIssued;
    }

    public String getDateMatured() {
        return dateMatured;
    }

    public void setDateMatured(String dateMatured) {
        this.dateMatured = dateMatured;
    }

    public String getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(String faceValue) {
        this.faceValue = faceValue;
    }

    public String getInterestRule() {
        return interestRule;
    }

    public void setInterestRule(String interestRule) {
        this.interestRule = interestRule;
    }

    // - index 7 = DATE_MATURED
    // - index 8 = FACE_VALUE
    // - index 9 = INTEREST_RULE

}
