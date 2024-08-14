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
import java.io.BufferedReader;
import java.security.MessageDigest;

import java.io.FileReader;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.StringWriter;
import java.io.StringReader;

public class SimpleTerminal {
    private static Map<String, Integer> commands = new HashMap<>();
    // Map<String, Function<>> map = new HashMap<>();
    // FunctionInetrface // - This is how to choose which command to use
    private String fileReadFrom; // - Incorporate this somehow
    private String fileWriteTo; // * fileWriteTo == currentBook?
    private Instrument currentInstrument;
    private Map<String, Instrument> instrumentsMap = new HashMap<>(); // - This is for finding the instrument by iCode
    private List<Transaction> transactions = new ArrayList<>(); // ? Necessary to add a HashMap?
    private boolean firstWrite = true;
    private User currentUser;
    private Map<String, User> allUsers = new HashMap<>();
    private Map<String, String> logins = new HashMap<>();
    private String currentBook;
    // private List<Transaction> transactionsList = new ArrayList<>();

    // TODO: Handle loading new instance variables when the user is switched

    static {
        // * All lower case so that there aren't any case issues
        commands.put("load_instruments", 0);
        commands.put("select_instrument", 1);
        commands.put("buy", 2);
        commands.put("sell", 3);
        commands.put("current_instrument", 4);
        commands.put("write_transactions", 5);
        commands.put("query", 6);
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

                case 6:
                    queryTransactions(input);
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
            System.out.println("\nInstrument found:\n" + currentInstrument);
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
        if (currentUser == null) {
            throw new IllegalStateException("No user selected yet");
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
                transactionAmount, settlementDate, settlementAmount, fileWriteTo);
        transactions.add(curr);
    }

    public void printAllTransactions() {
        for (Transaction curr : transactions) {
            System.out.println(curr);
        }
    }

    public void writeTransactions() {
        // TODO: Throw an exception if there are no transactions to be written?

        try {

            if (currentUser == null) {
                throw new IllegalAccessException("Current user not selected");
            }
            // * Will append firstWrite is false, and overwrite if firstWrite is true
            if (fileWriteTo == null) {
                fileWriteTo = currentUser.getUsername() + "_BOOK.csv";
                System.out.println(currentUser.getUsername());
            }
            if (firstWrite) {
                FileWriter fileWriter = new FileWriter(fileWriteTo, !firstWrite);
                // BufferedWriter bw = new BufferedWriter(fw);
                // PrintWriter out = new PrintWriter(bw);
                StatefulBeanToCsv<Transaction> beanToCsv = new StatefulBeanToCsvBuilder<Transaction>(fileWriter)
                        .withApplyQuotesToAll(true).build();
                beanToCsv.write(transactions);
                fileWriter.close(); // writer needs to be closed
                if (!firstWrite) {
                    System.out.println("File appended successfully");
                } else {
                    System.out.println("File created/overwritten successfully");
                }
                transactions.clear();
                firstWrite = false;
            } else {
                StringWriter stringWriter = new StringWriter();
                StatefulBeanToCsv<Transaction> beanToCsv = new StatefulBeanToCsvBuilder<Transaction>(stringWriter)
                        .withApplyQuotesToAll(true).build();
                beanToCsv.write(transactions);
                String transacsString = stringWriter.toString();
                BufferedReader reader = new BufferedReader(new StringReader(transacsString));
                FileWriter writer = new FileWriter(fileWriteTo, true);
                reader.readLine();
                // reader.withSkipLines(3);
                String line = reader.readLine();
                // System.out.println("LINE: \n" + line);
                while (line != null) {
                    writer.write(line + "\n");
                    line = reader.readLine();
                }
                writer.close();

            }

            // out.close(); // - It's necessary to close this stream or something
        } catch (Exception e) {
            System.out.println("File failed, reason: \n" + e);

        }

    }

    public void queryTransactions(String fileName) {
        try {
            Reader reader = new FileReader(fileName);
            CsvToBean<Transaction> csvReader = new CsvToBeanBuilder<Transaction>(reader)
                    .withType(Transaction.class)
                    .withSeparator(',')
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreEmptyLine(true)
                    .withIgnoreQuotations(true)
                    // .withSkipLines(1)
                    .build();

            // csvReader.skip(1); // - Skip the first line with the headers
            List<Transaction> list = csvReader.parse();
            // * Load instruments into a map
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid file");
        }
    }

