package com.geekbrains.notes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.geekbrains.notes.model.NotesInfo;


public class NotesFragment extends Fragment {

    private EditText noteName;
    private EditText note;
    private static String noteNameText;
    private static String noteText;
    private Button saveBtn;
    private static long time;
    public static final String ARG_NOTE = "note";

    public static NotesFragment newInstance(NotesInfo notesInfo) {
        NotesFragment f = new NotesFragment();

        Bundle args = new Bundle();
        args.putParcelable(ARG_NOTE, notesInfo);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        noteName = view.findViewById(R.id.noteName);
        note = view.findViewById(R.id.note);
        saveBtn = view.findViewById(R.id.button);
        saveNote();
    }

    private void saveNote() {
        saveBtn.setOnClickListener(v -> {
            noteNameText = String.valueOf(noteName.getText());
            noteText = String.valueOf(note.getText());
            time = SystemClock.elapsedRealtime();
        });
    }

    public static String getNoteNameText() {
        return noteNameText;
    }

   public static String getNoteText(){
        return noteText;
   }

    public static long getTime() {
        return time;
    }
}