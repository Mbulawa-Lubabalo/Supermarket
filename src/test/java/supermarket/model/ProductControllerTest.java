package supermarket.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import supermarket.controller.ProductController;
import supermarket.supabase.SupabaseClient;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductControllerTest {

    private SupabaseClient mockClient;
    private ProductController controller;

    @BeforeEach
    public void setUp() {
        mockClient = mock(SupabaseClient.class);
        controller = new ProductController(mockClient);
    }

    @Test
    public void testGetAllProducts_returnsProductList() throws Exception {
        // Arrange
        List<Product> mockProducts = Arrays.asList(
                new Product(1, "Laptop", 999.99),
                new Product(2, "Phone", 499.99)
        );
        when(mockClient.fetchAllProducts()).thenReturn(mockProducts);

        // Act
        List<Product> result = controller.getAllProducts();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Laptop", result.get(0).getName());
        verify(mockClient, times(1)).fetchAllProducts();
    }

    @Test
    public void testAddProduct_success() throws Exception {
        // Arrange
        Product newProduct = new Product(null, "Tablet", 299.99);
        Product createdProduct = new Product(3, "Tablet", 299.99);
        when(mockClient.addProduct(newProduct)).thenReturn(createdProduct);

        // Act
        Product result = controller.addProduct(newProduct);

        // Assert
        assertNotNull(result.getId());
        assertEquals("Tablet", result.getName());
        assertEquals(299.99, result.getPrice());
        verify(mockClient, times(1)).addProduct(newProduct);
    }

    @Test
    public void testGetAllProducts_handlesException() throws Exception {
        // Arrange
        when(mockClient.fetchAllProducts()).thenThrow(new RuntimeException("API error"));

        // Act & Assert
        RuntimeException ex = assertThrows(RuntimeException.class, () -> controller.getAllProducts());
        assertTrue(ex.getMessage().contains("Error fetching products"));
        verify(mockClient, times(1)).fetchAllProducts();
    }

    @Test
    public void testAddProduct_handlesException() throws Exception {
        // Arrange
        Product newProduct = new Product(null, "Tablet", 299.99);
        when(mockClient.addProduct(newProduct)).thenThrow(new RuntimeException("API error"));

        // Act & Assert
        RuntimeException ex = assertThrows(RuntimeException.class, () -> controller.addProduct(newProduct));
        assertTrue(ex.getMessage().contains("Error adding product"));
        verify(mockClient, times(1)).addProduct(newProduct);
    }
}

