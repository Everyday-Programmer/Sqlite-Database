package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Note> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton floatingActionButton = findViewById(R.id.add);
        floatingActionButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, AddNoteActivity.class)));

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);

        arrayList = new Database(MainActivity.this).getNotes();
        NoteAdapter adapter = new NoteAdapter(MainActivity.this, arrayList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener((view, pos) -> {
            Intent intent = new Intent(MainActivity.this, ViewNoteActivity.class);
            intent.putExtra("pos", pos);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        arrayList.clear();
        arrayList = new Database(MainActivity.this).getNotes();
        NoteAdapter adapter = new NoteAdapter(MainActivity.this, arrayList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener((view, pos) -> {
            Intent intent = new Intent(MainActivity.this, ViewNoteActivity.class);
            intent.putExtra("pos", pos);
            startActivity(intent);
        });
    }
}