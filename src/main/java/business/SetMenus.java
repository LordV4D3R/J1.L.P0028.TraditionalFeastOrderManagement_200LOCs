/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import model.SetMenu;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class SetMenus extends ArrayList<SetMenu> {

    public void loadFromCSV(String filePath) {
        File f = new File(filePath);
        if (!f.exists()) {
            System.out.println("Cannot read data from " + filePath + ". Please check it.");
            return;
        }

        // SỬA ĐỔI Ở ĐÂY: Dùng InputStreamReader với StandardCharsets.UTF_8
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8))) {
            
            String line;
            while ((line = br.readLine()) != null) {
                // Xử lý ký tự BOM (Byte Order Mark) nếu file CSV có dính ở đầu
                if (line.startsWith("\uFEFF")) {
                    line = line.substring(1);
                }

                // Bỏ qua dòng tiêu đề
                if (line.trim().startsWith("Code") || line.trim().startsWith("code")) {
                    continue;
                }
                
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String code = parts[0].trim();
                    String name = parts[1].trim();
                    try {
                        double price = Double.parseDouble(parts[2].trim());
                        
                        String ingredients = "";
                        for (int i = 3; i < parts.length; i++) {
                            ingredients += parts[i] + (i < parts.length - 1 ? "," : "");
                        }
                        ingredients = ingredients.trim().replace("#", "\n");

                        this.add(new SetMenu(code, name, price, ingredients));
                    } catch (NumberFormatException e) {
                        System.out.println("Skipping invalid price at line: " + line);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading CSV: " + e.getMessage());
        }
    }

    public void showAll() {
        if (this.isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        // Sort by Price ascending before showing (Source 1 req)
        Collections.sort(this);

        System.out.println("List of Set Menus:");
        for (SetMenu menu : this) {
            System.out.println(menu);
        }
    }

    // Helper to find Menu by ID for Order validation
    public SetMenu searchById(String id) {
        for (SetMenu menu : this) {
            if (menu.getCode().equalsIgnoreCase(id)) {
                return menu;
            }
        }
        return null;
    }
}
