package com.geekbrains.notes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainInfoFragment extends Fragment {

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
        TextView textView = new TextView(getContext());
        if(NotesFragment.getNoteNameText() == null){
            String text = "Новая заметка" + NotesFragment.getTime();
            textView.setText(text);
        } else {
            String text = NotesFragment.getNoteNameText() + NotesFragment.getTime();
            textView.setText(text);
        }
        layout.addView(textView);
    }
}