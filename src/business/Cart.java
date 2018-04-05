package business;

import data.ProductDB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Adam
 * Java bean for a Cart in the store. Contains a list of OrderItems and methods to add and remove OrderItems, as well
 * as various utility methods dealing with the cart.
 */
public class Cart implements Serializable {
    private List<OrderItem> items;

    /**
     * No argument constructor for the Cart.
     */
    public Cart() {
        items = new ArrayList<>();
    }

    /**
     * Adds the specified product in the given quantity to the list of OrderItems.
     * @param product the product to be added
     * @param quantity the amount of the product in the cart
     */
    public void addItem(Product product, int quantity) {
        boolean inCart = false;
        for(OrderItem orderItem : items) {
            if(orderItem.getProduct().equals(product)) {
                orderItem.setQuantity(quantity);
                inCart = true;
            }
        }
        if(!inCart) {
            OrderItem newOrderItem = new OrderItem();
            newOrderItem.setProduct(product);
            newOrderItem.setQuantity(quantity);
            items.add(newOrderItem);
        }
    }

    /**
     * Removes a given product from the cart, if it is found within the cart. If not found, no action is taken.
     * @param product the specified Product to be removed from the cart
     */
    public void removeItem(Product product) {
        int index = -1;
        for(int i = 0; i < items.size(); i++) {
            if(items.get(i).getProduct().equals(product)) {
                index = i;
            }
        }
        if(index != -1) {
            items.remove(index);
        }
    }

    /**
     * Removes any products in the cart with a product code matching the parameter.
     * @param productCode the product code to be checked
     */
    public void removeItem(String productCode) {
        Product product = ProductDB.getProduct(productCode);
        int index = -1;
        for(int i = 0; i < items.size(); i++) {
            if(items.get(i).getProduct().equals(product)) {
                index = i;
            }
        }
        if(index != -1) {
            items.remove(index);
        }
    }

    /**
     * Getter for the order items.
     * @return a list of OrderItems contained in this Cart.
     */
    public List<OrderItem> getItems() {
        return items;
    }

    /**
     * Clears the contents of the cart.
     */
    public void emptyCart() {
        items.clear();
    }

    /**
     * Checks if this cart is empty.
     * @return true if the list of OrderItems is empty.
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * Setter for the list of OrderItems in the cart.
     * @param orderItems the object that the items field will be set to
     */
    public void setItems(List<OrderItem> orderItems) {
        this.items = orderItems;
    }

    /**
     * Gets the total cost of all OrderItems in the cart, before tax.
     * @return the total cost of the contents of the cart, before tax
     */
    public double getTotal() {
        double total = 0;
        for(OrderItem item : items) {
            total += item.getTotal();
        }
        return total;
    }

    /**
     * Checks if the cart contains a Product with the given product code
     * @param productCode the product code to be checked
     * @return true if the cart contains any Products with the given product code
     */
    public boolean contains(String productCode) {
        for(OrderItem o : items) {
            if(o.getProduct().getProductCode().equals(productCode)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the OrderItem for the given Product
     * @param p the Product to be checked within the cart
     * @return the OrderItem that matches the given Product, or null if the cart does not contain that Product
     */
    public OrderItem getOrderItem(Product p) {
        for(OrderItem o : items) {
            if(o.getProduct().equals(p)) {
                return o;
            }
        }
        return null;
    }
}
