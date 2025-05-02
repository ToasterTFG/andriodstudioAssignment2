package com.example.myapplication1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication1.models.Book;
import com.example.myapplication1.utils.BookStore;

public class AddBookActivity extends AppCompatActivity {

    EditText titleInput, sellerInput, copiesInput, priceInput, bankInput;
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);  // Ensure this layout exists

        titleInput = findViewById(R.id.titleInput);
        sellerInput = findViewById(R.id.sellerInput);
        copiesInput = findViewById(R.id.copiesInput);
        priceInput = findViewById(R.id.priceInput);
        bankInput = findViewById(R.id.bankInput);
        submitBtn = findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener(v -> {
            try {
                String title = titleInput.getText().toString().trim();
                String seller = sellerInput.getText().toString().trim();
                int copies = Integer.parseInt(copiesInput.getText().toString().trim());
                double price = Double.parseDouble(priceInput.getText().toString().trim());
                String bank = bankInput.getText().toString().trim();

                Book newBook = new Book(title, seller, copies, price, bank);
                BookStore.getInstance().addBook(newBook);

                Toast.makeText(AddBookActivity.this, "Book added: " + title, Toast.LENGTH_SHORT).show();
                finish(); // Close activity and return to HomeActivity
            } catch (Exception e) {
                Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
