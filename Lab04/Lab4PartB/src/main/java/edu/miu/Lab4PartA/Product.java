package edu.miu.Lab4PartA;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long productnumber;
    private String name;
    private double price;

    @OneToMany(mappedBy = "product")
    private List<OrderLine> orderLines;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productnumber=" + productnumber +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", orderLines=" + orderLines +
                '}';
    }
}