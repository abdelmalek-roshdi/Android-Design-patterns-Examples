package com.example.firebasemvvmdemo.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebasemvvmdemo.entities.Book;
import com.example.firebasemvvmdemo.R;

import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BooksViewHolder> {
    private Context context;
    private List<Book> books;
    LayoutInflater layoutInflater;
    BooksViewHolder booksViewHolder;

    public BooksAdapter(Context context){
        this.context = context;
        layoutInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public BooksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = layoutInflater.inflate(R.layout.book_row, parent,false);
        booksViewHolder = new BooksViewHolder(root);
        return booksViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BooksViewHolder holder, int position) {
        holder.setName(books.get(position).getName());
        holder.setId(books.get(position).getId());
    }

    @Override
    public int getItemCount() {

         if (books == null){
             return 0;
         }else {return books.size();}
    }
    public void setBooks(List<Book> bookList){
        if (bookList !=null) {
            this.books = bookList;
            notifyDataSetChanged();
        }
    }

     class BooksViewHolder extends RecyclerView.ViewHolder {
        private TextView id, name;
        public LinearLayout linear_layout;
        public BooksViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            //linear_layout = itemView.findViewById(R.id.linear_layout);
        }
        public void setName(String name){
            this.name.setText(name);
        }
        public void  setId(String id){
            this.id.setText(id);
        }
    }
}
