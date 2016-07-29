package com.lilaoftheday.lilaoftheday.data;

import android.content.Context;

import com.lilaoftheday.lilaoftheday.models.Cat;
import com.lilaoftheday.lilaoftheday.utilities.Utilities;

import java.util.ArrayList;

// In this class we create an array of drawable photo file names
public class CatArray {

    // To get started with version 1 we use this static list of cat photo names
    static String[] catPhotoNameArray;
    static {
        catPhotoNameArray = new String[] {
                "lila_back_blue_curtains",
                "lila_basement_window",
                "lila_bright_dark_shadow",
                "lila_closeup_face_dark_blurry",
                "lila_close_up_on_knee",
                "lila_curled_covering_face",
                "lila_curl_on_blue_blanket",
                "lila_front_standing_maple_leaf_basement_carpet",
                "lila_hidden_in_comfort",
                "lila_in_cabinet",
                "lila_in_easter_basket",
                "lila_in_window_on_red_blanket",
                "lila_in_window_wide_open_eyes",
                "lila_laying_in_window",
                "lila_licking_nose",
                "lila_looking_over_shoulder_shadow",
                "lila_one_eye_through_blanket",
                "lila_on_back_leather_couch_open_mouth",
                "lila_on_bed_bright",
                "lila_sleeping_in_a_box",
                "lila_sleeping_on_couch_dark",
                "lila_standing_in_window_bright",
                "lila_standing_on_edge_of_shelf",
                "lila_under_leaf",
                "lila_under_leaf_looking_up",
                "lila_very_bright_by_window",
                "lila_very_close_up"
        };
    }

    // TODO: Include additional information about each photo.

    /*
    Now that we've got all of the different pieces of information about the cats ready, we
    create the array of cats and return it.
    */
    public ArrayList<Cat> getCatArray(Context context) {

        ArrayList<Cat> catArrayList;
        catArrayList = new ArrayList<>();

        // For each cat photo name in our cat photo name array
        for (String catPhotoName : catPhotoNameArray) {
            Cat cat;
            cat = new Cat();
            cat.setDbRecordId(Utilities.getDrawableResourceId(context, catPhotoName));
            cat.setPhotoName(catPhotoName);
            catArrayList.add(cat);
        }

        return catArrayList;

    }

    /**
     * Called to get the resource ID of the previous image in the cat array.
     *
     * From Stack Overflow
     * @see <a href="http://stackoverflow.com/a/30841413">how-click-next-button-and-back-button-in-array-index</a>
     *
     * @param context Context: Interface to global information about an application environment.
     * @param resourceId int: The current cat array image resource ID.
     *
     * @return The return value is the resource ID of the previous image in the array.
     */
    public int getPreviousResId(Context context, int resourceId) {

        // Get the Cat array list.
        ArrayList<Cat> catArrayList = new CatArray().getCatArray(context);

        // Get the index from the Cat array list for the supplied resourceId.
        int currentImageIndex = 0;
        for (int i = 0; i < catArrayList.size(); i++) {
            if (resourceId == catArrayList.get(i).getDbRecordId()) {
                currentImageIndex = i;
            }
        }

        // Get the previous index from the Cat array list, wrap around when reaching zero.
        // Neat use of modulus to loop through the array. The modulus returns the
        // remainder. If the current index is zero, this will return the remainder
        // as the full cat array list size, starting over at the end of the array.
        int previousIndex = (currentImageIndex + catArrayList.size() - 1) % catArrayList.size();

        // Get the previous resource ID using the previous index.
        return catArrayList.get(previousIndex).getDbRecordId();

    }

    /**
     * Called to get the resource ID of the next image in the cat array.
     *
     * From Stack Overflow
     * @see <a href="http://stackoverflow.com/a/30841413">how-click-next-button-and-back-button-in-array-index</a>
     *
     * @param context Context: Interface to global information about an application environment.
     * @param resourceId int: The current cat array image resource ID.
     *
     * @return The return value is the resource ID of the next image in the array.
     */
    public int getNextResId(Context context, int resourceId) {

        // Get the Cat array list.
        ArrayList<Cat> catArrayList = new CatArray().getCatArray(context);

        // Get the index from the Cat array list for the supplied resourceId.
        int currentImageIndex = 0;
        for (int i = 0; i < catArrayList.size(); i++) {
            if (resourceId == catArrayList.get(i).getDbRecordId()) {
                currentImageIndex = i;
            }
        }

        // Determine the next index, wrap around when reaching zero.
        // Neat use of modulus to loop through the array. The modulus returns the
        // remainder. If the current index equals the full size of the cat array
        // list, the remainder is zero, starting over at the beginning of the array.
        int nextIndex = (currentImageIndex + 1) % catArrayList.size();

        // Get the next resource ID using the next index.
        return catArrayList.get(nextIndex).getDbRecordId();

    }

    public String getPhotoName(Context context, int resourceId) {

        // Get the Cat array list.
        ArrayList<Cat> catArrayList = new CatArray().getCatArray(context);

        // Get the index from the Cat array list for the supplied resourceId.
        int currentImageIndex = 0;
        for (int i = 0; i < catArrayList.size(); i++) {
            if (resourceId == catArrayList.get(i).getDbRecordId()) {
                currentImageIndex = i;
            }
        }

        return catArrayList.get(currentImageIndex).getPhotoName();

    }

}

