package com.citics.intern;

import com.opencsv.bean.CsvBindByName;

public class Transaction {
    @CsvBindByName(column = "I_CODE")
    private String iCode;
    @CsvBindByName(column = "TRADE_DATE")
    private String date;
    // @csvBindByID // - This is for reading into beans
    @CsvBindByName(column = "TRANSACTION_TYPE")
    private String transactionType;
    @CsvBindByName(column = "CLEAN_TRANSACTION_PRICE")
    private double cleanTransactionPrice;
    @CsvBindByName(column = "DIRTY_TRANSACTION_PRICE")
    private double dirtyTransactionPrice;
    @CsvBindByName(column = "TRANSACTION_AMOUNT")
    private double transactionAmount;
    @CsvBindByName(column = "SETTLEMENT_DATE")
    private String settlementDate;
    @CsvBindByName(column = "TOTAL_SETTLEMENT_AMOUNT")
    private double totalSettlementAmount;

    public Transaction() {
    }

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

    @Override
    public String toString() {
        return "Transaction [iCode=" + iCode + ", date=" + date + ", transactionType=" + transactionType
                + ", cleanTransactionPrice=" + cleanTransactionPrice + ", dirtyTransactionPrice="
                + dirtyTransactionPrice + ", transactionAmount=" + transactionAmount + ", settlementDate="
                + settlementDate + ", totalSettlementAmount=" + totalSettlementAmount + "]";
    }

}
