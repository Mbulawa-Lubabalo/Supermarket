package supermarket.model;

import java.util.List;

public class Order {

    private Long id;
    private double totalAmount;
    private List<OrderItem> orderItems;

    public Order() {}

    public Long getId() {
        return id;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
}
