package com.example.notering.ui;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notering.R;
import com.example.notering.model.Note;

import java.util.ArrayList;
import java.util.List;


public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private static List<Note> notes = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType){

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note,parent,false);
        return new NoteViewHolder(view,listener);

    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position){
        Note currentNote = notes.get(position);
        Log.d("NoteAdapter", "Title: " + currentNote.getTitle());
        holder.tvTitle.setText(currentNote.getTitle());
        holder.tvContent.setText(currentNote.getContent());

    }


    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> noteList){
        this.notes = noteList;
        notifyDataSetChanged();

    }

    static class NoteViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle, tvContent;

        public NoteViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvContent = itemView.findViewById(R.id.tvContent);

            itemView.setOnClickListener(v ->{
                if(listener != null && getAbsoluteAdapterPosition() != RecyclerView.NO_POSITION){
                    listener.OnItemClickListener(notes.get(getAbsoluteAdapterPosition()));
                }
            });
        }
    }
public Note getNoteAt(int position){
        return notes.get(position);
}


public interface OnItemClickListener{
        void OnItemClickListener(Note note);
}

public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
}

}
