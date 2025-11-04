package supermarket.model;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;


public class ProductTest {

    @Test
    void testProductConstructorAndGetters() {
        Product product = new Product(1, "Apple", "Fresh apple", 1.0, 10);

//        assertThat(product.getId()).isEqualTo(1L);
        assertEquals(product.getId(), 1, 0.001);
        assertEquals(product.getName(), "Apple");
        assertEquals(product.getDescription(), "Fresh apple");
        assertEquals(product.getPrice(), 1.0, 0.01);
        assertEquals(product.getStockQuantity(), 10);

    }

    @Test
    void testSetters() {
        Product product = new Product();
        product.setName("Milk");
        product.setPrice(5.99);

        assertEquals(product.getName(), "Milk");
        assertEquals(product.getPrice(), 5.99, 0.01);

    }

}
