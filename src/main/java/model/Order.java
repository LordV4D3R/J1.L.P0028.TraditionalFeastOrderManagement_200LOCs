/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Order implements Serializable, Comparable<Order> {
    private String id;
    private String customerId;
    private String setMenuId;
    private int numOfTables;
    private Date eventDate;

    public Order(String customerId, String setMenuId, int numOfTables, Date eventDate) {
        this.id = generateId(); // Auto-generate ID based on time
        this.customerId = customerId;
        this.setMenuId = setMenuId;
        this.numOfTables = numOfTables;
        this.eventDate = eventDate;
    }

    // Generate unique ID (Source 2 guideline)
    private String generateId() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }

    // Getters and Setters
    public String getId() { return id; }
    // ID is auto-generated, usually no setter needed, but kept if strictly required
    
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getSetMenuId() { return setMenuId; }
    public void setSetMenuId(String setMenuId) { this.setMenuId = setMenuId; }

    public int getNumOfTables() { return numOfTables; }
    public void setNumOfTables(int numOfTables) { this.numOfTables = numOfTables; }

    public Date getEventDate() { return eventDate; }
    public void setEventDate(Date eventDate) { this.eventDate = eventDate; }

    // Check duplicate: Customer + Menu + Date (Source 1 logic)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Order other = (Order) obj;
        return Objects.equals(customerId, other.customerId) &&
               Objects.equals(setMenuId, other.setMenuId) &&
               Objects.equals(eventDate, other.eventDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, setMenuId, eventDate);
    }

    // Sort by Event Date ascending (Requirements Source 1)
    @Override
    public int compareTo(Order o) {
        return this.eventDate.compareTo(o.eventDate);
    }
    
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return String.format("ID: %s | Cus: %s | Menu: %s | Date: %s | Tables: %d",
                id, customerId, setMenuId, sdf.format(eventDate), numOfTables);
    }
}