package com.citics.intern;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

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

        List<Instrument> instruments = SimpleTerminal.loadInstruments("../债券信息1.csv");

        System.out.println(instruments.get(0));

    }
}
