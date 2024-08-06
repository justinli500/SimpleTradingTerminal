package com.citics.intern;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvBindByName;
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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.StringWriter;
import java.io.StringReader;
import java.lang.StringBuilder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    // @CsvBindByName("FIRST_NAME")
    private String fullName;
    // @CsvBindByName("LAST_NAME")
    // private String lastName;
    private List<Book> books;
    // private static boolean firstWrite = true;

    private static FileWriter usersFile;

    public User(String fullName, String username, String password) {
        this.fullName = fullName;

        // * Overwrite if it's the first time writing, append otherwise
        if (usersFile == null) {
            try {
                usersFile = new FileWriter("users.csv", false);
            } catch (Exception e) {
                throw new IllegalAccessError("Error creating the usersFile");
            }
        } else {
            try {
                usersFile = new FileWriter("users.csv", true);
            } catch (Exception e) {
                throw new IllegalAccessError("Error creating the usersFile");
            }

        }
        String hash = "";
        try {
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
        try {
            // * Checking if the file is empty, to append or not
            BufferedReader br = new BufferedReader(new FileReader("users.csv"));
            // if (firstWrite) {

            // }
            // usersFile.
            // usersFile.write("asdsad");

            usersFile.write(username + ", " + hash + "\n");
            usersFile.close();
        } catch (Exception e) {
            System.out.println("Username and password write failed");
        }

    }

    public Map<String, String> loadUsers(String filePath) {
        Map<String, String> users = new HashMap<>();
        return users;
    }

}
