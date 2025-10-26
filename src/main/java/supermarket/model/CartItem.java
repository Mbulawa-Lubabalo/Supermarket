package supermarket.model;

public class CartItem {

    private Long id;
    private Product product;
    private int quantity;
    private double totalPrice;

    public CartItem() {}
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = product.getPrice() * quantity;
    }

    public Long getId() {return this.id;}

    public Product getProduct() {return product;}

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

}
