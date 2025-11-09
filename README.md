# 🛒 Supermarket Backend API

A lightweight **Java-based REST API** that connects to a **Supabase** database to manage products.  
Built **without frameworks** — using only the Java Standard Library (`HttpServer`, `HttpClient`) and **Jackson** for JSON handling.

---

## 🚀 Features

- ✅ Fetch all products from Supabase
- ➕ Add new products to the database
- ⚙️ Lightweight HTTP server
- 📦 JSON-based request/response handling


---

## 🧩 Project Structure

```text
src/
├─ supermarket/
│   ├─ Main.java                 
│   ├─ controller/
│   │    └─ ProductController.java 
│   ├─ supabase/
│   │    └─ SupabaseClient.java    
│   └─ model/
│        └─ Product.java           
```

⚙️ Requirements

- Java 17+
- Maven (for dependencies)
- Supabase project with a products table

```test
### 🧾 Example Supabase `products` Table

| Column         | Type     | Description           |
|----------------|----------|-----------------------|
| `id`           | int8     | Primary key           |
| `name`         | text     | Product name          |
| `brand`        | text     | Product brand         |
| `price`        | numeric  | Product price         |
| `stockquantity`| int8     | Quantity in stock     |

```

🧠 **Setup Instructions**

1️⃣ ***Clone the repository***

- git clone https://github.com/Mbulawa-Lubabalo/Supermarket.git
- cd Supermarket


4️⃣ ***Run the server***
- mvn clean package exec:java


🌐 API Endpoints
- GET /api/products

- Fetch all products from Supabase.
- curl http://localhost:8080/api/products

Response:
```text
[
    {
    "id": 1,
    "name": "Organic Apples",
    "brand": "Woolworths",
    "price": 34.99,
    "stockquantity": 50
    }
]
```

🧪 Testing

Unit tests can be written using JUnit 5.
```text
src/test/java/
└─ supermarket/
└─ ProductControllerTest.java
```

Run tests:
- mvn test



## 👨‍💻 Author

### Lubabalo Mbulawa
- 💼 GitHub: https://github.com/Mbulawa-Lubabalo/Supermarket.git
- ✉️ Email: lmbulawa4@gmail.com