package com.citics.intern;

import com.opencsv.bean.CsvBindByName;

public class Transaction {
    private String iCode;
    // @CsvBindByName("TRADE_DATE")
    private String date;
    // @csvBindByID // - This is for reading into beans
    private String transactionType;
    private double cleanTransactionPrice;
    private double dirtyTransactionPrice;
    private double transactionAmount;
    private String settlementDate;
    private double totalSettlementAmount;

    public Transaction(String iCode, String date, String transactionType, double cleanTransactionPrice,
            double dirtyTransactionPrice,
            double transactionAmount, String settlementDate, double totalSettlementAmount) {
        this.iCode = iCode;
        this.date = date;
        this.transactionType = transactionType;
        this.cleanTransactionPrice = cleanTransactionPrice;
        this.dirtyTransactionPrice = dirtyTransactionPrice;
        this.transactionAmount = transactionAmount;
        this.settlementDate = settlementDate;
        this.totalSettlementAmount = totalSettlementAmount;
    }

    public String getICode() {
        return iCode;
    }

    public String getDate() {
        return date;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getCleanTransactionPrice() {
        return cleanTransactionPrice;
    }

    public double getDirtyTransactionPrice() {
        return dirtyTransactionPrice;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public String getSettlementDate() {
        return settlementDate;
    }

    public double getTotalSettlementAmount() {
        return totalSettlementAmount;
    }

}
