package com.example.sqlite;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class AddNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        Button add = findViewById(R.id.addNote);
        TextInputLayout titleLayout = findViewById(R.id.titleLayout);
        TextInputEditText title, content;

        title = findViewById(R.id.titleET);
        content = findViewById(R.id.contentET);

        add.setOnClickListener(view -> {
            if (Objects.requireNonNull(title.getText()).toString().isEmpty()) {
                titleLayout.setError("Title is Required");
            } else {
                new Database(AddNoteActivity.this).insert(title.getText().toString(), Objects.requireNonNull(content.getText()).toString());
                finish();
            }
        });
    }
}