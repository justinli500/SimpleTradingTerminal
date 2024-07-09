package com.citics.intern;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVReader;
import java.io.FileReader;

// import java.util.List;

// import list;

public class SimpleTerminal {
    private static Map<String, Integer> commands = new HashMap<>();
    // TODO: Handle checking LOAD INSTRUMENTS, or just have the user enter
    // LOAD_INSTRUMENTS
    static {
        commands.put("LOAD_INSTRUMENTS", 0);
        commands.put("SELECT_INSTRUMENT", 1);
        commands.put("BUY", 2);
        commands.put("SELL", 3);
        commands.put("QUERY", 4);
    }

    public static void command(String input) throws IllegalArgumentException {
        String[] inputs = input.split(" ");
        if (!(commands.containsKey(inputs[0]))) {
            System.out.println(commands.get(0));
            throw new IllegalArgumentException("Invalid command. Please try again");
        } else {
            System.out.println("This is a valid command");
            // switch ow
            // TODO: Add how to decide which command to use. Maybe use a switch statement?
        }

    }

    public static List<Instrument> loadInstruments(String file) {

        List<Instrument> returner = new ArrayList<>();
        // System.out.println("1111");

        try {
            // - Read the CSV file line by line and store each line into an array
            FileReader fileReader = new FileReader(file);
            // CSVReader csvReader = new CSVReader(fileReader, ',', '"', 1);
            CSVReader csvReader = new CSVReader(fileReader);
            csvReader.skip(1); // - Skip the first line with the headers

            String[] nextLine = null;
            while (((nextLine = csvReader.readNext()) != null)) {
                // - Remove the " at the end of each entry
                for (int i = 0; i < nextLine.length; i++) {
                    nextLine[i] = nextLine[i].replace("\"", "");
                    // System.out.println(nextLine[i]);
                }
                /*
                 * Format of the nextLine array:
                 * - index 0 = I_CODE,
                 * - index 1 = A_TYPE
                 * - index 2 = M_TYPE
                 * - index 3 = NAME
                 * - index 4 = COUPON
                 * - index 5 = ISSUER
                 * - index 6 = DATE_ISSUED
                 * - index 7 = DATE_MATURED
                 * - index 8 = FACE_VALUE
                 * - index 9 = INTEREST_RULE
                 */
                // - Initialize each Instrument object and add them to the ArrayList
                Instrument curr = new Instrument(nextLine[0], nextLine[1], nextLine[2], nextLine[3], nextLine[4],
                        nextLine[5], nextLine[6], nextLine[7], nextLine[8], nextLine[9]);
                returner.add(curr);
            }

        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid file");
        }

        return returner;

    }

    // TODO: Write to file - writing a

    // LOAD INSTRUMENTS xxx.csv
    // SELECT INSTRUMENT xxx
    //

}
