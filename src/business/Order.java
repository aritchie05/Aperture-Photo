/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import util.StoreUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author: Adam
 * A Java bean for an Order in the store. Contains a list of OrderItems as well as information about the cost, date,
 * and User.
 */
public class Order implements Serializable {
    private int orderNumber;
    private String date;
    private User user;
    private List<OrderItem> items;
    private double taxRate;
    private double subtotal;
    private double totalCost;
    private boolean paid;

    /**
     * No argument constructor for an Order.
     */
    public Order() {
        orderNumber = 0;
        date = new Date().toString();
        user = new User();
        items = new ArrayList<>();
        taxRate = 0;
        totalCost = 0;
        paid = false;
    }

    /**
     * Getter for the order number
     * @return the order number associated with this order
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * Setter for the order number
     * @param orderNumber the order number to be set
     */
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * Getter for the date of the order
     * @return the date of the order as a String
     */
    public String getDate() {
        return date;
    }

    /**
     * Setter for the date of the order
     * @param date the new date to be set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Getter for the user associated with the order
     * @return the user associated with this order
     */
    public User getUser() {
        return user;
    }

    /**
     * Setter for the user associated with this order
     * @param user the user to be set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets a list of OrderItems that are listed in the order
     * @return a complete List of OrderItems in this order
     */
    public List<OrderItem> getItems() {
        return items;
    }

    /**
     * Setter for the OrderItems list in the order
     * @param items the new list of items to be set
     */
    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    /**
     * Getter for the tax rate associated with this order
     * @return the tax rate for the order
     */
    public double getTaxRate() {
        return taxRate;
    }

    /**
     * Setter for the tax rate in this order
     * @param taxRate the new tax rate to be set
     */
    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    /**
     * Getter for the subtotal price of the order (before tax is added)
     * @return the price of the order before tax
     */
    public double getSubtotal() {
        return subtotal;
    }

    /**
     * Setter for the subtotal of the order (before tax is added)
     * @param subtotal the new subtotal to be set
     */
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * Getter for the total cost of the order with tax added
     * @return the total cost of the order
     */
    public double getTotalCost() {
        return totalCost;
    }

    /**
     * Gets the total cost rounded to two decimal places
     * @return the total cost of the order, rounded to two decimal places
     */
    public double getTaxCost() {
        return StoreUtil.toMoneyFormat(taxRate * subtotal);
    }

    /**
     * Sets the total cost to a new value
     * @param totalCost the new total cost to be set
     */
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    /**
     * Getter for whether the order is paid for
     * @return true if the order is paid
     */
    public boolean isPaid() {
        return paid;
    }

    /**
     * Setter for whether the order is paid
     * @param paid true if the order is paid for
     */
    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
