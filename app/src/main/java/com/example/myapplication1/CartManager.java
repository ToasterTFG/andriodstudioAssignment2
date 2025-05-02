package com.example.myapplication1;

import com.example.myapplication1.models.Book;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    public static List<Book> cartItems = new ArrayList<>();

    public static void addToCart(Book book) {
        for (Book b : cartItems) {
            if (b.getTitle().equals(book.getTitle())) {
                b.setQuantity(b.getQuantity() + 1);
                return;
            }
        }

        book.setQuantity(1);
        cartItems.add(book);
    }

    public static List<Book> getCartItems() {
        return cartItems;
    }

    public static double getTotalPrice() {
        double total = 0;
        for (Book b : cartItems)
            total += b.getQuantity()*b.getPrice();
        return total;
    }

    public static void clearCart() {
        cartItems.clear();
    }
}