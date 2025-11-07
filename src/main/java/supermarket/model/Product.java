package supermarket.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



/**
 * Represents a product entity in the system.
 * <p>
 * This class models a product with attributes such as ID, name, brand, price, and stock quantity.
 * It is designed to be serialized/deserialized using Jackson for easy conversion to and from JSON.
 * Unknown JSON fields will be ignored during deserialization.
 * </p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    private Integer id;
    private String name;
    private String brand;
    private Double price;
    private int stockquantity;
    private String category;

    public Product() {}
    public Product(Integer id, String name, String brand, Double price, int stockquantity, String category) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.stockquantity = stockquantity;
        this.category = category;
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
    public int getStockquantity() {return this.stockquantity;}
    public String getCategory() {return category;}

    public void setName(String productName) {
        this.name = productName;
    }
    public void setPrice(double productPrice) {
        this.price = productPrice;
    }


    /**
     * Converts this Product object into a JSON string representation.
     *
     * @return JSON-formatted string of the product
     * @throws RuntimeException if conversion to JSON fails
     */
    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
