package com.example.notering.repository;
import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.notering.dao.NoteDao;
import com.example.notering.database.NoteDatabase;
import com.example.notering.model.Note;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;
    private ExecutorService executorService;


    public NoteRepository (Application application){

        NoteDatabase noteDatabase =  NoteDatabase.getInstance(application);
        noteDao = noteDatabase.noteDao();
        allNotes = noteDao.getAllNotes();

        executorService = Executors.newSingleThreadExecutor();
    }

    public void insert(Note note){
        executorService.execute(() -> noteDao.insert(note));
    }

    public void update(Note note){
        executorService.execute(() -> noteDao.update(note));
    }

    public void delete(Note note){
        executorService.execute(() -> noteDao.delete(note));
    }

    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }



}
