package com.example.firebasemvvmdemo.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebasemvvmdemo.adapters.BooksAdapter;
import com.example.firebasemvvmdemo.viewModels.BooksViewModel;
import com.example.firebasemvvmdemo.utils.Constants;
import com.example.firebasemvvmdemo.entities.Book;
import com.example.firebasemvvmdemo.R;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import static com.example.firebasemvvmdemo.utils.Constants.booksReference;

public class BooksFragment extends Fragment {
    public static final String TAG = "BooksFragment";
    private BooksViewModel booksViewModel;
    private RecyclerView booksRecyclerView;
    BooksAdapter booksAdapter;
    ValueEventListener booksListener;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.books_fragment, container, false);
        initView(view);
        intViewModel();
        getBooks();
        addBooksListener();
        return view;
    }

    private void getBooks() {
        booksViewModel.getAllBooks(Constants.currentUserID);

    }

    private void addBooksListener() {
        booksViewModel.getBooksLiveData().observe(requireActivity(), new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> bookList) {
                setBooks(bookList);
            }
        });
       /*
        booksListener = booksReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getBooks();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        */
    }

    private void setBooks(List<Book> bookList) {
        booksAdapter.setBooks(bookList);

    }

    private void intViewModel() {
        if (booksViewModel == null){
           // booksViewModel = ViewModelProviders.of(requireActivity()).get(BooksViewModel.class);

            booksViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(BooksViewModel.class);
        }
    }

    private void initView(View itemView) {
        booksRecyclerView = itemView.findViewById(R.id.recyclerView);
        booksAdapter = new BooksAdapter(getActivity());
        booksRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        booksRecyclerView.setAdapter(booksAdapter);
    }

    @Override
    public void onDetach() {
        booksViewModel = null;
        booksReference.removeEventListener(booksListener);
        super.onDetach();
    }
}
