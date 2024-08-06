package com.citics.intern;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        /*
         * - How to get relative path
         * Path filePath1 = Paths.
         * get("/Users/justinli/Desktop/Summer Internship/SimpleTradingTerminal/债券信息1.csv"
         * );
         * Path filePath2 = Paths.get(
         * "/Users/justinli/Desktop/Summer Internship/SimpleTradingTerminal/simple-trading-terminal/src/main/java/com/citics/intern/App.java"
         * );
         * Path relativePath = filePath2.getParent().relativize(filePath1);
         * 
         * // Print the relative path
         * System.out.println("Relative path from file1 to file2: " + relativePath);
         */
        // List<Instrument> instruments = SimpleTerminal
        // .loadInstruments("../../../../../../../债券信息1.csv");

        // - Testing the different functions below here
        SimpleTerminal terminal = new SimpleTerminal();
        terminal.loadInstruments("./债券信息1.csv");
        terminal.selectInstrument("BBG00R953ZY1");
        System.out.println(terminal.getCurrentInstrument());

        terminal.addTransaction("iCode", "date", "buy", 123,
                123,
                123, "settlementDate", 123);
        terminal.writeTransactions();
        terminal.addTransaction("iCode", "date", "sell", 123,
                123,
                123, "settlementDate", 123);
        terminal.writeTransactions();
        terminal.addTransaction("iCode", "date", "sell", 123,
                123,
                123, "settlementDate", 123);
        terminal.addTransaction("iCode", "date", "sell", 123,
                123,
                123, "settlementDate", 123);
        terminal.writeTransactions();
        terminal.queryTransactions("transactions.csv");
        // - Testing

        // - Main app
        // int counter = 0;
        // // * Including this to wanr the user about the default file that the
        // // * transactions will be written to the first time addTransaction
        // // * is called
        // boolean warnedAboutSetFileWriteTo = false;
        // Scanner scanner = new Scanner(System.in);
        // SimpleTerminal user = new SimpleTerminal();
        // while (true) {

        // // String command = scanner.getfrominput

        // // - For ease of testing:
        // // - File path: ./债券信息1.csv
        // // - Example instrument iCode: BBG00R953ZY1
        // // - load_instruments ./债券信息1.csv
        // // - select_instrument BBG00R953ZY1
        // // - buy iCode date BUY 123 123 123 settlementDate 123
        // // - sell iCode date SELL 123 123 123 settlementDate 123
        // // - write_transactions

        // try {
        // // * Reprint available commands every 10 commands so that they are visible
        // // * again
        // if (counter % 10 == 0) {
        // System.out.println("\nList of possible commands (case insensitive):" +
        // "\nLOAD INSTRUMENTS xxxx.csv" +
        // "\nSELECT INSTRUMENT xxxx" +
        // "\nBUY (xxxx.csv) or SELL (xxxx.csv). Enter BUY or SELL for the format of the
        // parameters\n"
        // +
        // "\nCURRENT_INSTRUMENT");
        // }
        // if (counter == 0) {
        // System.out.println("Please enter your command:");
        // String command = scanner.nextLine();
        // user.command(command);
        // System.out.println("\nValid command");
        // counter++;
        // continue;
        // }
        // System.out.println("\nPlease enter your command:");
        // String command = scanner.nextLine();
        // user.command(command);
        // if ((command.toLowerCase().contains("buy") ||
        // command.toLowerCase().contains("sell"))
        // && (!warnedAboutSetFileWriteTo)) {
        // System.out.println(
        // "\nBy default, writeTransactions() will write the transactions " +
        // "to a file called \"transactions.csv\"." +
        // "Please provide a CSV file name after WRITE_TRANSACTIONS _____ " +
        // "if you would like to write to a custom file.");
        // warnedAboutSetFileWriteTo = true;
        // }
        // System.out.println("\nValid command");
        // } catch (Exception e) {
        // // System.out.println(
        // // "Exception reached. Invalid command or file name or parameter, or certain
        // // attributes haven't been setup yet.");
        // System.out.println(e);
        // // TODO: handle exception
        // }
        // counter++;
        // }
        // - Main app

        // System.out.println(instruments.get(0));

    }
}
