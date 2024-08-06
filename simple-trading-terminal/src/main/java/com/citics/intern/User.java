package com.citics.intern;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    // @CsvBindByName("FIRST_NAME")
    private String fullName;
    private String username;
    // @CsvBindByName("LAST_NAME")
    // private String lastName;
    private List<Book> books;
    // private static
    // private static boolean firstWrite = true;

    private static FileWriter usersFile;

    public User(String fullName, String username, String password) {
        this.fullName = fullName;
        this.username = username;
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

    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }

    // public static Map<String, User> loadUsers(String filePath) {
    // Map<String, User> users = new HashMap<>();
    // return users;
    // }

}
