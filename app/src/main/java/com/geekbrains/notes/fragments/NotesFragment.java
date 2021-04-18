package com.geekbrains.notes.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.geekbrains.notes.R;
import com.geekbrains.notes.model.NotesInfo;


public class NotesFragment extends Fragment {

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
        EditText noteName = view.findViewById(R.id.noteName);
        EditText note = view.findViewById(R.id.note);
        Button saveBtn = view.findViewById(R.id.button);
    }
}