/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    // Generic method to read list from binary file (Source 2 guideline)
    public static <T> List<T> readFromFile(String filePath) {
        List<T> list = new ArrayList<>();
        File f = new File(filePath);
        if (!f.exists()) return list;

        try (FileInputStream fis = new FileInputStream(f);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            while (fis.available() > 0) {
                T obj = (T) ois.readObject();
                list.add(obj);
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + filePath);
        }
        return list;
    }

    // Generic method to save list to binary file
    public static <T> void saveToFile(List<T> list, String filePath) {
        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            for (T obj : list) {
                oos.writeObject(obj);
            }
            System.out.println("Data saved to " + filePath);
        } catch (IOException e) {
            System.out.println("Error saving file: " + filePath);
        }
    }
}
