package supermarket.model;

public class Product {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private int stockQuantity;

    public Product() {}
    public Product(Long id, String name, String description, Double price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }


    public Long getId() {return this.id;}
    public String getName() {return this.name;}
    public String getDescription() {return this.description;}
    public Double getPrice() {return this.price;}
    public int getStockQuantity() {return this.stockQuantity;}

    public void setName(String productName) {
        this.name = productName;
    }

    public void setPrice(double productPrice) {
        this.price = productPrice;
    }
}
