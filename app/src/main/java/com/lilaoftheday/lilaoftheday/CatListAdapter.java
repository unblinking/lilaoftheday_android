package com.lilaoftheday.lilaoftheday;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class CatListAdapter extends RecyclerView.Adapter<CatListAdapter.CatViewHolder> {

    Context context;
    public CatListAdapter(Context context) {
        this.context = context;
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

    /*
    We use the ViewHolder subclass to override some methods for our specific data.
    There are three methods that we MUST implement:
        - onCreateViewHolder - Creates the ViewHolder instance
        - onBindViewHolder - Displays the items in correct positions in the list
        - getItemCount - Gets the count of items
    */

    @Override
    public int getItemCount () {
        return new CatArray().catArray().size();
    }

    @Override
    public CatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv, parent, false);
        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CatViewHolder holder, final int position) {

        final Cat cat = new CatArray().catArray().get(position);
        final int imageResourceID = cat.getImageResourceId(context);

        if (imageResourceID != 0) {
            Picasso.with(context)
                    .load(imageResourceID)
                    .resize(100,100)
                    .centerInside()
                    .into(holder.catPhoto);
        }

        holder.catPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,PhotoActivity.class);
                intent.putExtra("image_resource_id", imageResourceID);
                context.startActivity(intent);
            }
        });

        setAnimation(holder.cv, position);
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

}

