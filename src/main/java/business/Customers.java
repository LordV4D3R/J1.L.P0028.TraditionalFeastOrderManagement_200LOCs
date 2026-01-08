/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Customer;
import tools.Acceptable;
import tools.FileUtils;
import tools.Inputter;

public class Customers extends ArrayList<Customer> implements Workable<Customer> {
    private final String FILE_NAME = "customers.dat";
    private Inputter inputter = new Inputter();

    @Override
    public void addNew() {
        System.out.println("--- Register New Customer ---");
        // Validation loop using Inputter & Acceptable (Source 2 pattern)
        String id;
        while (true) {
            id = inputter.inputAndLoop("Enter ID (C/G/K + 4 digits): ", Acceptable.CUS_ID_VALID);
            if (searchById(id) == null) break;
            System.out.println("ID already exists!");
        }
        String name = inputter.inputAndLoop("Enter Name: ", Acceptable.NAME_VALID);
        String phone = inputter.inputAndLoop("Enter Phone: ", Acceptable.PHONE_VALID);
        String email = inputter.inputAndLoop("Enter Email: ", Acceptable.EMAIL_VALID);

        this.add(new Customer(id, name, phone, email));
        System.out.println("Customer registered successfully.");
    }

    // Function 3: Search by name (Partial match + Sort) (Source 1)
    public void searchByName() {
        String name = inputter.getString("Enter name to search: ").toLowerCase();
        List<Customer> result = new ArrayList<>();
        
        for (Customer c : this) {
            if (c.getName().toLowerCase().contains(name)) {
                result.add(c);
            }
        }

        if (result.isEmpty()) {
            System.out.println("No one matches the search criteria!");
        } else {
            Collections.sort(result); // Sort alphabetically
            System.out.println("Matching Customers:");
            for (Customer c : result) System.out.println(c);
        }
    }

    public Customer searchById(String id) {
        for (Customer c : this) {
            if (c.getId().equalsIgnoreCase(id)) return c;
        }
        return null;
    }

    @Override
    public void update() {
        String id = inputter.getString("Enter ID to update: ");
        Customer c = searchById(id);
        if (c == null) {
            System.out.println("Customer does not exist."); // Source 1
            return;
        }
        
        System.out.println("Updating info for " + c.getId() + " (Leave blank to keep old info)");
        // Logic: Input string -> Check empty -> Validate if not empty
        // This part needs careful handling of "blank to keep old"
        String newName = inputter.getString("New Name: ");
        if (!newName.isEmpty() && Acceptable.isValid(newName, Acceptable.NAME_VALID)) c.setName(newName);
        
        String newPhone = inputter.getString("New Phone: ");
        if (!newPhone.isEmpty() && Acceptable.isValid(newPhone, Acceptable.PHONE_VALID)) c.setPhone(newPhone);
        
        String newEmail = inputter.getString("New Email: ");
        if (!newEmail.isEmpty() && Acceptable.isValid(newEmail, Acceptable.EMAIL_VALID)) c.setEmail(newEmail);
        
        System.out.println("Update successful.");
    }

    @Override
    public void showAll() {
        if (this.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }
        Collections.sort(this); // Sort by Name (Source 1)
        System.out.println("--- Customer List ---");
        for (Customer c : this) System.out.println(c);
    }
    
    public void saveFile() {
        FileUtils.saveToFile(this, FILE_NAME);
    }
    
    public void loadFile() {
        this.addAll(FileUtils.readFromFile(FILE_NAME));
    }
}
