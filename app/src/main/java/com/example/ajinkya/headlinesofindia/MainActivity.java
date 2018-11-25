package com.example.ajinkya.headlinesofindia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.io.Serializable;

import adapters.CustomAdapter;
import adapters.SectionsPageAdapter;
import fragments.Business_tab_fragment;
import fragments.Entertainment_tab_fragment;
import fragments.Health_tab_fragment;
import fragments.Sports_tab_fragment;
import fragments.Technology_tab_fragment;
import fragments.Top_stories_tab_fragment;

public class MainActivity extends AppCompatActivity {

    //private ListView mArticlesListView;
    private SectionsPageAdapter mSectionPageAdapter;
    private ViewPager mViewPager;
    public static final String SEARCH_QUERY = "com.example.ajinkya.headlinesofindia.SEARCH_QUERY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mArticlesListView = findViewById(R.id.articles);
        mSectionPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    public void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Top_stories_tab_fragment(), "Top Stories");
        adapter.addFragment(new Business_tab_fragment(), "Business");
        adapter.addFragment(new Entertainment_tab_fragment(), "Entertainment");
        adapter.addFragment(new Sports_tab_fragment(), "Sports");
        adapter.addFragment(new Technology_tab_fragment(), "Technology");
        adapter.addFragment(new Health_tab_fragment(), "Health");
        viewPager.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tabbed, menu);
        MenuItem item = menu.findItem(R.id.articleSearch);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Intent intent = new Intent(MainActivity.this, SearchResultActivity.class);
                intent.putExtra(SEARCH_QUERY, s);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this,
                    SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


/*

Features to Include-

1. Search button in the app bar - Done
2. Settings -
Notifications : on/off, freqency, text size, theme, rating & reviwe, Share App.
3. Offline Message - Done
4. Global News Tab


V2

* News articles classification using ML
* Improve Code and remove hacks
* Implement coding process from Google tutorials

1. Download Articles Offline - on/off, frequence
2. Location news
3. Customize Home screen
4. Customize notifications
5. Widget
6. Quote of the Day
7. Online sync frequency
8. Landscapr/Tablet Variant
9. Notch Support
10. Async Task
11. Refresh
12. Loading animation

V3

*/