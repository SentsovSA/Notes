package com.geekbrains.notes;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainInfoFragment extends Fragment {

    private NotesInfo currentNote;
    private boolean isLandscape;
    public static final String CURRENT_NOTE = "CurrentNote";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initNotesNames(view);
    }

    private void initNotesNames(View view) {
        LinearLayout layout = (LinearLayout) view;
        String[] noteNames = getResources().getStringArray(R.array.noteNames);

        for (int i = 0; i < noteNames.length; i++) {
            String city = noteNames[i];
            TextView tv = new TextView(getContext());
            tv.setText(city);
            tv.setTextSize(30);
            layout.addView(tv);
            final int fi = i;
            tv.setOnClickListener(v -> {
                currentNote = new NotesInfo(fi, getResources().getStringArray(R.array.noteNames)[fi]);
                showNote(currentNote);
            });
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(CURRENT_NOTE, currentNote);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        isLandscape = getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;

        if (savedInstanceState != null) {
            currentNote = savedInstanceState.getParcelable(CURRENT_NOTE);
        } else {
            currentNote = new NotesInfo(0, getResources().getStringArray(R.array.noteNames)[0]);
        }

        if (isLandscape) {
            showLandNote(currentNote);
        }
    }

    private void showNote(NotesInfo currentNote) {
        if (isLandscape) {
            showLandNote(currentNote);
        } else {
            showPortNote(currentNote);
        }
    }

    private void showLandNote(NotesInfo currentNote) {
        NotesFragment detail = NotesFragment.newInstance(currentNote);

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.note, detail);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    private void showPortNote(NotesInfo currentNote) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), NoteActivity.class);
        intent.putExtra(NotesFragment.ARG_NOTE, currentNote);
        startActivity(intent);
    }

        /*if(NotesFragment.getNoteNameText() == null){
            String text = "Новая заметка" + NotesFragment.getTime();
            textView.setText(text);
        } else {
            String text = NotesFragment.getNoteNameText() + NotesFragment.getTime();
            textView.setText(text);
        }
        layout.addView(textView);*/
}