package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);

        int pos;
        TextView title = findViewById(R.id.titleTv);
        TextView content = findViewById(R.id.contentTV);

        Intent intent = getIntent();
        if (intent != null) {
            pos = intent.getIntExtra("pos", 0);
            Note note = new Database(ViewNoteActivity.this).getNotes().get(pos);
            title.setText(note.getTitle());
            content.setText(note.getContent());
        }
    }
}