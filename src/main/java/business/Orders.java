/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import model.Customer;
import model.Order;
import model.SetMenu;
import tools.FileUtils;
import tools.Inputter;

// Extends HashSet to prevent duplicates (Source 2)
public class Orders extends HashSet<Order> implements Workable<Order> {
    private final String FILE_NAME = "feast_order_service.dat";
    private Inputter inputter = new Inputter();
    
    // Dependencies to check valid codes
    private Customers customerManager;
    private SetMenus menuManager;

    public Orders(Customers c, SetMenus m) {
        this.customerManager = c;
        this.menuManager = m;
    }

    @Override
    public void addNew() {
        System.out.println("--- Place Feast Order ---");
        // Check valid Customer ID
        String cusId;
        while(true) {
            cusId = inputter.getString("Enter Customer ID: ");
            if (customerManager.searchById(cusId) != null) break;
            System.out.println("Customer not found in registry.");
        }
        
        // Check valid Menu ID
        String menuId;
        SetMenu selectedMenu;
        while(true) {
            menuId = inputter.getString("Enter Set Menu ID: ");
            selectedMenu = menuManager.searchById(menuId);
            if (selectedMenu != null) break;
            System.out.println("Menu not found.");
        }
        
        int tables = inputter.getInt("Number of tables: ", 1);
        Date date = inputter.getDate("Preferred Event Date");
        
        Order newOrder = new Order(cusId, menuId, tables, date);
        
        // Check Duplicate logic (HashSet uses equals/hashCode from Order model)
        if (this.contains(newOrder)) {
            System.out.println("Duplicate data! Order with same Customer, Menu and Date exists."); // Source 1
        } else {
            this.add(newOrder);
            System.out.println("Order placed successfully! Total Cost: " 
                + String.format("%,.0f", calculateCost(selectedMenu.getPrice(), tables)));
        }
    }

    @Override
    public void update() {
        String id = inputter.getString("Enter Order ID to update: ");
        Order order = searchById(id);
        
        if (order == null) {
            System.out.println("Order does not exist.");
            return;
        }
        
        // Validation: Cannot update past event (Source 1)
        if (order.getEventDate().before(new Date())) {
            System.out.println("Cannot update order for a past event!");
            return;
        }

        System.out.println("Updating Order " + id);
        // Update Menu
        String newMenuId = inputter.getString("New Menu ID (Leave blank to keep): ");
        if (!newMenuId.isEmpty()) {
            if (menuManager.searchById(newMenuId) != null) order.setSetMenuId(newMenuId);
            else System.out.println("Menu not found. Keep old menu.");
        }
        
        // Update Tables
        String tableStr = inputter.getString("New Table Count (Leave blank to keep): ");
        if (!tableStr.isEmpty()) {
            try {
                int t = Integer.parseInt(tableStr);
                if (t > 0) order.setNumOfTables(t);
            } catch (Exception e) {}
        }
        
        // Update Date
        // Note: For simplicity, re-entering date fully if update needed
        String changeDate = inputter.getString("Update Date? (y/n): ");
        if (changeDate.equalsIgnoreCase("y")) {
            order.setEventDate(inputter.getDate("New Date"));
        }
        
        System.out.println("Update successful.");
    }
    
    // Calculate total cost
    private double calculateCost(double price, int tables) {
        return price * tables;
    }

    public Order searchById(String id) {
        for (Order o : this) {
            if (o.getId().equals(id)) return o;
        }
        return null;
    }

    @Override
    public void showAll() {
        if (this.isEmpty()) {
            System.out.println("No data in the system."); // Source 1
            return;
        }
        
        // Convert Set to List to sort (Source 1 requires sorting by Date)
        List<Order> list = new ArrayList<>(this);
        Collections.sort(list);
        
        System.out.println("--- Order List ---");
        for (Order o : list) {
            SetMenu menu = menuManager.searchById(o.getSetMenuId());
            double price = (menu != null) ? menu.getPrice() : 0;
            double total = calculateCost(price, o.getNumOfTables());
            
            System.out.println(o + " | Total: " + String.format("%,.0f", total));
        }
    }

    public void saveFile() {
        // Convert Set to List for generic FileUtils
        FileUtils.saveToFile(new ArrayList<>(this), FILE_NAME);
    }
    
    public void loadFile() {
        List<Order> list = FileUtils.readFromFile(FILE_NAME);
        this.addAll(list);
    }
}
