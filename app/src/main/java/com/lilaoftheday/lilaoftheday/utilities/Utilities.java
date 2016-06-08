package com.lilaoftheday.lilaoftheday.utilities;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

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

    public static Fragment getActiveFragment(FragmentManager fm) {
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
    }

    public static void replaceFragmentInContainer(int containerViewId, AppCompatActivity aca, FragmentManager fm, Fragment fragment, String tagName) {

        if (fm.findFragmentByTag(tagName) != null) {
            // If the fragment is already in the fragment manager just resurface it
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
            int contViewId = R.id.mainContainer;

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

