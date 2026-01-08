/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

public class SetMenu implements Serializable, Comparable<SetMenu> {

    private String code;
    private String name;
    private double price;
    private String ingredients;

    public SetMenu(String code, String name, double price, String ingredients) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }

    // Getters and Setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Code       : " + code + "\n"
                + "Name       : " + name + "\n"
                + "Price      : " + String.format("%,.0f", price) + " Vnd\n"
                + "Ingredients:\n" + ingredients + "\n"
                + "--------------------------------------------------";
    }

    // Sort by Price ascending (Requirements Source 1)
    @Override
    public int compareTo(SetMenu o) {
        return Double.compare(this.price, o.price);
    }
}
