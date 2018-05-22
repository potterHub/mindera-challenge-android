package com.example.potter_desktop.meetmindera.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.potter_desktop.meetmindera.R;
import com.example.potter_desktop.meetmindera.ui.fragment.Events;
import com.example.potter_desktop.meetmindera.ui.fragment.Vacancies;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // set top bar
        Toolbar toolbar = findViewById(R.id.toolbar_menu);
        setSupportActionBar(toolbar);

        // get mDrawer
        mDrawer = findViewById(R.id.drawer_layout);

        // get views (listener to the menu buttons)
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        View navHeader = navigationView.getHeaderView(0);
        // Navigation view header
        TextView navEditTextName = navHeader.findViewById(R.id.nav_name_text_view);
        TextView navEditTextEmail = navHeader.findViewById(R.id.nav_email_text_view);

        // customize header
        navEditTextName.setText(getResources().getString(R.string.dummy_user_name));
        navEditTextEmail.setText(getResources().getString(R.string.dummy_user_email));

        // make the button on top
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        // deactivate toggle animation
        toggle.setDrawerSlideAnimationEnabled(false);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        // create tab adapter
        String tabEvents = getResources().getString(R.string.tab_events);
        String tabVacancies = getResources().getString(R.string.tab_vacancies);

        TabListAdapter tab_adapter = new TabListAdapter(getSupportFragmentManager());
        tab_adapter.addFragment(new Events(), tabEvents != null ? tabEvents : "1");
        tab_adapter.addFragment(new Vacancies(), tabVacancies != null ? tabVacancies : "2");

        // get viewPage adapter and set the tab_adapter
        ViewPager vPage = findViewById(R.id.view_pager);
        vPage.setAdapter(tab_adapter);

        // add view pager with the fragment adapter to the tablayout
        TabLayout tabLayout = findViewById(R.id.event_tab_layout);
        tabLayout.setupWithViewPager(vPage);
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the search magnifying glass on the top of the home menu
        getMenuInflater().inflate(R.menu.search_magnifying_glass_button, menu);
        return true;
    }

    // top right button option selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    // nav menu buttons actions
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        mDrawer.closeDrawers();
        return false;
    }

    // adapter to show the list tabs
    public static class TabListAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mTabFrags;
        private final List<String> mTabTitles;

        public TabListAdapter(FragmentManager fm) {
            super(fm);
            mTabFrags = new ArrayList<>();
            mTabTitles = new ArrayList<>();
        }

        public void addFragment(Fragment frag, String title) {
            if (frag != null && title != null) {
                mTabFrags.add(frag);
                mTabTitles.add(title);
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabTitles.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            // return fragment
            return mTabFrags.get(position);
        }

        @Override
        public int getCount() {
            return mTabFrags.size();
        }
    }
}
