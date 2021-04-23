package com.geekbrains.notes.model;

import java.util.List;

public interface CardsSource {
    int size();
    NotesInfo getCardData(int position);
    CardsSource init(CardsSourceResponse cardsSourceResponse);
    void addCardData(NotesInfo notesInfo);
}
