package dispatcher;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import business.Customers;
import business.Orders;
import business.SetMenus;
import tools.Inputter;

public class Main {
    public static void main(String[] args) {
        // 1. Khởi tạo các đối tượng quản lý
        SetMenus menuManager = new SetMenus();
        Customers customerManager = new Customers();
        
        // Orders cần biết về Customer và Menu để kiểm tra ID hợp lệ
        Orders orderManager = new Orders(customerManager, menuManager);
        
        Inputter inputter = new Inputter();

        // 2. Nạp dữ liệu từ file (Startup loading)
        // Load menu từ CSV (File đề bài cung cấp)
        menuManager.loadFromCSV("FeastMenu.csv");
        // Load data cũ nếu có
        customerManager.loadFile(); 
        orderManager.loadFile();

        // 3. Vòng lặp Menu chính
        while (true) {
            System.out.println("\n========================================");
            System.out.println("   TRADITIONAL FEAST ORDER MANAGEMENT");
            System.out.println("========================================");
            System.out.println("1. Register customers");
            System.out.println("2. Update customer information");
            System.out.println("3. Search for customer information by name");
            System.out.println("4. Display feast menus");
            System.out.println("5. Place a feast order");
            System.out.println("6. Update order information");
            System.out.println("7. Save data to file");
            System.out.println("8. Display Customer or Order lists");
            System.out.println("9. Quit");
            
            int choice = inputter.getInt("Choose an option: ", 1);

            switch (choice) {
                case 1: // Register customers (Source 1, Fn 1)
                    customerManager.addNew();
                    break;
                    
                case 2: // Update customer information (Source 1, Fn 2)
                    customerManager.update();
                    break;
                    
                case 3: // Search customer by name (Source 1, Fn 3)
                    customerManager.searchByName();
                    break;
                    
                case 4: // Display feast menus (Source 1, Fn 4)
                    menuManager.showAll();
                    break;
                    
                case 5: // Place order (Source 1, Fn 5)
                    // Kiểm tra xem có menu để đặt chưa
                    if (menuManager.isEmpty()) {
                        System.out.println("Feast Menu is empty. Cannot place order.");
                    } else {
                        orderManager.addNew();
                    }
                    break;
                    
                case 6: // Update order (Source 1, Fn 6)
                    orderManager.update();
                    break;
                    
                case 7: // Save to file (Source 1, Fn 7)
                    customerManager.saveFile();
                    orderManager.saveFile();
                    break;
                    
                case 8: // Display Lists (Source 1, Fn 8)
                    displayLists(inputter, customerManager, orderManager);
                    break;
                    
                case 9: // Quit
                    System.out.println("Exiting program. Goodbye!");
                    return;
                    
                default:
                    System.out.println("Invalid option! Please choose 1-9.");
            }
        }
    }

    // Hàm phụ xử lý menu con cho chức năng 8
    private static void displayLists(Inputter inputter, Customers c, Orders o) {
        System.out.println("--- Display Lists ---");
        System.out.println("1. Display Customer List");
        System.out.println("2. Display Order List");
        int subChoice = inputter.getInt("Select list to view: ", 1);
        
        if (subChoice == 1) {
            c.showAll();
        } else if (subChoice == 2) {
            o.showAll();
        } else {
            System.out.println("Invalid selection.");
        }
    }
}
