/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.Serializable;
import java.util.Objects;

/**
 * Author: Adam
 * Java bean for a Product within the photography store.
 */
public class Product implements Serializable {
    private String productCode;
    private String productName;
    private String category;
    private String description;
    private String imageURL;
    private double price;

    /**
     * No argument constructor for the Product class.
     */
    public Product() {
        productCode= "";
        productName = "";
        category = "";
        description = "";
        imageURL = "imgs/xxxx.png";
        price = 0;
    }

    /**
     * Getter and setter for the product code, which is a unique, six-digit identifier for the product.
     */
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }


    /**
     * Getter and setter for the product name.
     */
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Getter and setter for the product category, which might include lenses, cameras, etc.
     */
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Getter and setter for the product description, which is displayed on the item detail page.
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter and setter for the product price.
     */
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Getter and setter for the product image URL, which points to the image in the "/web/imgs" directory.
     */
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    /**
     * Chceks if this product equals another based on the product code.
     * @param obj The object being compared to this object
     * @return true if the product codes of the two objects match
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (!Objects.equals(this.productCode, other.productCode)) {
            return false;
        }
        return true;
    }
    
    
}
