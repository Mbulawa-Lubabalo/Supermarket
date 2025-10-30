
import fetchData from "./fetchData.js";

let products = await fetchData();
let cart = {};


const cartItemsContainer = document.getElementById('cart-items-container');
const emptyCartMessage = document.getElementById('empty-cart-message');
const cartTotalDisplay = document.getElementById('cart-total');
const messageBox = document.getElementById('message-box');
const messageContent = document.getElementById('message-content');
const messageIcon = document.getElementById('message-icon');

const renderCart = () => {
    const cartItemKeys = Object.keys(cart);
    cartItemsContainer.innerHTML = '';
    let total = 0;
    let totalItems = 0;

    if (cartItemKeys.length === 0) {
        emptyCartMessage.classList.remove('hidden');
        cartItemsContainer.appendChild(emptyCartMessage);
    } else {
        emptyCartMessage.classList.add('hidden');

        cartItemKeys.forEach(id => {
            const item = cart[id];
//             adding cart items
            total += item.product.price * item.quantity;
            totalItems += item.quantity;

            const cartItemEl = document.createElement('div');
            cartItemEl.className = 'cart-item';
            cartItemEl.innerHTML = `
                <div class="item-details">
                    <p class="item-name">${item.product.name}</p>
                    <p class="item-price">R${item.product.price.toFixed(2)} x ${item.quantity}</p>
                </div>
                <div class="item-controls">
                    <button onclick="updateCartQuantity(${id}, -1)" class="quantity-btn decrement">-</button>
                    <span class="item-quantity">${item.quantity}</span>
                    <button onclick="updateCartQuantity(${id}, 1)" class="quantity-btn increment">+</button>
                </div>
            `;
            cartItemsContainer.appendChild(cartItemEl);
        });
    }

    cartTotalDisplay.textContent = `R${total.toFixed(2)}`;
    updateCartIconCount(totalItems);
}


const showMessage = (content, type = 'info') => {
    messageContent.textContent = content;

    messageIcon.style.color = 'var(--color-primary)';
    messageIcon.style.stroke = 'var(--color-primary)';

    if (type === 'error') {
        messageIcon.style.color = '#ef4444';
        messageIcon.style.stroke = '#ef4444';

        messageIcon.innerHTML = '<path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M10 14l2-2m0 0l2-2m-2 2l-2-2m2 2l2 2m7-2a9 9 0 11-18 0 9 9 0 0118 0z"></path>';
    } else {
        messageIcon.style.color = 'var(--color-primary)';
        messageIcon.style.stroke = 'var(--color-primary)';
        // Default icon
        messageIcon.innerHTML = '<path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"></path>';
    }

    messageBox.classList.remove('hidden');
    document.body.style.overflow = 'hidden';
}

const hideMessage = () => {
    messageBox.classList.add('hidden');
    document.body.style.overflow = '';
}


const addToCart = (productId) => {
    const product = products.find(p => p.id === productId);

    if (!product || product.stockQuantity === 0) {
        console.error("Product not found or out of stock.");
        showMessage("Sorry, that product is currently out of stock.", 'error');
        return;
    }

    if (cart[productId]) {
        if (cart[productId].quantity < product.stockQuantity) {
            cart[productId].quantity += 1;
        } else {
            showMessage('Cannot add more: the maximum stock quantity has been reached!', 'info');
            return;
        }
    } else {
        cart[productId] = { product: product, quantity: 1 };
    }

    renderCart();
    showMessage(`${product.name} added to cart!`, 'info');
}

window.addToCart = addToCart;
window.hideMessage = hideMessage;
export default addToCart;