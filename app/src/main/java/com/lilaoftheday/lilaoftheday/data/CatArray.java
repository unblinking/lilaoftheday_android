package com.lilaoftheday.lilaoftheday.data;

import com.lilaoftheday.lilaoftheday.models.Cat;

import java.util.ArrayList;
import java.util.Collections;

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
    public ArrayList<Cat> catArray() {

        ArrayList<Cat> catArrayList;
        catArrayList = new ArrayList<>();

        // For each cat photo name in our cat photo name array
        for (String catPhotoName : catPhotoNameArray) {
            Cat cat;
            cat = new Cat();
            cat.setPhotoName(catPhotoName);
            /*cat.photoName = catPhotoName;*/
            catArrayList.add(cat);
        }

        Collections.shuffle(catArrayList);

        return (catArrayList);

    }

}

