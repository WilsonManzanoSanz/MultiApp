package manzano.wilson.multiapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import manzano.wilson.multiapp.R;

/**
 * Created by User on 29/11/2017.
 */

public class FragmentAdminMain extends Fragment implements View.OnClickListener {
    @Nullable


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_admin_main,container,false);
        return  result;
    }


    @Override
    public void onClick(View view) {

    }

}