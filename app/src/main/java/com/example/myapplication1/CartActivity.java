package com.example.myapplication1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication1.models.Book;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    private LinearLayout cartContainer;
    private TextView totalPriceView;
    private Button checkoutButton;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart); // Your provided layout

        cartContainer = findViewById(R.id.cartContainer);
        totalPriceView = findViewById(R.id.totalPriceView);
        checkoutButton = findViewById(R.id.checkoutButton);

        List<Book> cartItems = CartManager.getCartItems();

        if (cartItems.isEmpty()) {
            Toast.makeText(this, "Your cart is empty!", Toast.LENGTH_SHORT).show();
        } else {
            for (Book book : cartItems) {
                addBookToLayout(book);
            }
        }

        totalPriceView.setText("Total: R" + CartManager.getTotalPrice());

        checkoutButton.setOnClickListener(v -> {
            if (cartItems.isEmpty()) {
                Toast.makeText(this, "Your cart is empty! Cannot proceed to checkout.", Toast.LENGTH_SHORT).show();
            } else {
                // Go to Payment Activity
                Intent intent = new Intent(CartActivity.this, PaymentActivity.class);
                startActivity(intent);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void addBookToLayout(Book book) {
        // Dynamically add book info using the layout placeholders
        TextView titleView = new TextView(this);
        titleView.setText(book.getTitle());
        titleView.setTextSize(18);
        titleView.setTextAppearance(this, android.R.style.TextAppearance_Medium);
        titleView.setPadding(0, 10, 0, 0);

        TextView priceView = new TextView(this);
        priceView.setText("Price: R" + book.getPrice());

        TextView qtyView = new TextView(this);
        qtyView.setText("Qty: " + book.getQuantity());

        cartContainer.addView(titleView);
        cartContainer.addView(priceView);
        cartContainer.addView(qtyView);
    }
}
