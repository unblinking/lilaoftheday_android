package com.lilaoftheday.lilaoftheday.utilities;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.lilaoftheday.lilaoftheday.R;
import com.lilaoftheday.lilaoftheday.models.BackStack;

import java.util.ArrayList;

public class Utilities {

    public static int getDrawableResourceId(Context context, String imageName) {
        return context.getResources().getIdentifier(
                imageName,
                "drawable",
                context.getPackageName()
        );
    }

    // TODO: Commented the old way for now. There is no longer an easy sense of which fragment
    // happens to be the "active" fragment, since different layouts for different devices might
    // have multiple fragments on the screen at one time. A fragment will still be active, but
    // that's not a good thing to use to set the action bar title. Maybe revisit this later.
    /*public static Fragment getActiveFragment(FragmentManager fm) {
        int backStackCount;
        backStackCount = fm.getBackStackEntryCount();
        if (backStackCount == 0) {
            return null;
        } else {
            String tag;
            tag = fm.getBackStackEntryAt(backStackCount - 1).getName();
            Fragment fragment;
            fragment = fm.findFragmentByTag(tag);
            return fragment;
        }
    }*/

    public static void replaceFragmentInContainer(int containerViewId, FragmentManager fm, Fragment fragment, String tagName) {

        if (fm.findFragmentByTag(tagName) != null) {
            // If a fragment with the same tag is already in the fragment manager, just resurface it.
            resurfaceFragmentInBackStack(fm, tagName);
        } else {
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(
                    containerViewId,
                    fragment,
                    tagName // Tag name, to later retrieve the fragment with.
            );
            fragmentTransaction.addToBackStack(tagName);
            fragmentTransaction.commit();
        }

        fm.executePendingTransactions();

    }

    public static void resurfaceFragmentInBackStack(FragmentManager fm, String tagName) {

        int backStackEntryCount = fm.getBackStackEntryCount();

        // Create our own ArrayList of the current back stack entries
        ArrayList<BackStack> backStackArrayList = new ArrayList<>();
        // Populate our own ArrayList of back stack entries
        for (int entry = 0; entry < backStackEntryCount; entry++) {

            BackStack backStack;
            backStack = new BackStack();

            String tag = fm.getBackStackEntryAt(entry).getName();
            Fragment frag = fm.findFragmentByTag(tag);

            // TODO: Do this a better way
            // For now we just have two containers, so its simple enough to just handle like this
            // right now, but maybe the container name could be part of a future fragment tag where
            // we have multiple pieces of info, delimited by pipes or something.
            int contViewId;
            if (tag.equals("Lila of the day")) {
                contViewId = R.id.mainContainer;
            } else {
                contViewId = R.id.photoContainer;
            }

            backStack.setTagName(tag);
            backStack.setFragment(frag);
            backStack.setContainerViewId(contViewId);

            backStackArrayList.add(backStack);

        }

        int backStackArrayListSize = backStackArrayList.size();

        // Clear the fragment manager back stack completely
        if (fm.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry firstEntry = fm.getBackStackEntryAt(0);
            fm.popBackStackImmediate(
                    firstEntry.getId(),
                    FragmentManager.POP_BACK_STACK_INCLUSIVE
            );
        }

        // Remove all fragments from the fragment manager
        for (int entry = 0; entry < backStackArrayListSize; entry++) {
            BackStack backStack;
            backStack = backStackArrayList.get(entry);

            Fragment entryFragment = backStack.getFragment();

            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.remove(entryFragment);
            fragmentTransaction.commit();
        }

        // Add fragments back, starting with anything that is not the desired fragment
        for (int entry = 0; entry < backStackArrayListSize; entry++) {
            BackStack backStack;
            backStack = backStackArrayList.get(entry);

            int entryContainerViewId = backStack.getContainerViewId();
            Fragment entryFragment = backStack.getFragment();
            String entryTagName = backStack.getTagName();

            if (!entryTagName.equals(tagName)) {
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.add(
                        entryContainerViewId,
                        entryFragment,
                        entryTagName
                );
                fragmentTransaction.addToBackStack(entryTagName);
                fragmentTransaction.commit();
            }
        }

        // Now add the desired fragment back, so that it is on top of the back stack
        for (int entry = 0; entry < backStackArrayListSize; entry++) {
            BackStack backStack;
            backStack = backStackArrayList.get(entry);

            int entryContainerViewId = backStack.getContainerViewId();
            Fragment entryFragment = backStack.getFragment();
            String entryTagName = backStack.getTagName();

            if (entryTagName.equals(tagName)) {
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.add(
                        entryContainerViewId,
                        entryFragment,
                        entryTagName
                );
                fragmentTransaction.addToBackStack(entryTagName);
                fragmentTransaction.commit();
            }
        }

    }

}

