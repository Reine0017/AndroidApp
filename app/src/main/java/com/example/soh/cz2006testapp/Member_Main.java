/**
 * Main menu for registered users.
 * Provides navigations to any function either through buttons
 * or through navigation drawer.
 */

package com.example.soh.cz2006testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Member_Main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private FirebaseAuth mAuth;

    /**
     * Creates the view using the activity_member__main xml.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member__main);

        mAuth = FirebaseAuth.getInstance();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_member_main);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_member_main);
        View hView =  navigationView.getHeaderView(0);
        TextView nav_user = (TextView)hView.findViewById(R.id.nav_view_display_name);
        nav_user.setText(mAuth.getCurrentUser().getDisplayName());
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * Closes navigation drawer on pressing back.
     */

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_member_main);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent intent = new Intent(this, Guest_Main.class);
            startActivity(intent);
            finish();
        }
    }

    /**
     * Menu for navigation drawer.
     * @param item
     * @return
     */

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.district_map) {
            Intent intent = new Intent(this, District_Map.class);
            startActivity(intent);
        }

        if (id == R.id.calculator) {
            Intent intent = new Intent(this, Calculator_Form.class);
            startActivity(intent);
        }
        else if (id == R.id.messages) {
            Intent intent = new Intent(this, View_Chat.class);
            startActivity(intent);
        }

        else if (id == R.id.property_list) {
            Intent intent = new Intent(this, Post_Listing_Form.class);
            startActivity(intent);
        }

        else if (id == R.id.listed_property) {
            Intent intent = new Intent(this, View_User_Post.class);
            intent.putExtra("district", "0");
            startActivity(intent);
        }

        /*
        else if (id == R.id.main_menu) {
            Intent intent = new Intent(this, Member_Main.class);
            startActivity(intent);
        }*/

        else if (id == R.id.sign_out){
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, Guest_Main.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_member_main);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Sends view to district map.
     * @param view
     */

    public void viewProperty(View view){
        Intent intent = new Intent(this, District_Map.class);
        startActivity(intent);
    }

    /**
     * Sends view to post listing.
     * @param view
     */

    public void listProperty(View view){
        Intent intent = new Intent(this, Post_Listing_Form.class);
        startActivity(intent);
    }

    /**
     * Sends view to calculator.
     * @param view
     */

    public void calculator(View view){
        Intent intent = new Intent(this, Calculator_Form.class);
        startActivity(intent);
    }

    /**
     * Sends view to chat.
     * @param view
     */

    public void message(View view){
        Intent intent = new Intent(this, View_Chat.class);
        startActivity(intent);
    }

}
