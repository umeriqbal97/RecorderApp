package com.example.recorder.adapter;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.recorder.fragments.ListFragment;
import com.example.recorder.fragments.RecordFragment;

/**
 * The type Pager adapter.
 */
public class PagerAdapter extends FragmentPagerAdapter {
    /**
     * The Numoftabs.
     */
    int numoftabs;

    /**
     * Instantiates a new Pager adapter.
     *
     * @param fm        the fm
     * @param behavior  the behavior
     * @param numoftabs the numoftabs
     */
    public PagerAdapter(@NonNull FragmentManager fm, int behavior, int numoftabs) {
        super(fm,behavior);
        this.numoftabs=numoftabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new RecordFragment();
            case 1:
                return new ListFragment();
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Recording";
            case 1:
                return "List";
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numoftabs;
    }
}
