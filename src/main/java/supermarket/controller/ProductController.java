package supermarket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import supermarket.model.Product;
import supermarket.supabase.SupabaseClient;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ProductController {
    private final SupabaseClient supabaseClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ProductController(SupabaseClient supabaseClient) {
        this.supabaseClient = supabaseClient;
    }

    public List<Product> getAllProducts() throws Exception {
        try {
            return supabaseClient.fetchAllProducts();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching products", e);
        }
    }

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

    public Product addProduct(Product product) {
        try {
            return supabaseClient.addProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding product", e);
        }
    }
}