    private void queryTransactions(String fileName, int identifier) {
        try {
            Reader reader = new FileReader(fileName);
            CsvToBean<Transaction> csvReader = new CsvToBeanBuilder<Transaction>(reader)
                    .withType(Transaction.class)
                    .withSeparator(',')
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreEmptyLine(true)
                    .withIgnoreQuotations(true)
                    // .withSkipLines(1)
                    .build();

            // csvReader.skip(1); // - Skip the first line with the headers
            List<Transaction> list = csvReader.parse();
            // * Load transactions into a list
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getTransactionIdentifier() == identifier) {
                    System.out.println(list.get(i));
                }
            }
            throw new IllegalArgumentException("Transaction with identifier not found");
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid file");
        }
    }

    public void readTransactions() {
        try {
            Reader reader = new FileReader(fileWriteTo);
            CsvToBean<Transaction> csvReader = new CsvToBeanBuilder<Transaction>(reader)
                    .withType(Transaction.class)
                    .withSeparator(',')
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreEmptyLine(true)
                    .withIgnoreQuotations(true)
                    // .withSkipLines(1)
                    .build();

            // * Load transactions into a list
            List<Transaction> list = csvReader.parse();
            transactions = list;
            // transactionsList = list;
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid file");
        }
    }

    public void register(String fullName, String username, String password, String book) {

        if (allUsers.get(username) != null) {
            throw new IllegalArgumentException("User already exists");
        } else {
            currentUser = new User(fullName, username, password);
            allUsers.put(username, currentUser); // ? This line might cause a problem?
            setFileWriteTo(book); // * Change the file we're writing to
            String hash = User.getHash(password);
            logins.put(username, hash);
            currentUser.addBook(book);
            // currentUser.addBook("second_book"); // -
            // currentUser.addBook("third_book"); // -

        }

    }

    public void register(String fullName, String username, String password) {
        register(fullName, username, password, username + "_BOOK.csv");

    }

    /*
     * login process:
     * Register (check if the user already exists)? If not, enter username and
     * passwords
     * List all books, select which one
     */

    public boolean login(String username, String password) {
        String hash = User.getHash(password);
        // * Check if entered password equals the one in the database
        if (hash.equals(logins.get(username))) {
            currentUser = allUsers.get(username);
            return true;
        } else {
            return false;
        }
    }

    public boolean logout() {
        if (currentUser == null) {
            return false;
        } else {
            currentUser = null;
            return true;
        }
    }

    public void loadUsers(String path) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                // * Split the line into username and password
                String[] login = line.split(",");
                String fullName = login[0].replace("\"", "");
                String password = login[1].replace("\"", "");
                String username = login[2].replace("\"", "");
                String books = login[3].replace("\"", "");
                logins.put(username, password);
                // register(books, username, password);
                List<String> booksList = new ArrayList<>();
                String[] booksArray = books.split(" ");
                for (String book : booksArray) {
                    booksList.add(book);
                }
                // System.out.println(fullName);
                // System.out.println(username);
                // System.out.println(password);
                // System.out.println(booksArray[0]);
                allUsers.put(username, new User(fullName, username, password, booksList));
            }
        } catch (IOException e) {
            e.printStackTrace(); // ? What does this do?
        }
    }

    public void printUsers() {
        for (String username : allUsers.keySet()) {
            System.out.println(allUsers.get(username));
        }
    }

    // ? Is it necessary to let the user choose a book on login?
    // public boolean login(String username, String password, String book) {
    // login(fullName, username, password);
    // // - If a book isn't provided, create one for the user
    // // currentUser = new User()
    // return false;
    // }

    // ? Should I do the login method here or in the User class?
    // ? Should I be able to load users from a list of usernames and passwords?
    // ? Perhaps not, but I should be able to log in
    // ? But then how should I check for access easily?

    // - Meeting notes:
    /*
     * -
     * 
     * SimpleTerminal:
     * - login and logout should go in SimpleTerminal
     * - put all registered users in
     * - username, password, accessible books (put all books in a quotation marks)
     * - check how to save a list of books into a CSV
     * - Load files first thing in the App.java, and then log in. If the user
     * doesn't exist, then register, then write into files
     * - Also include a registering process
     * - If the user isn't logged in, then don't allow any processes
     * - logout: user = null;
     * - if user == null, then not logged in
     * - load users into a map, list, or set.
     * - serialize
     * - writeToCsv - you can tell the OpenCSV thing how to separate the elements
     * - String book property, in transactions?
     * - User: String bookName
     * -
     * 
     * - TradeBook:
     * - no accessible
     * - add a Book property into a transaction, so that you can see which book the
     * transaction belongs to
     * - in reality, they might store an ID, and user. No transaction details
     * - all transactions are in a massive table, and can store information about
     * which book
     * - during transaction, you don't need to know too much about books
     * -
     * 
     * Authentication:
     * - only your own program knows what the salted thing is, but there must be a
     * pattern, so that you can treat different users differently
     * - salt: even if the password is unencrypted, they won't know what the
     * password is
     * -
     */

    // TODO: Create a wipe all method

    public void writeUsers() {
        try {
            // * Will append firstWrite is false, and overwrite if firstWrite is true
            if (true) {
                FileWriter fileWriter = new FileWriter("users.csv", !firstWrite);
                List<User> usersList = new ArrayList<>();
                for (String key : allUsers.keySet()) {
                    usersList.add(allUsers.get(key));
                }
                // BufferedWriter bw = new BufferedWriter(fw);
                // PrintWriter out = new PrintWriter(bw);
                StatefulBeanToCsv<User> beanToCsv = new StatefulBeanToCsvBuilder<User>(fileWriter)
                        .withApplyQuotesToAll(true).build();
                beanToCsv.write(usersList);
                fileWriter.close(); // writer needs to be closed
                if (!firstWrite) {
                    System.out.println("File appended successfully");
                } else {
                    System.out.println("File created/overwritten successfully");
                }
                // transactions.clear();
                // firstWrite = false;
            } else { // TODO: Do something with this?
                StringWriter stringWriter = new StringWriter();
                StatefulBeanToCsv<Transaction> beanToCsv = new StatefulBeanToCsvBuilder<Transaction>(stringWriter)
                        .withApplyQuotesToAll(true).build();
                beanToCsv.write(transactions);
                String transacsString = stringWriter.toString();
                BufferedReader reader = new BufferedReader(new StringReader(transacsString));
                FileWriter writer = new FileWriter(fileWriteTo, true);
                reader.readLine();
                // reader.withSkipLines(3);
                String line = reader.readLine();
                // System.out.println("LINE: \n" + line);
                while (line != null) {
                    writer.write(line + "\n");
                    line = reader.readLine();
                }
                writer.close();

            }

            // out.close(); // - It's necessary to close this stream or something
        } catch (Exception e) {
            System.out.println("File failed, reason: \n" + e);

        }

    }

    public boolean changeBook(String bookName) throws IllegalStateException {
        if (currentUser == null) {
            throw new IllegalStateException("Current user not selected");
        }
        if (currentUser.checkBookAccess(bookName)) {
            setFileWriteTo(bookName);
            return true;
        } else {
            return false;
        }
        // ? Are these boolean return values necessary?
    }

    public void grantAccess(String targetUsername, String bookName) throws IllegalStateException {
        if (currentUser == null) {
            throw new IllegalStateException("Current user not selected");
        } else if (currentUser.checkBookAccess(bookName)) {
            throw new IllegalStateException("Current user does not have access to given book");
        }
        allUsers.get(targetUsername).addBook(bookName);
    }

    public void getPosition(String targetBook) throws IllegalStateException {
        if (currentUser == null) {
            throw new IllegalStateException("Current user not selected");
        } else if (currentUser.checkBookAccess(targetBook)) {
            throw new IllegalStateException("Current user does not have access to given book");
        }

    }

    // TODO: add "book" attribute for transaction and change all constructors
    // public List<Transaction> position(String book) {

    // }

}
