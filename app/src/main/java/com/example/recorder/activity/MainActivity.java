package com.example.recorder.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TableLayout;

import com.example.recorder.R;
import com.example.recorder.adapter.PagerAdapter;
import com.example.recorder.fragments.ListFragment;
import com.example.recorder.fragments.RecordFragment;
import com.google.android.material.tabs.TabLayout;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity {
    /**
     * The Tab layout.
     */
    TabLayout tabLayout;
    /**
     * The View pager.
     */
    ViewPager viewPager;
    /**
     * The Toolbar.
     */
    Toolbar toolbar;
    /**
     * The Pager adapter.
     */
    PagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setTextOnTabLayout();
    }

    /**
     * Init views.
     */
//This method is used to initialize views in main activity
    public void initViews(){
        tabLayout=findViewById(R.id.tablayout);
        toolbar=findViewById(R.id.toolbar);
        viewPager=findViewById(R.id.viewpager);
    }

    /**
     * Set text on tab layout.
     */
    public void setTextOnTabLayout(){
        pagerAdapter=new PagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
