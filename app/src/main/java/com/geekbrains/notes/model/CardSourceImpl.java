package com.geekbrains.notes.model;

import android.content.res.Resources;

import com.geekbrains.notes.R;

import java.util.ArrayList;
import java.util.List;

public class CardSourceImpl implements CardsSource {
    private final List<NotesInfo> dataSource;
    private final Resources resources;

    public CardSourceImpl(Resources resources) {
        dataSource = new ArrayList<>(5);
        this.resources = resources;
    }

    @Override
    public int size() {
        return dataSource.size();
    }

    @Override
    public NotesInfo getCardData(int position) {
        return dataSource.get(position);
    }

    @Override
    public CardsSource init(CardsSourceResponse cardsSourceResponse) {
        String[] titles = resources.getStringArray(R.array.noteNames);
        String[] descriptions = resources.getStringArray(R.array.note_text);
        for (int i = 0; i < descriptions.length; i++) {
            dataSource.add(new NotesInfo(descriptions[i], titles[i]));
        }
        if (cardsSourceResponse != null){
            cardsSourceResponse.initialized(this);
        }
        return this;
    }
}
