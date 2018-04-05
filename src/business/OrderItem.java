package business;

import util.StoreUtil;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * Author: Adam
 * Java bean that tracks a Product and quantity in an order.
 */
public class OrderItem implements Serializable {
    private Product product;
    private int quantity;

    /**
     * No argument constructor for the OrderItem.
     */
    public OrderItem() {
        product = new Product();
        quantity = 0;
    }


    /**
     * Getter for the product field
     * @return the value of the product field
     */
    public Product getProduct() {
        return product;
    }


    /**
     * Setter for the product field
     * @param product the new value of the product field
     */
    public void setProduct(Product product) {
        this.product = product;
    }


    /**
     * Getter for the quantity field
     * @return the value of the quantity field
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Setter for the quantity field
     * @param quantity the new value of the quantity field
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the total price of this OrderItem (price * quantity).
     * @return the total value of this OrderItem, rounded to two decimal places
     */
    public double getTotal() {
        return StoreUtil.toMoneyFormat(product.getPrice() * quantity);
    }
}
