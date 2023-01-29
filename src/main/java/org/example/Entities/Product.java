package org.example.Entities;

import java.util.Objects;

public class Product {
    private Long pid;
    private String name;
    private String description;

    private byte[] image;
    private Double price;
    private Long cid;
    private String cname;
    private String base64;
    private Long quantity;

    public Product() {
    }

    public Product(Long pid, String name, String description, Long cid, String cname) {
        this.pid = pid;
        this.name = name;
        this.description = description;
        this.cid = cid;
        this.cname = cname;
    }

    public Product(String name, String description, double price, long cid) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.cid = cid;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public byte[] getImage() {
        return image;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
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

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", cid=" + cid +
                ", cname='" + cname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product that = (Product) o;
        return Objects.equals(pid, that.pid) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(cid, that.cid) && Objects.equals(cname, that.cname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid, name, description, cid, cname);
    }
}
