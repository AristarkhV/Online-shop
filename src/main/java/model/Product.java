package model;

import java.util.Objects;

public class Product {

    private Long productID;
    private String name;
    private String description;
    private Double price;

    public Product(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.productID = IdCreator.idCreator();
    }

    public Product(Long productID, String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return productID.equals(product.productID) &&
                name.equals(product.name) &&
                Objects.equals(description, product.description) &&
                price.equals(product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID, name, description, price);
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }
}
