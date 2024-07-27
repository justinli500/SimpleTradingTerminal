package com.citics.intern;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.Reader;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.FileReader;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;

public class SimpleTerminal {
    private static Map<String, Integer> commands = new HashMap<>();
    // Map<String, Function<>> map = new HashMap<>();
    // FunctionInetrface // - This is how to choose which command to use
    private String fileReadFrom; // - Incorporate this somehow
    private String fileWriteTo;
    private Instrument currentInstrument;
    private Map<String, Instrument> instrumentsMap = new HashMap<>(); // - This is for finding the instrument by iCode
    private List<Transaction> transactions = new ArrayList<>();
    private boolean firstWrite = true;

    // TODO: Handle checking LOAD INSTRUMENTS, or just have the user enter
    // TODO: Let the user select a set of instruments, select instrument, and then
    // TODO: keep track of all the transactions with ArrayLists.

    static {
        // * All lower case so that there aren't any case issues
        commands.put("load_instruments", 0);
        commands.put("select_instrument", 1);
        commands.put("buy", 2);
        commands.put("sell", 3);
        // commands.put("query", 4);
        commands.put("current_instrument", 4);
        commands.put("write_transactions", 5);
    }
    // - Maybe use an ArrayList to store the instruments
    // - Can use a Refresh method to refresh on the directory

