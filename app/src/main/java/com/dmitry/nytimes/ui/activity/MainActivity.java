package com.dmitry.nytimes.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import com.dmitry.nytimes.R;
import com.dmitry.nytimes.ui.adapters.ViewPagerAdapter;
import com.dmitry.nytimes.ui.fragments.FragmentListEmailed;
import com.dmitry.nytimes.ui.fragments.FragmentListShared;
import com.dmitry.nytimes.ui.fragments.FragmentListViewed;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        Fragment Emailed = new FragmentListEmailed();
        adapter.addFragment(Emailed, "Most Emailed");

        Fragment Shared = new FragmentListShared();
        adapter.addFragment(Shared, "Most Shared");

        Fragment Viewed = new FragmentListViewed();
        adapter.addFragment(Viewed, "Most Viewed");

//        adapter.addFragment(Saved, "Saved");
        viewPager.setAdapter(adapter);
    }



}
