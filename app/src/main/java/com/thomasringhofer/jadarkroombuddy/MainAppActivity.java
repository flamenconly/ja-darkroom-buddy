package com.thomasringhofer.jadarkroombuddy;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.thomasringhofer.jadarkroombuddy.adapter.MainAppPagerAdapter;
import com.thomasringhofer.jadarkroombuddy.database.AppDatabase;
import com.thomasringhofer.jadarkroombuddy.entities.Film;
import com.thomasringhofer.jadarkroombuddy.entities.Fluid;
import com.thomasringhofer.jadarkroombuddy.model.DevelopmentProcessAndItsActivities;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainAppActivity extends AppCompatActivity implements
        RecentDevelopmentProcessesFragment.OnListFragmentInteractionListener,
        WorkingSolutionsFragment.OnFragmentInteractionListener,
        FilmFragment.OnFragmentInteractionListener,
        FluidFragment.OnListFragmentInteractionListener{

    private MainAppPagerAdapter mainAppPagerAdapter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.main_pager)
    ViewPager myViewPager;

    @BindView(R.id.main_tab_layout)
    TabLayout myTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppDatabase.CreateInstance(getApplicationContext());

        setContentView(R.layout.activity_main_app);

        // Initialize Binding
        ButterKnife.bind(this);

        // Initialize AppPagerAdapter
        mainAppPagerAdapter = new MainAppPagerAdapter(getSupportFragmentManager());
        myViewPager.setAdapter(mainAppPagerAdapter);

        // Connect TabLayout with Viewpager
        myTabLayout.setupWithViewPager(myViewPager);

        // Setting Tab Icons
        myTabLayout.getTabAt(0).setIcon(R.drawable.ic_content_book_2_selected);
        myTabLayout.getTabAt(1).setIcon(R.drawable.ic_beaker_science);
        myTabLayout.getTabAt(2).setIcon(R.drawable.ic_receipt_1);

        myTabLayout.addOnTabSelectedListener(new MyOnTabSelectedListener());

        // Setup toolbar
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu_resources,menu);

        return true;
    }

    @Override
    public void onFragmentInteraction(Film film) {

    }

    @Override
    public void onFragmentInteraction(DevelopmentProcessAndItsActivities developmentProcessAndItsActivities) {

    }

    @Override
    public void onFragmentInteraction(Uri uri){

    }

    @Override
    public void onListFragmentInteraction(Fluid item, boolean longClick) {

        if(longClick){


        }

    }

    class MyOnTabSelectedListener implements TabLayout.OnTabSelectedListener{

        @Override
        public void onTabSelected(TabLayout.Tab tab) {

            // Change Color
            switch (tab.getPosition()){
                case 0:
                    tab.setIcon(R.drawable.ic_content_book_2_selected);
                    break;
                case 1:
                    tab.setIcon(R.drawable.ic_beaker_science_selected);
                    break;
                case 2:
                    tab.setIcon(R.drawable.ic_receipt_1_selected);
                default:
            }

        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
            // Change Color
            switch (tab.getPosition()){
                case 0:
                    tab.setIcon(R.drawable.ic_content_book_2);
                    break;
                case 1:
                    tab.setIcon(R.drawable.ic_beaker_science);
                    break;
                case 2:
                    tab.setIcon(R.drawable.ic_receipt_1);
                    break;
                default:
            }
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    }
}
