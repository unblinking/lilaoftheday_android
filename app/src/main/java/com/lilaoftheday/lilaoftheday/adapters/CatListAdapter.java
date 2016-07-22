package com.lilaoftheday.lilaoftheday.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lilaoftheday.lilaoftheday.R;
import com.lilaoftheday.lilaoftheday.activities.MainActivity;
import com.lilaoftheday.lilaoftheday.data.CatArray;
import com.lilaoftheday.lilaoftheday.fragments.PhotoFragment;
import com.lilaoftheday.lilaoftheday.models.Cat;
import com.lilaoftheday.lilaoftheday.utilities.FragmentBoss;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CatListAdapter extends RecyclerView.Adapter<CatListAdapter.CatViewHolder> {

    Context context;
    ArrayList<Cat> catArrayList;

    public CatListAdapter(Context context) {

        this.context = context;
        catArrayList = new CatArray().getCatArray(context);

    }

    public class CatViewHolder extends RecyclerView.ViewHolder {

        public CardView cv;
        public ImageView catPhoto;

        public CatViewHolder(View itemView) {
            super(itemView);
            this.cv = (CardView) itemView.findViewById(R.id.cv);
            this.catPhoto = (ImageView) itemView.findViewById(R.id.catPhoto);
        }

    }

    @Override
    public int getItemCount () {

        return catArrayList.size();

    }

    @Override
    public CatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv, parent, false);
        return new CatViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final CatViewHolder holder, final int position) {

        final Cat cat = catArrayList.get(position);
        final String tagTitle = cat.getPhotoName();
        final int dbRecordId = cat.getDbRecordId(); // Also the image resource ID.

        if (dbRecordId != 0) {
            Picasso.with(context)
                    .load(dbRecordId)
                    .resize(200,200)
                    .centerInside()
                    .into(holder.catPhoto);
        }

        holder.catPhoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                AppCompatActivity activity = (MainActivity) context;
                FragmentManager fm = activity.getSupportFragmentManager();
                int containerViewId = R.id.photoContainer;
                Fragment fragment = PhotoFragment.newInstance(dbRecordId, tagTitle);
                String tagCombo = FragmentBoss.tagJoiner(tagTitle, containerViewId, dbRecordId);

                FragmentBoss.replaceFragmentInContainer(
                        containerViewId,
                        fm,
                        fragment,
                        tagCombo
                );

            }

        });

    }

}

