package com.citics.intern;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.Reader;

import java.io.FileReader;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.BufferOverflowException;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;

// import java.util.List;

// import list;

public class SimpleTerminal {
    private static Map<String, Integer> commands = new HashMap<>();
    // Map<String, Function<>> map = new HashMap<>();
    // FunctionInetrface // - This is how to choose which command to use
    private String fileReadFrom; // - Incorporate this somehow
    private String fileWriteTo;
    private Instrument instrument;
    private Map<String, Instrument> instrumentsMap = new HashMap<>(); // - This is for finding the instrument by iCode
    private List<Instrument> instruments = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();
    private boolean firstWrite = true;

    // TODO: Handle checking LOAD INSTRUMENTS, or just have the user enter
    // TODO: Let the user select a set of instruments, select instrument, and then
    // TODO: keep track of all the transactions with ArrayLists.

    static {
        commands.put("LOAD_INSTRUMENTS", 0);
        commands.put("SELECT_INSTRUMENT", 1);
        commands.put("BUY", 2);
        commands.put("SELL", 3);
        commands.put("QUERY", 4);
    }
    // - Maybe use an ArrayList to store the instruments
    // - Can use a Refresh method to refresh on the directory

    public void command(String input) throws IllegalArgumentException {
        String[] inputs = input.split(" ");
        if (!(commands.containsKey(inputs[0]))) {
            System.out.println(commands.get(0));
            throw new IllegalArgumentException("Invalid command. Please try again");
        } else {
            // * System.out.println("This is a valid command");
            switch (commands.get(inputs[0])) {
                case 0:
                    loadInstruments(inputs[1]);
                    break;
                case 1:
                    selectInstrument(inputs[1]);
                    break;
                case 2:

                    // * Check if the number of parameters is correct, BUY + 8 other parameters for
                    // * the Transaction object
                    if (inputs.length != 9) {
                        throw new IllegalArgumentException("Incorrect amount of parameters");
                    }
                    // * Converting some elements in String to doubles to pass into the
                    // * addTransaction function
                    double cleanTransactionPrice = Double.parseDouble(inputs[4]);
                    double dirtyTransactionPrice = Double.parseDouble(inputs[5]);
                    double transactionAmount = Double.parseDouble(inputs[6]);
                    double totalSettlementAmount = Double.parseDouble(inputs[8]);
                    addTransaction(inputs[1], inputs[2], "SELL", cleanTransactionPrice, dirtyTransactionPrice,
                            transactionAmount, inputs[7],
                            totalSettlementAmount);
                    break;
                case 3:
                    // * Converting some elements in String to doubles to pass into the
                    // * addTransaction function
                    double cleanTransactionPrice1 = Double.parseDouble(inputs[4]);
                    double dirtyTransactionPrice1 = Double.parseDouble(inputs[5]);
                    double transactionAmount1 = Double.parseDouble(inputs[6]);
                    double totalSettlementAmount1 = Double.parseDouble(inputs[8]);
                    addTransaction(inputs[1], inputs[2], "SELL", cleanTransactionPrice1, dirtyTransactionPrice1,
                            transactionAmount1, inputs[7],
                            totalSettlementAmount1);
                    break;

                case 4:
                    if (inputs.length == 2) {
                        setFileWriteTo(inputs[1]);
                    }
                    writeTransactions();
                    break;

                default:
                    throw new IllegalArgumentException("Default switch expression reached");
            }
        }

    }

