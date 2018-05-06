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
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // set top bar
        Toolbar toolbar = findViewById(R.id.toolbar_menu);
        setSupportActionBar(toolbar);

        // get drawer
        drawer = findViewById(R.id.drawer_layout);

        // get views (listener to the menu buttons)
        NavigationView navigation_view = findViewById(R.id.nav_view);
        navigation_view.setNavigationItemSelectedListener(this);


        View nav_header = navigation_view.getHeaderView(0);
        // Navigation view header
        TextView nav_edit_text_name = nav_header.findViewById(R.id.nav_name_text_view);
        TextView nav_edit_text_email = nav_header.findViewById(R.id.nav_email_text_view);

        // customize header
        nav_edit_text_name.setText(getResources().getString(R.string.dummy_user_name));
        nav_edit_text_email.setText(getResources().getString(R.string.dummy_user_email));

        // make the button on top
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        // deactivate toggle animation
        toggle.setDrawerSlideAnimationEnabled(false);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // create tab adapter
        String tab_events = getResources().getString(R.string.tab_events);
        String tab_vacancies = getResources().getString(R.string.tab_vacancies);

        TabListAdapter tab_adapter = new TabListAdapter(getSupportFragmentManager());
        tab_adapter.addFragment(new Events(), tab_events != null ? tab_events : "tab 1");
        tab_adapter.addFragment(new Vacancies(), tab_vacancies != null ? tab_vacancies : "tab 2");

        // get viewPage adapter and set the tab_adapter
        ViewPager v_page = findViewById(R.id.view_pager);
        v_page.setAdapter(tab_adapter);

        // add view pager with the fragment adapter to the tablayout
        TabLayout tabLayout = findViewById(R.id.event_tab_layout);
        tabLayout.setupWithViewPager(v_page);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
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
        drawer.closeDrawers();
        return false;
    }

    // adapter to show the list tabs
    public static class TabListAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> tab_frags = new ArrayList<>();
        private final List<String> tab_titles = new ArrayList<>();

        public TabListAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment frag, String title) {
            if (frag != null && title != null) {
                tab_frags.add(frag);
                tab_titles.add(title);
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tab_titles.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            // return fragment
            return tab_frags.get(position);
        }

        @Override
        public int getCount() {
            return tab_frags.size();
        }
    }
}
