package com.geekbrains.notes.model;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CardsSourceFirebaseImpl implements CardsSource {
    private static final String NOTES_COLLECTION = "notes";
    private static final String TAG = "[CardsSourceFirebaseImpl]";

    private FirebaseFirestore store = FirebaseFirestore.getInstance();

    private CollectionReference collection = store.collection(NOTES_COLLECTION);

    private List<NotesInfo> notesInfoList = new ArrayList<NotesInfo>();


    @Override
    public int size() {
        if (notesInfoList == null){
            return 0;
        }
        return notesInfoList.size();
    }

    @Override
    public NotesInfo getCardData(int position) {
        return notesInfoList.get(position);
    }

    @Override
    public CardsSource init(final CardsSourceResponse cardsSourceResponse) {
        collection.orderBy(CardDataMapping.Fields.TITLE, Query.Direction.DESCENDING).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        notesInfoList = new ArrayList<NotesInfo>();
                        for (QueryDocumentSnapshot document : task.getResult()){
                            Map<String, Object> doc = document.getData();
                            String id = document.getId();
                            NotesInfo notesInfo = CardDataMapping.toNotesInfo(id, doc);
                            notesInfo.add(notesInfo);
                        }
                        cardsSourceResponse.initialized(CardsSourceFirebaseImpl.this);
                    }
                });
        return null;
    }

    @Override
    public void addCardData(NotesInfo notesInfo) {
        collection.add(CardDataMapping.toDocument(notesInfo)).addOnSuccessListener(documentReference ->
                notesInfo.setId(documentReference.getId()));
    }
}
