package com.citics.intern;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVReader;
import java.io.FileReader;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.BufferOverflowException;
import java.io.File;
import java.io.BufferedWriter;

// import java.util.List;

// import list;

public class SimpleTerminal {
    private static Map<String, Integer> commands = new HashMap<>();
    private String fileReadFrom;
    private String fileWriteTo;
    private Instrument instrument;
    private List<Instrument> instruments = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();

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

    public static void command(String input) throws IllegalArgumentException {
        String[] inputs = input.split(" ");
        if (!(commands.containsKey(inputs[0]))) {
            System.out.println(commands.get(0));
            throw new IllegalArgumentException("Invalid command. Please try again");
        } else {
            System.out.println("This is a valid command");
            // switch ow
            // TODO: Add how to decide which command to use. Maybe use a switch statement?
            System.out.println("HI");
        }

    }

    public void loadInstruments(String file) {
        fileReadFrom = file;

        List<Instrument> returner = new ArrayList<>();
        // System.out.println("1111");

        try {
            // - Read the CSV file line by line and store each line into an array
            FileReader fileReader = new FileReader(file);
            // CSVReader csvReader = new CSVReader(fileReader, ',', '"', 1);
            CSVReader csvReader = new CSVReader(fileReader);
            csvReader.skip(1); // - Skip the first line with the headers

            String[] nextLine = null;
            while (((nextLine = csvReader.readNext()) != null)) {
                // - Remove the " at the end of each entry
                for (int i = 0; i < nextLine.length; i++) {
                    nextLine[i] = nextLine[i].replace("\"", "");
                    nextLine[i] = nextLine[i].trim();
                    // System.out.println(nextLine[i]);
                }
                /*
                 * Format of the nextLine array:
                 * - index 0 = I_CODE,
                 * - index 1 = A_TYPE
                 * - index 2 = M_TYPE
                 * - index 3 = NAME
                 * - index 4 = COUPON
                 * - index 5 = ISSUER
                 * - index 6 = DATE_ISSUED
                 * - index 7 = DATE_MATURED
                 * - index 8 = FACE_VALUE
                 * - index 9 = INTEREST_RULE
                 */
                // - Initialize each Instrument object and add them to the ArrayList
                Instrument curr = new Instrument(nextLine[0], nextLine[1], nextLine[2], nextLine[3], nextLine[4],
                        nextLine[5], nextLine[6], nextLine[7], nextLine[8], nextLine[9]);
                returner.add(curr);
                // System.out.println(curr);
            }

        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid file");
        }

        instruments = returner;

    }

    public void selectInstrument(String iCode) {
        if (instruments == null) {
            throw new IllegalStateException("Instruments not loaded yet");

        }
        for (int i = 0; i < instruments.size(); i++) {
            if (instruments.get(i).getICode().equalsIgnoreCase(iCode)) {
                instrument = instruments.get(i);
                // return instrument;
                return;
            }
        }
        throw new IllegalArgumentException("Instrument not found");
    }

    public Instrument getInstrument() {
        return instrument;
    }
    // TODO: Write to file - writing a

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
            boolean append = true;
            if (fileWriteTo == null) {
                fileWriteTo = "transactions.csv";
                append = false;
                // System.out.println("false");
            }
            FileWriter fw = new FileWriter(fileWriteTo, append);
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

                );
            }

            out.close(); // - It's necessary to close this stream or something
        } catch (Exception e) {

        }

    }
}

// TODO: Decide how you're going to interact with the user and where to decide
// TODO: which command to execute.
