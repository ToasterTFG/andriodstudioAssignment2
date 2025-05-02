package com.example.myapplication1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication1.models.Book;

public class PaymentActivity extends AppCompatActivity {

    LinearLayout paymentContainer;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);

        paymentContainer = findViewById(R.id.paymentContainer);
        Button finishButton = findViewById(R.id.finishButton);

        for (Book book : CartManager.getCartItems()) {
            TextView bookInfo = new TextView(this);
            bookInfo.setText(
                    "Book: " + book.getTitle() + "\n" +
                            "Author: " + book.getAuthor() + "\n" +
                            "Price: R" + book.getPrice()*book.getQuantity() + "\n" +
                            "Bank Account: 551248220\n" +
                            "Bank Name: FNB\n" +
                            "Reference: " + book.getTitle().replace(" ", "_")
            );
            bookInfo.setPadding(0, 0, 0, 24);
            paymentContainer.addView(bookInfo);
        }

        finishButton.setOnClickListener(v -> {
            CartManager.clearCart();  // Optional: clear cart after payment
            Intent intent = new Intent(PaymentActivity.this, HomeActivity.class);  // replace with your actual home activity class
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}
