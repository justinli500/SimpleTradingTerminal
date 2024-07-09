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

        // Date date = new Date(10, 10, 10);
        // System.out.println(date);

        // Scanner scanner = new Scanner(System.in);
        // System.out.println("Please input your command:");
        // String input = scanner.nextLine();

        // SimpleTerminal.command(input);

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

        List<Instrument> instruments = SimpleTerminal
                .loadInstruments("./债券信息1.csv");

        System.out.println(instruments.get(0));

    }
}
