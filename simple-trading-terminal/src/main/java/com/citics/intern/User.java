package com.citics.intern;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opencsv.bean.*;
import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;

public class User {
    @CsvBindByName(column = "FULL_NAME")
    private String fullName;
    @CsvBindByName(column = "USERNAME")
    private String username;
    // @CsvBindByName(column = "USER_BOOKS")
    // TODO: fix this so that the values are separated by commas
    @CsvBindAndSplitByName(column = "USER_BOOKS", elementType = String.class, splitOn = ",") // - Issue on this line,
                                                                                             // - not creating commas
    private List<String> accessibleBooks = new ArrayList<>();
    @CsvBindByName(column = "PASSWORD_HASH")
    private String passwordHash;

    private static FileWriter usersFile;

    public User(String fullName, String username, String password) {
        this.fullName = fullName;
        this.username = username;
        this.passwordHash = getHash(password);
        // * Overwrite if it's the first time writing, append otherwise
    }

    public User(String fullName, String username, String password, List<String> books) {
        this(fullName, username, password);
        accessibleBooks = books;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }

    public void addBook(String bookName) {
        accessibleBooks.add(bookName);
    }

    public static String getHash(String password) {
        String hash = "";
        try {
            // TODO: Add salting
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            // hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
            StringBuilder hexBuilder = new StringBuilder();
            for (byte curr : digest) {
                hexBuilder.append(String.format("%02x", curr));
            }
            hash = hexBuilder.toString();
        } catch (Exception e) {

        }
        return hash;

    }

    public void printBooks() {
        for (String book : accessibleBooks) {
            System.out.println(book);
        }
    }

    @Override
    public String toString() {
        return "User [fullName=" + fullName + ", username=" + username + ", accessibleBooks=" + accessibleBooks
                + ", password=" + passwordHash + "]";
    }

    // ? Where should I keep the list of users? From this, should I not include
    // ? hashing stuff within this class? Because then
    // public boolean isValidUser(String username) {

    // }

    // public static Map<String, User> loadUsers(String filePath) {
    // Map<String, User> users = new HashMap<>();
    // return users;
    // }

}
