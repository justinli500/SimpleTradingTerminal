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
        // SimpleTerminal terminal = new SimpleTerminal();
        // terminal.loadInstruments("./债券信息1.csv");
        // terminal.selectInstrument("BBG00R953ZY1");
        // System.out.println(terminal.getCurrentInstrument());

        // terminal.addTransaction("iCode", "date", "buy", 123,
        // 123,
        // 123, "settlementDate", 123);
        // terminal.writeTransactions();
        // terminal.addTransaction("iCode", "date", "sell", 123,
        // 123,
        // 123, "settlementDate", 123);
        // terminal.writeTransactions();
        // terminal.addTransaction("iCode", "date", "sell", 123,
        // 123,
        // 123, "settlementDate", 123);
        // terminal.addTransaction("iCode", "date", "sell", 123,
        // 123,
        // 123, "settlementDate", 123);
        // terminal.writeTransactions();
        // terminal.queryTransactions("transactions.csv");
        // - Testing all functions above

        // - Testing out login system and transaction system here
        // SimpleTerminal terminal = new SimpleTerminal();
        // terminal.register("John Smith", "john123", "123123");
        // terminal.loadInstruments("./债券信息1.csv");
        // // // terminal.loadInstruments("./债卷信息1.csv"); // - 1 character difference
        // terminal.selectInstrument("BBG00R953ZY1");
        // terminal.addTransaction("BBG00R953ZY1", "11/22/2024", "buy", 1, 1, 1,
        // "11.11.2021", 1);
        // terminal.addTransaction("BBG00R953ZY1", "11/22/2024", "buy", 1, 1, 1,
        // "11.11.2021", 1);
        // terminal.writeTransactions(); // - Writing transactions clears list of
        // transactions!
        // // terminal.printAllTransactions();
        // List<Transaction> position = terminal.getPosition("john123_BOOK.csv",
        // "11/22/2023");
        // // TODO: fix getPosition with date
        // // List<Transaction> position = terminal.getPosition("john123_BOOK.csv");
        // for (Transaction curr : position) {
        // System.out.println(curr);
        // }
        // System.out.println(terminal.changeBook("john123_BOOK.cs"));
        // terminal.writeUsers();
        // terminal.loadUsers("users.csv");
        // System.out.println(terminal.login("john123", "123123"));

        // User userA = new User("John Smith", "userA", "123asdishdidhs");
        // User userB = new User("John Doe", "userB", "asdlkasdjalksjdasdl");

        // terminal.loadUsers("users.csv");
        // terminal.printUsers();
        // - Testing out login system above

        // - Testing out loadUsers() below
        // SimpleTerminal terminal = new SimpleTerminal();
        // terminal.loadUsers("users.csv");
        // System.out.println(terminal.login("john123", "123123"));
        // terminal.printUsers();
        // terminal.register("James Doe", "james123", "124124");
        // terminal.writeUsers();
        // - Testing our loadUsers() above

        // - Testing out updated queryTransactions() methods below
        SimpleTerminal terminal = new SimpleTerminal();
        terminal.loadUsers("users.csv");
        System.out.println(terminal.login("john123", "123123"));
        // terminal.queryTransactions();
        terminal.queryTransactions(2);

        // - Testing out updated queryTransactions() methods above

        // - Testing out access authorization system here
        // SimpleTerminal terminal = new SimpleTerminal();
        // terminal.register("John Smith", "john123", "123123");
        // terminal.loadInstruments("./债券信息1.csv");
        // terminal.selectInstrument("BBG00R953ZY1");
        // terminal.addTransaction("BBG00R953ZY1", "11/22/2024", "buy", 1, 1, 1,
        // "11.11.2021", 1);
        // terminal.addTransaction("BBG00R953ZY1", "11/22/2024", "buy", 1, 1, 1,
        // "11.11.2021", 1);
        // terminal.writeTransactions(); // - Writing transactions clears list of
        // transactions!
        // List<Transaction> position = terminal.getPosition("john123_BOOK.csv",
        // "11/22/2023");
        // for (Transaction curr : position) {
        // System.out.println(curr);
        // }

        // terminal.register("James Doe", "james123", "124124");
        // // terminal.changeBook("john123_BOOK.csv");
        // terminal.login("john123", "123123");
        // terminal.grantAccess("james123", "john123_BOOK.csv");

        // terminal.login("james123", "124124");
        // terminal.changeBook("john123_BOOK.csv");
        // terminal.addTransaction("BBG00R953ZY1", "11/22/2024", "buy", 1, 1, 1,
        // "11.11.2021", 1);
        // terminal.writeTransactions();

        // - Testing out access authorization system above

        // - Testing out the readTransactions() method here
        // SimpleTerminal terminal = new SimpleTerminal();
        // terminal.setFileWriteTo("transactions.csv");
        // terminal.readTransactions();
        // terminal.printAllTransactions();
        // Transaction transaction1 = new Transaction("iCode", "11/22/2024", "buy", 1,
        // 1, 1, "11.11.2021", 1, "OK");
        // Transaction transaction2 = new Transaction("iCode", "11/22/2025", "buy", 1,
        // 1, 1, "11.11.2022", 1, "OK");
        // System.out.println(transaction1.compareTo(transaction2));
        // System.out.println(Transaction.convertToDate(transaction1.getTradeDate()));
        // - Testing out the readTransactions() method above

        // - Testing out the book class here
        // Book book = new Book("transactions.csv");
        // book.printAllTransactions();

        // - Testing out the book class above

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
        // parameters" +
        // "\nCURRENT_INSTRUMENT" +
        // "\nWRITE_TRANSACTIONS" +
        // "\nQUERY\n");
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
