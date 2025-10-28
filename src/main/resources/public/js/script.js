//------------- Modules ----------------
import fetchData from "./fetchData.js";

let products = await fetchData();

// --- DOM Elements ---
const productGrid = document.getElementById('product-grid');

const renderProducts = () => {
    productGrid.innerHTML = '';

    products.forEach(product => {
        let stockClass = 'in-stock';
        if (product.stockQuantity <= 0) {
            stockClass = 'out-of-stock';
        } else if (product.stockQuantity <= 10) {
            stockClass = 'low-stock';
        }

        const stockText = product.stockQuantity > 0 ? `${product.stockQuantity} in stock` : 'Out of Stock';
        const buttonDisabled = product.stockQuantity === 0 ? 'disabled' : '';
        const buttonText = product.stockQuantity === 0 ? 'Sold Out' : 'Add to Cart';

        const productCard = document.createElement('div');
        productCard.className = 'product-card';

        // Using a utility function for price formatting to handle non-BigDecimals in mock data
        const formatPrice = (p) => p.toFixed(2);

        productCard.innerHTML = `
            <div class="product-card-info">
                <span class="category-tag">${product.category}</span>
                <h4 class="product-name">${product.name}</h4>
                <p class="product-brand">${product.brand}</p>
            </div>
            <div>
                <p class="product-price">R${formatPrice(product.price)}</p>
                <p class="stock-status ${stockClass}">${stockText}</p>
                <button
                    onclick="addToCart(${product.id})"
                    ${buttonDisabled}
                    class="add-to-cart-btn">
                    ${buttonText}
                </button>
            </div>
        `;
        productGrid.appendChild(productCard);
    });
}

renderProducts();
