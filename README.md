# 🛒 Supermarket Backend API

A lightweight **Java-based REST API** that connects to a **Supabase** database to manage products.  
Built **without frameworks** — using only the Java Standard Library (`HttpServer`, `HttpClient`) and **Jackson** for JSON handling.

---

## 🚀 Features

- ✅ Fetch all products from Supabase
- ➕ Add new products to the database
- ⚙️ Lightweight HTTP server (no Spring Boot or frameworks)
- 📦 JSON-based request/response handling
- 🌐 CORS enabled (works with frontends like React or Vercel)

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
- Maven or Gradle (for dependencies)
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

1️⃣ Clone the repository

- git clone https://github.com/your-username/Supermarket.git
- cd Supermarket


2️⃣ Add dependencies
```test
<dependencies>
  <dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.17.0</version>
  </dependency>
</dependencies>
```

3️⃣ Configure Supabase credentials

- String url = "https://YOUR_PROJECT.supabase.co";
- String key = "YOUR_SUPABASE_API_KEY";


4️⃣ Run the server
- ?????????????????????????????????????????


🌐 API Endpoints
- GET /api/products

- Fetch all products from Supabase.
- curl http://localhost:8080/api/products

Response:
```text
[
{
"id": 1,
"name": "Laptop",
"brand": "MSI",
"price": 1200.0,
"stockquantity": 5
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