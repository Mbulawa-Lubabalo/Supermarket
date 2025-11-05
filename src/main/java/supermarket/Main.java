package supermarket;

import com.sun.net.httpserver.HttpServer;
import supermarket.controller.ProductController;
import supermarket.model.Product;
import supermarket.supabase.SupabaseClient;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://gddadnfbngafndmnbirp.supabase.co"; // replace with your Supabase URL
        String key = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImdkZGFkbmZibmdhZm5kbW5iaXJwIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjE2NTkzNjIsImV4cCI6MjA3NzIzNTM2Mn0.uOOqfJDHkqPsHoJXSJDM-rIJvuSwlF4p7DB0mDoCiUA";               // replace with your Supabase API key

        SupabaseClient client = new SupabaseClient(url, key);
        ProductController controller = new ProductController(client);

        // Add a new product
//        Product newProd = new Product(null, "Tablet", 299.99);
//        Product created = controller.addProduct(newProd);
//        System.out.println("Created: " + created);

        // Get all products
//        List<Product> products = controller.getAllProducts();
//        System.out.println("All products: ");
//        products.forEach(System.out::println);
//        System.out.println(controller.getAllProducts());

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/api/products", exchange -> {

            // ✅ Add CORS headers to allow browser access
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type, Authorization");

            
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1); // No content
                exchange.close();
                return;
            }
//            String response = "{\"message\": \"Hello from Java backend!\"}";
//            exchange.sendResponseHeaders(200, response.length());
//            OutputStream os = exchange.getResponseBody();
//            os.write(response.getBytes());
//            os.close();
            switch (exchange.getRequestMethod()) {
                case "GET" -> controller.handleGetAllProducts(exchange);
                default -> {
                    String msg = "{\"error\":\"Method not allowed\"}";
                    exchange.getResponseHeaders().set("Content-Type", "application/json");
                    exchange.sendResponseHeaders(405, msg.length());
                    exchange.getResponseBody().write(msg.getBytes());
                    exchange.close();
                }
            }
        });

        server.start();
        System.out.println("Server running on http://localhost:8080");

    }
}