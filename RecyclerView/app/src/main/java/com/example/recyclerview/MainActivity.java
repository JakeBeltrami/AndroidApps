package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // Variables
    private List<Book> bookList = new ArrayList<>();

    // UI elements
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitialiseUI(savedInstanceState);
    }

    private void InitialiseUI(Bundle bundle)
    {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MyAdapter(bookList);
        recyclerView.setAdapter(mAdapter);

        PrepareBookData();
    }

    private void PrepareBookData()
    {
        Book book = new Book(R.drawable.apple_small, "Storm Front", "7/10");
        bookList.add(book);

        book = new Book(R.drawable.apple_small, "Fool Moon", "6/10");
        bookList.add(book);

        book = new Book(R.drawable.apple_small, "Grave Peril", "9/10");
        bookList.add(book);

        book = new Book(R.drawable.apple_small, "Summer Knight", "9/10");
        bookList.add(book);

        book = new Book(R.drawable.apple_small, "Death Masks", "10/10");
        bookList.add(book);

        book = new Book(R.drawable.apple_small, "Blood Rites", "8/10");
        bookList.add(book);

        book = new Book(R.drawable.apple_small, "Dead Beat", "9/10");
        bookList.add(book);

        book = new Book(R.drawable.apple_small, "Proven Guilty", "10/10");
        bookList.add(book);

        book = new Book(R.drawable.apple_small, "White Night", "8/10");
        bookList.add(book);

        book = new Book(R.drawable.apple_small, "Small Favour", "9/10");
        bookList.add(book);

        mAdapter.notifyDataSetChanged();
    }
}
