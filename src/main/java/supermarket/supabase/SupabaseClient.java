package supermarket.supabase;

import supermarket.model.Product;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;


/**
 * A lightweight client for interacting with the Supabase REST API.
 * <p>
 * This class provides methods for performing basic CRUD-like operations
 * (currently fetching and adding products) using HTTP requests to the Supabase backend.
 * </p>
 *
 * <p>
 * It relies on the Java {@link java.net.http.HttpClient} for making HTTP requests
 * and Jackson's {@link ObjectMapper} for JSON serialization and deserialization.
 * </p>
 */
public class SupabaseClient {
    private final String supabaseUrl;
    private final String supabaseKey;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public SupabaseClient(String supabaseUrl, String supabaseKey) {
        this.supabaseUrl = supabaseUrl;
        this.supabaseKey = supabaseKey;
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Fetches all products from the "products" table in Supabase.
     *
     * <p>
     * This method sends an authenticated {@code GET} request to the Supabase REST API endpoint
     * for the "products" table and deserializes the JSON response into a list of {@link Product} objects.
     * </p>
     *
     * @return a list of all products retrieved from Supabase
     * @throws Exception if the HTTP request fails or the JSON cannot be parsed
     */
    public List<Product> fetchAllProducts() throws Exception {
        String table = "products";
        String url = String.format("%s/rest/v1/%s", supabaseUrl, table);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("apikey", supabaseKey)
                .header("Authorization", "Bearer " + supabaseKey)
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed to fetch products: " + response.statusCode() + " -> " + response.body());
        }

        List<Product> products = objectMapper.readValue(response.body(), new TypeReference<List<Product>>(){});
        return products;
    }

    /**
     * Adds a new product to the "products" table in Supabase.
     *
     * <p>
     * This method sends a {@code POST} request to the Supabase REST API,
     * containing the serialized JSON representation of a {@link Product}.
     * </p>
     *
     * @param product the product to be added to the database
     * @return the {@link Product} object returned by Supabase, which may include an auto-generated ID
     * @throws Exception if the request fails or Supabase returns an error response
     */
    public Product addProduct(Product product) throws Exception {
        String table = "products";
        String url = String.format("%s/rest/v1/%s", supabaseUrl, table);

        String requestBody = objectMapper.writeValueAsString(product);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("apikey", supabaseKey)
                .header("Authorization", "Bearer " + supabaseKey)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 201 && response.statusCode() != 200) {
            throw new RuntimeException("Failed to add product: " + response.statusCode() + " -> " + response.body());
        }

        Product created = objectMapper.readValue(response.body(), Product.class);
        return created;
    }
}

