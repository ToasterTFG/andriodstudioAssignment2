package com.example.myapplication1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication1.adapters.BookAdapter;
import com.example.myapplication1.models.Book;
import com.example.myapplication1.utils.BookStore;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Book> bookList;
    private List<Book> filteredList;
    private EditText searchInput;
    private Button searchButton;
    private Button cartButton;
    private Button addBookButton;
    private BookAdapter bookAdapter;
    private boolean dummyDataAdded = false;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchInput = findViewById(R.id.searchInput);
        searchButton = findViewById(R.id.searchButton);
        cartButton = findViewById(R.id.cartButton);
        addBookButton = findViewById(R.id.addBookButton);

        bookList = new ArrayList<>();
        filteredList = new ArrayList<>();

        bookAdapter = new BookAdapter(filteredList);
        recyclerView.setAdapter(bookAdapter);

        searchButton.setOnClickListener(v -> {
            String query = searchInput.getText().toString().trim().toLowerCase();
            filteredList.clear();

            for (Book book : bookList) {
                if (book.getTitle().toLowerCase().contains(query) ||
                        book.getAuthor().toLowerCase().contains(query)) {
                    filteredList.add(book);
                }
            }

            bookAdapter.notifyDataSetChanged();

            if (filteredList.isEmpty()) {
                Toast.makeText(this, "No books found", Toast.LENGTH_SHORT).show();
            }
        });

        cartButton.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, CartActivity.class));
        });

        addBookButton.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, AddBookActivity.class));
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onResume() {
        super.onResume();

        // Add dummy data only once
        if (!dummyDataAdded && BookStore.getInstance().getBooks().isEmpty()) {
            BookStore.getInstance().addBook(new Book("coding for dummies", "toaster", 10, 159.99, "Bank A"));
            BookStore.getInstance().addBook(new Book("intro to surviving computering", "survivers", 7, 139.99, "Bank B"));
            dummyDataAdded = true;
        }

        bookList = BookStore.getInstance().getBooks();
        filteredList.clear();
        filteredList.addAll(bookList);
        bookAdapter.notifyDataSetChanged();
    }
}
