package manzano.wilson.multiapp.Recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import manzano.wilson.multiapp.Objects.Profession;
import manzano.wilson.multiapp.R;

/**
 * Created by User on 23/01/2018.
 */

public class RecyclerAdapater extends RecyclerView.Adapter<RecyclerAdapater.RecyclerViewHolder> {

    private ArrayList<Profession> mArrayList = new ArrayList<>();
    private Context context;

    public RecyclerAdapater(ArrayList<Profession> mArrayList) {
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
        Profession mProfession = mArrayList.get(position);
        Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(holder.mProfessionImage);
        holder.mProfessionName.setText(mProfession.getProfessional_name());
        holder.mProfessionDescription.setText(mProfession.getProfessional_description());

    }


    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView mProfessionName, mProfessionDescription;
        private ImageView mProfessionImage;

        public RecyclerViewHolder(View view) {

            super(view);
            mProfessionDescription = (TextView) view.findViewById(R.id.textview_profession_description);
            mProfessionName = (TextView) view.findViewById(R.id.textview_profession_name);
            mProfessionImage = (ImageView) view.findViewById(R.id.imageview_profession_image);

        }
    }
}
