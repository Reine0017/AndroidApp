/**
 * Activity that holds the Help_Guide, Private_Property_Guide and
 * Public_Property_Guide fragments.
 */

package com.example.soh.cz2006testapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class Guide_Control extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    /**
     * Creates the view using the activity_guide__control xml.
     * Uses an up button to return to the guest main page.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide__control);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * Adds fragments into view.
     * @param viewPager
     */

    private void setupViewPager(ViewPager viewPager) {
        viewPagerAdapter adapter = new viewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Help_Guide(), "Help Guide");
        adapter.addFragment(new Public_Property_Guide(), "Public Property");
        adapter.addFragment(new Private_Property_Guide(), "Private Property");
        viewPager.setAdapter(adapter);
    }

    /**
     * Adapter for viewing fragment.
     */

    class viewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        /**
         * Constructor for adapter.
         * @param manager
         */

        public viewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        /**
         * Get the fragment at the provided position.
         * @param position
         * @return view of fragment at position
         */

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        /**
         * Gets number of fragment.
         * @return number of fragment
         */

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        /**
         * Method to add fragment to view.
         * @param fragment
         * @param title
         */

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        /**
         * Gets title of fragment at each position.
         * @param position
         * @return title of fragment
         */

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    /**
     * When up button is clicked, return to previous open activity.
     * @param item
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}