package manzano.wilson.multiapp.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import manzano.wilson.multiapp.Objects.Gallery;
import manzano.wilson.multiapp.R;
import manzano.wilson.multiapp.Recycler.RecyclerAdapaterGalery;

public class FragmentGallery extends Fragment implements OnCompleteListener<QuerySnapshot> {

    private static final String TAG = "Fragment Galley";
    //Arraylist that save the orders
    public ArrayList<Gallery> mArrayList = new ArrayList<>();
    //My classes
    private RecyclerView mRecyclerView;
    private RecyclerAdapaterGalery mAdapter;
    private FirebaseFirestore mDataBase;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_gallery, container, false);
        mRecyclerView = result.findViewById(R.id.recyclerOrder);

        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new RecyclerAdapaterGalery(mArrayList);
        mRecyclerView.setAdapter(mAdapter);
        // Access a Cloud Firestore instance from your Activity

        mDataBase = FirebaseFirestore.getInstance();
        CollectionReference mCollectionReference = mDataBase.collection("photos");
        mCollectionReference.get().addOnCompleteListener(this);

        mCollectionReference.orderBy("date", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshots,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }

                for (DocumentChange dc : snapshots.getDocumentChanges()) {
                    switch (dc.getType()) {
                        case ADDED:
                            mArrayList.add(dc.getDocument().toObject(Gallery.class));
                            mAdapter.notifyDataSetChanged();
                            Log.d(TAG, "New city: " + dc.getDocument().getData());
                            break;
                        case MODIFIED:
                            Log.d(TAG, "Modified city: " + dc.getDocument().getData());
                            break;
                        case REMOVED:
                            Log.d(TAG, "Removed city: " + dc.getDocument().getData());
                            break;
                    }
                }
            }
        });
        return result;
    }


    @Override
    public void onComplete(@NonNull Task<QuerySnapshot> task) {
        if (task.isSuccessful()) {
            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                Gallery gallery = documentSnapshot.toObject(Gallery.class);
                //mArrayList.add(gallery);
            }
            Log.i("Length", String.valueOf(mArrayList.size()));
            //mAdapter.notifyDataSetChanged();
        } else {
            Log.w(TAG, "Error getting documents.", task.getException());
        }
    }


}
