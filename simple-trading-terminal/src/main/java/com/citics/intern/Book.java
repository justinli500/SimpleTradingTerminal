package com.citics.intern;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.Reader;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class Book {
    String filePath;
    List<Transaction> transactions;
    List<String> accessors; // ? Change it to a list of strings or users?

    public Book(String filePath) {
        try {
            Reader reader = new FileReader(filePath);
            CsvToBean<Transaction> csvReader = new CsvToBeanBuilder<Transaction>(reader)
                    .withType(Transaction.class)
                    .withSeparator(',')
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreEmptyLine(true)
                    .withIgnoreQuotations(true)
                    // .withSkipLines(1)
                    .build();

            transactions = csvReader.parse();
        } catch (Exception e) {
            System.out.println("Loading transactions into book failed");
        }
    }

    public void printAllTransactions() {
        for (Transaction curr : transactions) {
            System.out.println(curr);
        }
    }

    public void grantControl(String username) {
        accessors.add(username);

    }

    public boolean checkAccess(String username) {
        return false;
    }

}
