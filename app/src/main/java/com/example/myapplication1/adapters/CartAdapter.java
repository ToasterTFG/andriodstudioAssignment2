package com.example.myapplication1.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication1.R;
import com.example.myapplication1.models.Book;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<Book> cartList;

    public CartAdapter(List<Book> cartList) {
        this.cartList = cartList;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        Book book = cartList.get(position);
        holder.title.setText(book.getTitle());
        holder.price.setText("R" + book.getPrice());
        holder.copies.setText("Qty: " + book.getCopies());
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView title, price, copies;

        public CartViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.cartBookTitle);
            price = itemView.findViewById(R.id.cartBookPrice);
            copies = itemView.findViewById(R.id.cartBookCopies);
        }
    }
}
