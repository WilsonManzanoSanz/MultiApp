package manzano.wilson.multiapp.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import manzano.wilson.multiapp.R;

public class FragmentGraph extends Fragment implements View.OnClickListener {

    private static final String TAG = "GraphFragment" ;
    private LineChart lineChart;
    LineDataSet lineDataSet;
    LineData lineData;
    private ArrayList signal;
    private DatabaseReference mDatabase;
    List<Entry> entries = new ArrayList<Entry>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_graph, container, false);
        mDatabase = FirebaseDatabase.getInstance().getReference("graph");
        initializeChart(result);
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                //signal = dataSnapshot.getValue(ArrayList.class);
                entries = new ArrayList<Entry>();
                int i = 0;
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                        if(postSnapshot.getValue() != null){
                            entries.add(new Entry (i,  postSnapshot.getValue(Float.class)));
                        }
                       // signal.add(new Entry(i, postSnapshot.getValue(Float.class)));
                        i++;
                }
                refreshGraph();
                Log.i("entries", String.valueOf(entries));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        mDatabase.addValueEventListener(postListener);
        return result;
    }

    private void initializeChart( View result) {
        lineChart = (LineChart) result.findViewById(R.id.graph);
    }

    private void refreshGraph(){
        lineDataSet = new LineDataSet(entries,"V");
        lineDataSet.setDrawCircles(false);
        lineDataSet.setCircleColor(Color.CYAN);
        lineDataSet.setDrawValues(true);

        lineData = new LineData();
        lineData.addDataSet(lineDataSet);
        if (entries.isEmpty()) {
            lineChart.clear();
        } else {
            // set data
            lineChart.setData(lineData);
        }
        lineChart.invalidate(); // refresh
    }

    @Override
    public void onClick(View view) {

    }
}
