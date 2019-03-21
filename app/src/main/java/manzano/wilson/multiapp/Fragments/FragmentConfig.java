package manzano.wilson.multiapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import manzano.wilson.multiapp.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import manzano.wilson.multiapp.Objects.Profession;
import manzano.wilson.multiapp.Recycler.RecyclerAdapaterGalery;
/*
 * Created by User on 29/11/2017.
 */

public class FragmentConfig extends Fragment {
    @Nullable


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_config,container,false);
        return  result;
    }



}