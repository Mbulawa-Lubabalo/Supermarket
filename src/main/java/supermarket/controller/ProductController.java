package supermarket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import supermarket.model.Product;
import supermarket.supabase.SupabaseClient;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;


/**
 * Controller class responsible for handling product-related operations.
 * <p>
 * This class acts as a bridge between the HTTP layer and the Supabase database client.
 * It supports retrieving and adding products through HTTP requests or direct method calls.
 * </p>
 */
public class ProductController {
    private final SupabaseClient supabaseClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ProductController(SupabaseClient supabaseClient) {
        this.supabaseClient = supabaseClient;
    }

    /**
     * Retrieves all products from the Supabase database.
     *
     * @return a list of all {@link Product} objects available in the database
     * @throws RuntimeException if an error occurs while fetching products
     */
    public List<Product> getAllProducts() throws Exception {
        try {
            return supabaseClient.fetchAllProducts();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching products", e);
        }
    }

    /**
     * Handles an incoming HTTP GET request to fetch all products.
     * <p>
     * This method retrieves all products from the database, converts them into JSON,
     * and writes the JSON response back to the client with a {@code 200 OK} status.
     * </p>
     *
     * @param exchange the {@link HttpExchange} object representing the current HTTP request and response
     * @throws IOException if an I/O error occurs while writing the response
     */
    public void handleGetAllProducts(HttpExchange exchange) throws IOException {
        try {
            String response = objectMapper.writeValueAsString(getAllProducts());

            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);

            try(OutputStream os = exchange.getResponseBody()){
                os.write(response.getBytes(StandardCharsets.UTF_8));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds a new product to the Supabase database.
     *
     * @param product the {@link Product} object to be added
     * @return the created {@link Product} returned from Supabase, possibly including an auto-generated ID
     * @throws RuntimeException if an error occurs while adding the product
     */
    public Product addProduct(Product product) {
        try {
            return supabaseClient.addProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding product", e);
        }
    }
}
