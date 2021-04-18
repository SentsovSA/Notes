package com.geekbrains.notes.fragments;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geekbrains.notes.NoteActivity;
import com.geekbrains.notes.model.NotesAdapter;
import com.geekbrains.notes.R;
import com.geekbrains.notes.model.CardSourceImpl;
import com.geekbrains.notes.model.NotesInfo;

public class MainInfoFragment extends Fragment {

    private static NotesInfo currentNote;
    private boolean isLandscape;
    public static final String CURRENT_NOTE = "CurrentNote";

    public MainInfoFragment(NotesInfo currentNote) {
        MainInfoFragment.currentNote = currentNote;
    }

    public MainInfoFragment() {
    }

    public static MainInfoFragment newInstance() {
        return new MainInfoFragment(currentNote);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_info, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.noteList);
        initRecyclerView(recyclerView);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initRecyclerView(RecyclerView recyclerView){
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        NotesAdapter adapter = new NotesAdapter(new CardSourceImpl(getResources()).getData());
        recyclerView.setAdapter(adapter);
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

        if (isLandscape) {
            showNote(currentNote);
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
}