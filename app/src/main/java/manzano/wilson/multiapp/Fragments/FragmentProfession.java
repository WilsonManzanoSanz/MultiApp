package manzano.wilson.multiapp.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import manzano.wilson.multiapp.Activitys.HomeDrawerActivity;
import manzano.wilson.multiapp.Objects.Profession;
import manzano.wilson.multiapp.R;
import manzano.wilson.multiapp.Recycler.RecyclerAdapaterGalery;

/**
 * Created by User on 29/11/2017.
 */

public class FragmentProfession extends Fragment implements View.OnClickListener {
    @Nullable

    private  RecyclerView mRecyclerView;
    private RecyclerAdapaterGalery mAdapter;
    private ArrayList<Profession> mProfessions;
    private String TAG = "FragmentProfession";
    private FirebaseFirestore mDataBase;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_profession_list,container,false);

        /*
        mProfessions = new ArrayList<>();

        mRecyclerView = result.findViewById(R.id.reclyclerview);

        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new RecyclerAdapaterGalery(mProfessions);
        mRecyclerView.setAdapter(mAdapter);
        // Access a Cloud Firestore instance from your Activity

        mDataBase = FirebaseFirestore.getInstance();

        mDataBase.collection("profession").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot documentSnapshot : task.getResult()) {
                        Profession profession = documentSnapshot.toObject(Profession.class);
                        mProfessions.add(profession);
                        mAdapter.notifyDataSetChanged();
                        Log.d(TAG, documentSnapshot.getId() + " => " + documentSnapshot.getData());
                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        });

        Toast.makeText(getActivity().getApplicationContext(),"Hello " + ((HomeDrawerActivity)getActivity()).getUser().getDisplayName(),Toast.LENGTH_LONG).show();
        */
        return  result;
    }


    @Override
    public void onClick(View view) {

    }

}