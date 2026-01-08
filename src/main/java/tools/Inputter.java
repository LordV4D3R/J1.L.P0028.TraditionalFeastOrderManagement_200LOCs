package tools;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class Inputter {
    private Scanner sc;

    public Inputter() {
        this.sc = new Scanner(System.in);
    }

    // Get a string from keyboard
    public String getString(String mess) {
        System.out.print(mess);
        return sc.nextLine().trim();
    }

    // Get string with validation loop (matches Source 2 guideline)
    public String inputAndLoop(String mess, String pattern) {
        String result = "";
        boolean more = true;
        do {
            result = getString(mess);
            more = !Acceptable.isValid(result, pattern);
            if (more) System.out.println("Invalid data! Re-enter...");
        } while (more);
        return result;
    }

    // Get valid integer >= min
    public int getInt(String mess, int min) {
        while (true) {
            String temp = getString(mess);
            try {
                int result = Integer.parseInt(temp);
                if (result >= min) return result;
                System.out.println("Must be >= " + min);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number.");
            }
        }
    }

    // Get valid double >= min
    public double getDouble(String mess, double min) {
        while (true) {
            String temp = getString(mess);
            try {
                double result = Double.parseDouble(temp);
                if (result >= min) return result;
                System.out.println("Must be >= " + min);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number.");
            }
        }
    }

    // Get future date (dd/MM/yyyy)
    public Date getDate(String mess) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        Date now = new Date();
        
        while (true) {
            try {
                Date result = sdf.parse(getString(mess + " (dd/MM/yyyy): "));
                if (result.after(now)) return result;
                System.out.println("Date must be in the future.");
            } catch (ParseException e) {
                System.out.println("Invalid format (dd/MM/yyyy).");
            }
        }
    }
}