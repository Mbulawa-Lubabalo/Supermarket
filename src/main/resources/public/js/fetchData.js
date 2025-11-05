import { createClient } from 'https://esm.sh/@supabase/supabase-js@2';

//const supabaseUrl = 'https://gddadnfbngafndmnbirp.supabase.co';
//const supabaseAnonKey = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImdkZGFkbmZibmdhZm5kbW5iaXJwIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjE2NTkzNjIsImV4cCI6MjA3NzIzNTM2Mn0.uOOqfJDHkqPsHoJXSJDM-rIJvuSwlF4p7DB0mDoCiUA';
//const supabase = createClient(supabaseUrl, supabaseAnonKey)

let products = [];

const loadingMessage = document.getElementById('loading-message');

//const mockProducts = [
//    { id: 1, name: "Organic Apples (1kg)", price: 3.99, brand: "EcoFarm", stockQuantity: 50, category: "Produce" },
//    { id: 2, name: "Whole Milk (1L)", price: 1.85, brand: "DairyDelight", stockQuantity: 200, category: "Dairy" },
//    { id: 3, name: "Instant Coffee", price: 6.50, brand: "QuickBrew", stockQuantity: 80, category: "Pantry" },
//    { id: 4, name: "Artisan Bread Loaf", price: 2.50, brand: "Baker's Dozen", stockQuantity: 30, category: "Bakery" },
//    { id: 5, name: "Sparkling Water (6-pack)", price: 4.50, brand: "AquaFresh", stockQuantity: 120, category: "Beverages" },
//    { id: 6, name: "Canned Tuna", price: 1.20, brand: "OceanCatch", stockQuantity: 150, category: "Canned Goods" },
//];

//const { data, error } = await supabase
//  .from('products')
//  .select("*")
//
//console.log(data)
//fetch("http://localhost:8080/api/products")
//    .then(res => res.json())
//    .then(data => console.log(data))
//
//const fetchData = async() => {
//    await new Promise(resolve => setTimeout(resolve, 800));
//    products = data;
//    loadingMessage.classList.add('hidden');
//
//    return products
//
//}

const fetchData = async () => {
    try {
        // Fetch data from backend
        const res = await fetch("http://localhost:8080/api/products");
        const data = await res.json();

        // Optional: simulate loading delay
        await new Promise(resolve => setTimeout(resolve, 800));

        // Hide loading message (if element exists)
//        const loadingMessage = document.getElementById('loadingMessage');
        if (loadingMessage) {
            loadingMessage.classList.add('hidden');
        }

        // Return products
        return data;
    } catch (err) {
        console.error("Error fetching products:", err);
        return [];
    }
};

//export default fetchData;


export default fetchData;
