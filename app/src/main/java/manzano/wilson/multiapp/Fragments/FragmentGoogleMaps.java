package manzano.wilson.multiapp.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

import manzano.wilson.multiapp.Objects.Gallery;
import manzano.wilson.multiapp.Objects.Location;
import manzano.wilson.multiapp.R;

public class FragmentGoogleMaps extends Fragment implements OnMapReadyCallback {

    private SupportMapFragment mapFragment;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final String TAG = "MAP_FRAGMENT";
    private ArrayList<Location> arrayList = new ArrayList<Location>();
    private GoogleMap mMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_maps, container, false);

        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            mapFragment.getMapAsync(this);

            // R.id.map is a FrameLayout, not a Fragment
            getChildFragmentManager().beginTransaction().replace(R.id.map, mapFragment).commit();

        }

        db.collection("locations").orderBy("date", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
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
                            if(mMap != null){
                                Location element = dc.getDocument().toObject(Location.class);
                                mMap.addMarker(new MarkerOptions().position(new LatLng(element.getLatitude(), element.getLongitude())).title(String.valueOf(element.getTime())));
                            }
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
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        getPositions(googleMap);
        /*LatLng latLng = new LatLng(1.289545, 103.849972);
        googleMap.addMarker(new MarkerOptions().position(latLng)
                .title("Singapore"));
                */
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(11.00449191, -74.79175851), 11));

    }

    public void getPositions(final GoogleMap googleMap){
        db.collection("locations").orderBy("time").limit(20)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Log.i("Results", String.valueOf(task));
                        if (task.isSuccessful()) {
                            Location element = null;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                element = document.toObject(Location.class);
                                googleMap.addMarker(new MarkerOptions().position(new LatLng(element.getLatitude(), element.getLongitude())).title(String.valueOf(element.getTime())));
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

}
