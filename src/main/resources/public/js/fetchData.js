import { createClient } from 'https://esm.sh/@supabase/supabase-js@2';

let products = [];
const loadingMessage = document.getElementById('loading-message');


const fetchData = async () => {
    try {
        // Fetch data from backend
        const res = await fetch("http://localhost:8080/api/products");
        const data = await res.json();

        await new Promise(resolve => setTimeout(resolve, 800));

        // Hide loading message (if element exists)
//        const loadingMessage = document.getElementById('loadingMessage');
        if (loadingMessage) {
            loadingMessage.classList.add('hidden');
        }

        return data;
    } catch (err) {
        console.error("Error fetching products:", err);
        return [];
    }
};

export default fetchData;
