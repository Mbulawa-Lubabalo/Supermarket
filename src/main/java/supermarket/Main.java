package supermarket;

import supermarket.controller.ProductController;
import supermarket.model.Product;
import supermarket.supabase.SupabaseClient;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String url = "https://gddadnfbngafndmnbirp.supabase.co"; // replace with your Supabase URL
        String key = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImdkZGFkbmZibmdhZm5kbW5iaXJwIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjE2NTkzNjIsImV4cCI6MjA3NzIzNTM2Mn0.uOOqfJDHkqPsHoJXSJDM-rIJvuSwlF4p7DB0mDoCiUA";               // replace with your Supabase API key

        SupabaseClient client = new SupabaseClient(url, key);
        ProductController controller = new ProductController(client);

        // Add a new product
//        Product newProd = new Product(null, "Tablet", 299.99);
//        Product created = controller.addProduct(newProd);
//        System.out.println("Created: " + created);

        // Get all products
        List<Product> products = controller.getAllProducts();
        System.out.println("All products: ");
        products.forEach(System.out::println);
    }
}