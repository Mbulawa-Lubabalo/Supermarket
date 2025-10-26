package supermarket.model;

public class OrderItem {

    private Long id;
    private Product product;
    private int quantity;
    private double subtotal;

    public OrderItem() {}
    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.subtotal = product.getPrice() * quantity;
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public double getSubtotal() {
        return subtotal;
    }
    public int getQuantity() {
        return quantity;
    }
}
