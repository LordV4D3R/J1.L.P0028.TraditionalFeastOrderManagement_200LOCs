/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

public class Customer implements Serializable, Comparable<Customer> {
    private String id;
    private String name;
    private String phone;
    private String email;

    public Customer(String id, String name, String phone, String email) {
        this.id = id.toUpperCase(); // ID should be standardized
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return String.format("%-7s | %-20s | %-12s | %s", id, name, phone, email);
    }

    // Sort by Name alphabetically (Requirements Source 1)
    @Override
    public int compareTo(Customer o) {
        return this.name.compareToIgnoreCase(o.name);
    }
}