
import fetchData from "./fetchData.js";

let products = await fetchData();
let cart = {};


const cartItemsContainer = document.getElementById('cart-items-container');
const emptyCartMessage = document.getElementById('empty-cart-message');
const cartTotalDisplay = document.getElementById('cart-total');

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