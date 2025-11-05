package supermarket.controller;

import supermarket.model.Product;
import supermarket.supabase.SupabaseClient;

import java.util.List;

public class ProductController {
    private final SupabaseClient supabaseClient;

    public ProductController(SupabaseClient supabaseClient) {
        this.supabaseClient = supabaseClient;
    }

    public List<Product> getAllProducts() {
        try {
//            System.out.println(supabaseClient.fetchAllProducts());
            return supabaseClient.fetchAllProducts();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching products", e);
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
