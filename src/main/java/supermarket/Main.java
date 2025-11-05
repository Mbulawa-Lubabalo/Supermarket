package supermarket;

import com.sun.net.httpserver.HttpServer;
import supermarket.controller.ProductController;
import supermarket.supabase.SupabaseClient;

import java.io.IOException;
import java.net.InetSocketAddress;


public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://gddadnfbngafndmnbirp.supabase.co";
        String key = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImdkZGFkbmZibmdhZm5kbW5iaXJwIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjE2NTkzNjIsImV4cCI6MjA3NzIzNTM2Mn0.uOOqfJDHkqPsHoJXSJDM-rIJvuSwlF4p7DB0mDoCiUA";

        SupabaseClient client = new SupabaseClient(url, key);
        ProductController controller = new ProductController(client);

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/api/products", exchange -> {


            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type, Authorization");


            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                exchange.close();
                return;
            }

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