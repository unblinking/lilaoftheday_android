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

    Context mContext;
    public CatListAdapter(Context context) {
        this.mContext = context;
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

    /* How many items are in our array? */
    @Override
    public int getItemCount () {

        /* For this example we get the count of the cat list. */
        return new CatArray().catArray().size();

    }

    /* Instantiate our special ViewHolder and pass it an inflated view. */
    @Override
    public CatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        /* For this example we pass the cv view (our CardView layout xml filename is cv.xml) */
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv, parent, false);
        return new CatViewHolder(view);

    }

    /* Display an item in the correct position. */
    @Override
    public void onBindViewHolder(final CatViewHolder holder, final int position) {

        /* For this example we put the cat name into position in the list */
        final Cat cat = new CatArray().catArray().get(position);
        final int imageResourceID = cat.getImageResourceId(mContext);

        // holder.catPhoto.setImageDrawable(mContext.getDrawable(imageResourceID));

        if (imageResourceID != 0) {
            Picasso.with(mContext)
                    .load(imageResourceID)
                    .resize(100,100)
                    .centerInside()
                    .into(holder.catPhoto);
        }

        holder.catPhoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext,PhotoActivity.class);
                intent.putExtra("image_resource_id", imageResourceID);
                mContext.startActivity(intent);

                /*Toast.makeText(
                        mContext,
                        "You clicked the photo.",
                        Toast.LENGTH_SHORT
                ).show();*/

            }

        });

        setAnimation(holder.cv, position);
    }

    private void setAnimation(View viewToAnimate, int position) {

        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }

    }

}

