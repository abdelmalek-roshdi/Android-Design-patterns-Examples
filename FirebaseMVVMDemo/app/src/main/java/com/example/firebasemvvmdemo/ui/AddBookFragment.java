package com.example.firebasemvvmdemo.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.firebasemvvmdemo.R;
import com.example.firebasemvvmdemo.entities.Book;
import com.example.firebasemvvmdemo.viewModels.BooksViewModel;

public class AddBookFragment extends Fragment {
    public static final String TAG = "AddBookFragment";
    EditText editText;
    Button button;
    private BooksViewModel booksViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_fragment, container, false);
        initViews(v);
        initViewModel();
        handleActions();
        return v;
    }

    private void initViewModel() {
        booksViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(BooksViewModel.class);

    }

    private void handleActions() {
        button.setOnClickListener((v)->{
            if (!TextUtils.isEmpty(editText.getText().toString())){
                Book book = new Book();
                book.setName(editText.getText().toString());
                editText.setText("");
                booksViewModel.addBookToUser(book);
               getActivity().onBackPressed();
            }
        });
    }

    private void initViews(View itemView) {
        editText = itemView.findViewById(R.id.bookNameEditText);
        button = itemView.findViewById(R.id.doneBtn);
    }
}
