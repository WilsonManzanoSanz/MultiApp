package manzano.wilson.multiapp.Recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import manzano.wilson.multiapp.Objects.Gallery;
import manzano.wilson.multiapp.R;

/**
 * Created by User on 23/01/2018.
 */

public class RecyclerAdapaterGalery extends RecyclerView.Adapter<RecyclerAdapaterGalery.RecyclerViewHolder> {

    private ArrayList<Gallery> mArrayList = new ArrayList<>();
    private Context context;

    public RecyclerAdapaterGalery(ArrayList<Gallery> mArrayList) {
        this.mArrayList = mArrayList;
    }


    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_row, parent, false);
        context = parent.getContext();
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        Gallery mGallery = mArrayList.get(position);
        Picasso.with(context).load(mGallery.getPhotoUrl()).into(holder.mGalleryPhoto);
        holder.mLectureDate.setText(mGallery.getDate());
        holder.mLectureTime.setText(mGallery.getTime());
    }


    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView mLectureTime, mLectureDate;
        private ImageView mGalleryPhoto;

        public RecyclerViewHolder(View view) {

            super(view);
            mLectureTime = (TextView) view.findViewById(R.id.textview_lecture_time);
            mLectureDate = (TextView) view.findViewById(R.id.textview_lecture_date);
            mGalleryPhoto = (ImageView) view.findViewById(R.id.imageview_lecture);

        }
    }
}