    public void loadInstruments(String file) {
        fileReadFrom = file;

        // List<Instrument> returner = new ArrayList<>();
        // System.out.println("1111");

        try {
            // * Use OpenCSV to read into Java beans
            // CSVReader csvReader = new CSVReader(fileReader, ',', '"', 1);
            // FileReader fileReader = new FileReader(file);
            // Reader reader = new BufferedReader(fileReader);
            Reader reader = new FileReader(fileReadFrom);
            // CsvToBean<Instrument> csvReader = new
            // CsvToBeanBuilder<Instrument>(reader).withType(Instrument.class)
            // .withSeparator(',').withIgnoreLeadingWhiteSpace(true).withSkipLines(1).withIgnoreEmptyLine(true)
            // .build();

            // CsvToBean<Instrument> csvReader = new
            // CsvToBeanBuilder<Instrument>(reader).withType(Instrument.class)
            // .withSeparator(',').withIgnoreLeadingWhiteSpace(true).withIgnoreEmptyLine(true).withSkipLines(1)
            // .build();

            CsvToBean<Instrument> csvReader = new CsvToBeanBuilder<Instrument>(reader)
                    .withType(Instrument.class)
                    .withSeparator(',')
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreEmptyLine(true)
                    .withIgnoreQuotations(true)
                    // .withSkipLines(1)
                    .build();

            // csvReader.skip(1); // - Skip the first line with the headers
            List<Instrument> list = csvReader.parse();
            for (Instrument curr : list) {
                // System.out.println(curr);
            }
            // System.out.println(list.get(1));

            // instruments = list;
            // instruments = new ArrayList<>(list);
            instruments.addAll(list);
            // * Load instruments into a map
            for (int i = 0; i < instruments.size(); i++) {
                instrumentsMap.put(instruments.get(i).getICode(), instruments.get(i));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid file");
        }

        // instruments = returner; // - This line was resetting the list of instruments
    }

    public void selectInstrument(String iCode) {
        if (instruments == null) {
            throw new IllegalStateException("Instruments not loaded yet");
        }
        // for (int i = 0; i < instruments.size(); i++) {
        // System.out.println(instruments.get(i));
        // }
        if (instrumentsMap.get(iCode) == null) {
            throw new IllegalArgumentException("Instrument not found");
        }
        instrument = instrumentsMap.get(iCode);
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setFileWriteTo(String file) {
        fileWriteTo = file;
    }

    // - Create 2 separate methods for buying and selling or just have one for both?

    public void addTransaction(String iCode, String date, String transactionType, double cleanTransactionPrice,
            double dirtyTransactionPrice, double transactionAmount,
            String settlementDate, double settlementAmount) {
        if (instrument == null) {
            throw new IllegalStateException("No instrument selected yet");
        }
        // assert (buyOrSell.equalsIgnoreCase("buy")) ||
        // (buyOrSell.equalsIgnoreCase("sell"))
        // : "Invalid transaction type";

        if (!(transactionType.equalsIgnoreCase("buy")) || (transactionType.equalsIgnoreCase("sell"))) {
            throw new IllegalArgumentException("Invalid transaction type");
        }
        // - Probably include some assertion about the relationship between dirty and
        // - clean price

        Transaction curr = new Transaction(iCode, date, transactionType, cleanTransactionPrice, dirtyTransactionPrice,
                transactionAmount, settlementDate, settlementAmount);
        transactions.add(curr);
    }

    public void writeTransactions() {
        // - Write the CSV headers

        // File file = new File(fileWriteTo);
        try {
            if (firstWrite) {
                firstWrite = false;
            }
            // * Will append firstWrite is false, and overwrite if firstWrite is true
            if (fileWriteTo == null || firstWrite) {
                fileWriteTo = "transactions.csv";
                firstWrite = false;
                // System.out.println("false");
            }
            FileWriter fw = new FileWriter(fileWriteTo, !firstWrite);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            out.println(
                    "\"TRADE_DATE\",\"TRANSACTION_TYPE\",\"CLEAN_TRANSACTION_PRICE\",\"DIRTY_TRANSACTION_PRICE\",\"TRANSACTION_AMOUNT\",\"SETTLEMENT_DATE\",\"TOTAL_SETTLEMENT_AMOUNT\"");
            for (int i = 0; i < transactions.size(); i++) {
                Transaction curr = transactions.get(i);
                out.println("\"" + curr.getDate() + "\","
                        + "\"" + curr.getTransactionType() + "\","
                        + "\"" + curr.getCleanTransactionPrice() + "\","
                        + "\"" + curr.getDirtyTransactionPrice() + "\","
                        + "\"" + curr.getTransactionAmount() + "\","
                        + "\"" + curr.getSettlementDate() + "\","
                        + "\"" + curr.getTotalSettlementAmount() + "\""
                // TODO: Don't overwrite each time
                );
            }
            transactions.clear();

            out.close(); // - It's necessary to close this stream or something
        } catch (Exception e) {

        }

    }

    private void queryTransaction(String filenName) {

    }

    private void queryTransaction(String fileName, String identifier) {

    }
}