    public void command(String input) throws IllegalArgumentException {
        String[] inputs = input.split(" ");
        if (!(commands.containsKey(inputs[0].toLowerCase()))) {
            System.out.println(commands.get(0));
            throw new IllegalArgumentException("Invalid command. Please try again");
        } else {
            // * System.out.println("This is a valid command");
            switch (commands.get(inputs[0].toLowerCase())) {
                case 0:
                    loadInstruments(inputs[1]);
                    break;
                case 1:
                    selectInstrument(inputs[1]);
                    break;
                case 2:

                    // * Check if the number of parameters is correct, BUY + 8 other parameters for
                    // * the Transaction object
                    if (inputs.length == 1) {
                        System.out.println("\nFormat of BUY parameters: "
                                + "\nString iCode"
                                + "\nString date"
                                + "\nString transactionType (BUY/SELL)"
                                + "\ndouble cleanTransactionPrice"
                                + "\ndouble dirtyTransactionPrice"
                                + "\ndouble transactionAmount"
                                + "\nString settlementDate"
                                + "\ndouble settlementAmount");
                        break;

                        // iCode, date, transactionType, cleanTransactionPrice, dirtyTransactionPrice,
                        // transactionAmount, settlementDate, settlementAmount
                    }
                    if (inputs.length != 9) {
                        throw new IllegalArgumentException("Incorrect amount of parameters");
                    }
                    if (inputs[2].equalsIgnoreCase(inputs[7])) {
                        throw new IllegalArgumentException("The transation date is equal to the settlement date");
                    }
                    // * Converting some elements in String to doubles to pass into the
                    // * addTransaction function
                    double cleanTransactionPrice = Double.parseDouble(inputs[4]);
                    double dirtyTransactionPrice = Double.parseDouble(inputs[5]);
                    double transactionAmount = Double.parseDouble(inputs[6]);
                    double totalSettlementAmount = Double.parseDouble(inputs[8]);
                    addTransaction(inputs[1], inputs[2], "BUY", cleanTransactionPrice, dirtyTransactionPrice,
                            transactionAmount, inputs[7],
                            totalSettlementAmount);
                    // - If transactionType == "SELL" then this creates an error, not sure why
                    break;
                case 3:
                    // * Checking if there are enough parameters
                    if (inputs.length == 1) {
                        System.out.println("Format of BUY parameters: "
                                + "\nString iCode"
                                + "\nString date"
                                + "\nString transactionType (BUY/SELL)"
                                + "\ndouble cleanTransactionPrice"
                                + "\ndouble dirtyTransactionPrice"
                                + "\ndouble transactionAmount"
                                + "\nString settlementDate"
                                + "\ndouble settlementAmount");
                        break;
                    }
                    if (inputs.length != 9) {
                        throw new IllegalArgumentException("Incorrect amount of parameters");
                    }
                    if (inputs[2].equalsIgnoreCase(inputs[7])) {
                        throw new IllegalArgumentException("The transation date is equal to the settlement date");
                    }
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
                    System.out.println(currentInstrument);
                    break;

                case 5:
                    if (inputs.length == 2) {
                        setFileWriteTo(inputs[1]);
                    } else {
                        // -

                    }
                    writeTransactions();
                    break;

                // case 6:
                // writeTransactions();
                // break;
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
            // * Load instruments into a map
            for (int i = 0; i < list.size(); i++) {
                instrumentsMap.put(list.get(i).getICode(), list.get(i));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid file");
        }

        // instruments = returner; // - This line was resetting the list of instruments
    }

    public void selectInstrument(String iCode) {
        if (instrumentsMap == null || instrumentsMap.size() == 0) {
            throw new IllegalArgumentException("Instrument not loaded");
        }
        if (!instrumentsMap.containsKey(iCode)) {
            throw new IllegalArgumentException("Instrument not found");
        } else {
            this.currentInstrument = instrumentsMap.get(iCode);
        }
    }

    public Instrument getCurrentInstrument() {
        return currentInstrument;
    }

    public void setFileWriteTo(String file) {
        fileWriteTo = file;
    }

    // - Create 2 separate methods for buying and selling or just have one for both?

    public void addTransaction(String iCode, String date, String transactionType, double cleanTransactionPrice,
            double dirtyTransactionPrice, double transactionAmount,
            String settlementDate, double settlementAmount) {
        if (currentInstrument == null) {
            throw new IllegalStateException("No instrument selected yet");
        }
        // assert (buyOrSell.equalsIgnoreCase("buy")) ||
        // (buyOrSell.equalsIgnoreCase("sell"))
        // : "Invalid transaction type";

        if (!(transactionType.equalsIgnoreCase("buy")) && !(transactionType.equalsIgnoreCase("sell"))) {
            throw new IllegalArgumentException("Invalid transaction type");
        }
        // - Probably include some assertion about the relationship between dirty and
        // - clean price

        Transaction curr = new Transaction(iCode, date, transactionType, cleanTransactionPrice, dirtyTransactionPrice,
                transactionAmount, settlementDate, settlementAmount);
        transactions.add(curr);
    }

    public void printAllTransactions() {
        for (Transaction curr : transactions) {
            System.out.println(curr);
        }
    }

    public void writeTransactions() {
        // - Write the CSV headers
        // TODO: Throw an exception if there are no transactions to be written?

        // File file = new File(fileWriteTo);
        try {

            // * Will append firstWrite is false, and overwrite if firstWrite is true
            if (fileWriteTo == null) {
                fileWriteTo = "transactions.csv";
                // System.out.println("false");
            }
            // - Maybe don't firstWrite until after writing. But for now there is another
            // - bug
            FileWriter fileWriter = new FileWriter(fileWriteTo, !firstWrite);
            // BufferedWriter bw = new BufferedWriter(fw);
            // PrintWriter out = new PrintWriter(bw);
            StatefulBeanToCsv<Transaction> beanToCsv = new StatefulBeanToCsvBuilder<Transaction>(fileWriter)
                    .withApplyQuotesToAll(true).build();
            beanToCsv.write(transactions);
            fileWriter.close(); // writer needs to be closed
            // beanToCsv.write(new Transaction("iCode", "date", "buy", 123,
            // 123,
            // 123, "settlementDate", 123));
            if (!firstWrite) {
                System.out.println("File appended successfully");
            } else {
                System.out.println("File created/overwritten successfully");
            }
            transactions.clear();
            if (firstWrite) {
                firstWrite = false;
            }

            // out.close(); // - It's necessary to close this stream or something
        } catch (Exception e) {
            System.out.println("File failed, reason: \n" + e);

        }

    }

    private void queryTransaction(String filenName) {

    }

    private void queryTransaction(String fileName, String identifier) {

    }
}
