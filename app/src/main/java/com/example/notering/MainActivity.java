package com.example.notering;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notering.R;
import com.example.notering.model.Note;
import com.example.notering.ui.EditNoteActivity;
import com.example.notering.ui.NoteAdapter;
import com.example.notering.viewmodel.NoteViewModel;

public class MainActivity extends AppCompatActivity {

            private NoteViewModel noteViewModel;
            private EditText etTitle, etContent;
            private Button btnAdd;
            private NoteAdapter adapter;

            @Override
            protected void onCreate(Bundle savedInstanceState) {

                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                etTitle = findViewById(R.id.etTitle);
                etContent = findViewById(R.id.etContent);
                btnAdd = findViewById(R.id.btnAdd);

                RecyclerView recyclerView = findViewById(R.id.recyclerView);

                recyclerView.setLayoutManager(new LinearLayoutManager(this));

                adapter = new NoteAdapter();
                recyclerView.setAdapter(adapter);

                noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

                noteViewModel.getAllNotes().observe(this, notes -> {
                    adapter.setNotes(notes);
                });

                btnAdd.setOnClickListener(v -> {

                    String title = etTitle.getText().toString().trim();
                    String content = etContent.getText().toString().trim();

                    if (title.isEmpty() || content.isEmpty()) {
                        Toast.makeText(this, "Please fill both fields", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Note note = new Note(title, content);
                    noteViewModel.insert(note);

                    etTitle.setText("");
                    etContent.setText("");
                });


                new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                        Note noteToDelete = adapter.getNoteAt(viewHolder.getAbsoluteAdapterPosition());
                        noteViewModel.delete(noteToDelete);
                        Toast.makeText(MainActivity.this,"Note Deleted",Toast.LENGTH_SHORT).show();
                    }
                }).attachToRecyclerView(recyclerView);

                adapter.setOnItemClickListener(note -> {
                    Intent intent = new Intent(MainActivity.this, EditNoteActivity.class);
                    intent.putExtra("id", note.getId());
                    intent.putExtra("title", note.getTitle());
                    intent.putExtra("content", note.getContent());
                    startActivity(intent);
                });


            }
}