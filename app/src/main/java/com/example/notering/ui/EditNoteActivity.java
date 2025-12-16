package com.example.notering.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.notering.R;
import com.example.notering.model.Note;
import com.example.notering.viewmodel.NoteViewModel;

public class EditNoteActivity extends AppCompatActivity {

    private EditText etEditTitle, etEditContent;
    private Button btnSave;
    private NoteViewModel noteViewModel;

    private int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        etEditTitle = findViewById(R.id.etEditTitle);
        etEditContent = findViewById(R.id.etEditContent);
        btnSave = findViewById(R.id.btnSave);

        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);


        Intent intent = getIntent();
        noteId = intent.getIntExtra("id", -1);
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");

        etEditTitle.setText(title);
        etEditContent.setText(content);

        btnSave.setOnClickListener(v -> {
            String newTitle = etEditTitle.getText().toString().trim();
            String newContent = etEditContent.getText().toString().trim();

            Note updatedNote = new Note(newTitle, newContent);
            updatedNote.setId(noteId);

            noteViewModel.update(updatedNote);

            Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}