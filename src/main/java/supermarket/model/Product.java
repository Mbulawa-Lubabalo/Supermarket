package supermarket.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    private Integer id;
    private String name;
    private String brand;
    private Double price;
    private int stockquantity;

    public Product() {}
    public Product(Integer id, String name, String brand, Double price, int stockquantity) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.stockquantity = stockquantity;
    }

    public Product(Integer id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }


    public Integer getId() {return this.id;}
    public String getName() {return this.name;}
    public String getBrand() {return this.brand;}
    public Double getPrice() {return this.price;}
    public int getStockQuantity() {return this.stockquantity;}

    public void setName(String productName) {
        this.name = productName;
    }

    public void setPrice(double productPrice) {
        this.price = productPrice;
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(this); //"Product{id=" + id + ", name='" + name + "', price=" + price + "}";
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
