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

        // * Testing the different functions below here
        SimpleTerminal terminal = new SimpleTerminal();
        terminal.loadInstruments("./债券信息1.csv");
        terminal.selectInstrument("XS2858459634");
        System.out.println(terminal.getInstrument());

        // terminal.addTransaction("iCode", "date", "buy", 123,
        // 123,
        // 123, "settlementDate", 123);
        // terminal.writeTransactions();

        // - Main app
        // int counter = 0;
        // Scanner scanner = new Scanner(System.in);
        // SimpleTerminal user = new SimpleTerminal();
        // while (true) {

        // // TODO: Setup making a terminal object and getting input from the user here

        // // String command = scanner.getfrominput
        // try {
        // // * Reprint available commands every 10 commands so that they are visible
        // again
        // if (counter % 10 == 0) {
        // System.out.println("\nList of possible commands:" +
        // "\nLOAD INSTRUMENTS xxxx.csv" +
        // "\nSELECT INSTRUMENT xxxx" +
        // "\nBUY (xxxx.csv) or SELL (xxxx.csv)\n");
        // }

        // System.out.println("Please enter your command:");
        // String command = scanner.nextLine();
        // user.command(command);
        // } catch (Exception e) {
        // System.out.println(
        // "Exception reached. Invalid command or file name or parameter, or certain
        // attributes haven't been setup yet.");
        // // TODO: handle exception
        // }
        // counter++;
        // }
        // - Main app

        // System.out.println(instruments.get(0));

    }
}
