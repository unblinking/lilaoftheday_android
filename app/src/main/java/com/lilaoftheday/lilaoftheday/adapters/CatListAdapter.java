package com.lilaoftheday.lilaoftheday.adapters;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.lilaoftheday.lilaoftheday.R;
import com.lilaoftheday.lilaoftheday.data.CatArray;
import com.lilaoftheday.lilaoftheday.fragments.PhotoFragment;
import com.lilaoftheday.lilaoftheday.models.Cat;
import com.lilaoftheday.lilaoftheday.utilities.Utilities;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CatListAdapter extends RecyclerView.Adapter<CatListAdapter.CatViewHolder> {

    Context context;
    ArrayList<Cat> catArrayList = new CatArray().catArray();
    ViewGroup parent;
    FragmentManager fragmentManager;

    public CatListAdapter(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    private int lastPosition = -1;

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
        /*return new CatArray().catArray().size();*/
        return catArrayList.size();
    }

    @Override
    public CatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.parent = parent;

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv, parent, false);
        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CatViewHolder holder, final int position) {

        /*final Cat cat = new CatArray().catArray().get(position);*/
        final Cat cat = catArrayList.get(position);

        String imageName = cat.getPhotoName();

        /*final int imageResourceID = cat.getImageResourceId(context);*/
        final int imageResourceID = Utilities.getDrawableResourceId(context, imageName);

        if (imageResourceID != 0) {
            Picasso.with(context)
                    .load(imageResourceID)
                    .resize(150,150)
                    .centerInside()
                    .into(holder.catPhoto);
        }

        holder.catPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Start the Photo Activity
                /*Intent intent = new Intent(context,PhotoActivity.class);
                intent.putExtra("image_resource_id", imageResourceID);
                context.startActivity(intent);*/

                // Start the Photo Fragment
                Utilities.replaceFragmentInContainer(
                        R.id.mainContainer,
                        fragmentManager,
                        PhotoFragment.newInstance(imageResourceID),
                        "Photo"
                );

            }
        });

        /*setAnimation(holder.cv, position);*/

    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

}

