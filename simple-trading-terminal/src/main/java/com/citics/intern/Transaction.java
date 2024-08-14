package com.citics.intern;

import java.time.LocalDate;

import com.opencsv.bean.CsvBindByName;

public class Transaction implements Comparable<Transaction> {
    @CsvBindByName(column = "I_CODE")
    private String iCode;
    @CsvBindByName(column = "TRADE_DATE")
    private String tradeDate;
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
    @CsvBindByName(column = "TRANSACTION_IDENTIFIER")
    private final int transactionIdentifier;

    private static int currentTransactionIdentifier = 0;

    public Transaction() {
        transactionIdentifier = currentTransactionIdentifier++;
    }

    public Transaction(String iCode, String tradeDate, String transactionType, double cleanTransactionPrice,
            double dirtyTransactionPrice,
            double transactionAmount, String settlementDate, double totalSettlementAmount) {
        this.iCode = iCode;
        this.tradeDate = tradeDate;
        this.transactionType = transactionType;
        this.cleanTransactionPrice = cleanTransactionPrice;
        this.dirtyTransactionPrice = dirtyTransactionPrice;
        this.transactionAmount = transactionAmount;
        this.settlementDate = settlementDate;
        this.totalSettlementAmount = totalSettlementAmount;
        transactionIdentifier = currentTransactionIdentifier++;
    }

    // TODO: Decide how to parse the date and add a get date thingy. Ensure that the
    // TODO: date is able to be compared

    @Override
    public int compareTo(Transaction rhsTransaction) {
        LocalDate localDate1 = this.convertToDate();
        LocalDate localDate2 = rhsTransaction.convertToDate();
        if (localDate1.isBefore(localDate2)) {
            return -1;
        } else if (localDate1.isAfter(localDate2)) {
            return 1;
        } else {
            return 0;
        }
    }

    public LocalDate convertToDate() {
        String date = this.getTradeDate();
        String[] splitDate = date.replaceAll("[-/,.]", " ").split(" ");

        // * Assuming that the date passed in will be MM-DD-YYYY
        int mm = Integer.parseInt(splitDate[0]);
        int dd = Integer.parseInt(splitDate[1]);
        int yyyy = Integer.parseInt(splitDate[2]);
        return LocalDate.of(yyyy, mm, dd);

    }

    public String getICode() {
        return iCode;
    }

    public String getTradeDate() {
        return tradeDate;
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

    public int getTransactionIdentifier() {
        return transactionIdentifier;
    }

    @Override
    public String toString() {
        return "Transaction [iCode=" + iCode + ", tradeDate=" + tradeDate + ", transactionType=" + transactionType
                + ", cleanTransactionPrice=" + cleanTransactionPrice + ", dirtyTransactionPrice="
                + dirtyTransactionPrice + ", transactionAmount=" + transactionAmount + ", settlementDate="
                + settlementDate + ", totalSettlementAmount=" + totalSettlementAmount + ", transactionIdentifier="
                + transactionIdentifier + "]";
    }

}
