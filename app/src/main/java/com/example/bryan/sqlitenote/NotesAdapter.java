package com.example.bryan.sqlitenote;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {
    private Context context;
    private List<Note> noteList;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.note_list_row, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
            Note note = noteList.get(i);

        myViewHolder.note.setText(note.getNote());

        myViewHolder.dot.setText(Html.fromHtml("&#8226"));

        myViewHolder.timestamp.setText(formatDate(note.getTimestamp()));

    }

    private String formatDate(String dateStr) {
        try{
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = fmt.parse(dateStr);
            SimpleDateFormat fmtOut = new SimpleDateFormat("MMM d");
            return fmtOut.format(date);

        }catch (ParseException e){

        }
        return "";
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView note;
        public TextView dot;
        public TextView timestamp;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            note = itemView.findViewById(R.id.note);
            dot = itemView.findViewById(R.id.dot);
            timestamp = itemView.findViewById(R.id.timestamp);

        }
    }
    public NotesAdapter(Context context, List<Note> noteList){
        this.context = context;
        this.noteList = noteList;

    }

}
