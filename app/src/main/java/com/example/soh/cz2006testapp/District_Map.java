/**
 * Displays the Singapore district map.
 * Clickable district moves view to property listing.
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
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class District_Map extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener, View.OnLongClickListener {

    private String district;
    private Button district1, district2, district3, district4, district5, district6, district7, district8, district9;
    private Button district10, district11, district12, district13, district14, district15, district16, district17;
    private Button district18, district19, district20, district21, district22, district23, district24, district25;
    private Button district26, district27, district28;
    private TextView temp = null;
    private TextView district1text, district2text, district3text, district4text, district5text, district6text;
    private TextView district7text, district8text, district9text, district10text, district11text;
    private TextView district12text, district13text, district14text, district15text, district16text, district17text;
    private TextView district18text, district19text, district20text, district21text, district22text, district23text;
    private TextView district24text, district25text, district26text, district27text, district28text;
    private FirebaseAuth mAuth;

    /**
     * Creates the view using the activity_district__map xml.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district__map);
        district1 = (Button) findViewById(R.id.button_district_1);
        district2 = (Button) findViewById(R.id.button_district_2);
        district3 = (Button) findViewById(R.id.button_district_3);
        district4 = (Button) findViewById(R.id.button_district_4);
        district5 = (Button) findViewById(R.id.button_district_5);
        district6 = (Button) findViewById(R.id.button_district_6);
        district7 = (Button) findViewById(R.id.button_district_7);
        district8 = (Button) findViewById(R.id.button_district_8);
        district9 = (Button) findViewById(R.id.button_district_9);
        district10 = (Button) findViewById(R.id.button_district_10);
        district11 = (Button) findViewById(R.id.button_district_11);
        district12 = (Button) findViewById(R.id.button_district_12);
        district13 = (Button) findViewById(R.id.button_district_13);
        district14 = (Button) findViewById(R.id.button_district_14);
        district15 = (Button) findViewById(R.id.button_district_15);
        district16 = (Button) findViewById(R.id.button_district_16);
        district17 = (Button) findViewById(R.id.button_district_17);
        district18 = (Button) findViewById(R.id.button_district_18);
        district19 = (Button) findViewById(R.id.button_district_19);
        district20 = (Button) findViewById(R.id.button_district_20);
        district21 = (Button) findViewById(R.id.button_district_21);
        district22 = (Button) findViewById(R.id.button_district_22);
        district23 = (Button) findViewById(R.id.button_district_23);
        district24 = (Button) findViewById(R.id.button_district_24);
        district25 = (Button) findViewById(R.id.button_district_25);
        district26 = (Button) findViewById(R.id.button_district_26);
        district27 = (Button) findViewById(R.id.button_district_27);
        district28 = (Button) findViewById(R.id.button_district_28);
        district1.setOnLongClickListener(this);
        district2.setOnLongClickListener(this);
        district3.setOnLongClickListener(this);
        district4.setOnLongClickListener(this);
        district5.setOnLongClickListener(this);
        district6.setOnLongClickListener(this);
        district7.setOnLongClickListener(this);
        district8.setOnLongClickListener(this);
        district9.setOnLongClickListener(this);
        district10.setOnLongClickListener(this);
        district11.setOnLongClickListener(this);
        district12.setOnLongClickListener(this);
        district13.setOnLongClickListener(this);
        district14.setOnLongClickListener(this);
        district15.setOnLongClickListener(this);
        district16.setOnLongClickListener(this);
        district17.setOnLongClickListener(this);
        district18.setOnLongClickListener(this);
        district19.setOnLongClickListener(this);
        district20.setOnLongClickListener(this);
        district21.setOnLongClickListener(this);
        district22.setOnLongClickListener(this);
        district23.setOnLongClickListener(this);
        district24.setOnLongClickListener(this);
        district25.setOnLongClickListener(this);
        district26.setOnLongClickListener(this);
        district27.setOnLongClickListener(this);
        district28.setOnLongClickListener(this);

        district1.setOnClickListener(this);
        district2.setOnClickListener(this);
        district3.setOnClickListener(this);
        district4.setOnClickListener(this);
        district5.setOnClickListener(this);
        district6.setOnClickListener(this);
        district7.setOnClickListener(this);
        district8.setOnClickListener(this);
        district9.setOnClickListener(this);
        district10.setOnClickListener(this);
        district11.setOnClickListener(this);
        district12.setOnClickListener(this);
        district13.setOnClickListener(this);
        district14.setOnClickListener(this);
        district15.setOnClickListener(this);
        district16.setOnClickListener(this);
        district17.setOnClickListener(this);
        district18.setOnClickListener(this);
        district19.setOnClickListener(this);
        district20.setOnClickListener(this);
        district21.setOnClickListener(this);
        district22.setOnClickListener(this);
        district23.setOnClickListener(this);
        district24.setOnClickListener(this);
        district25.setOnClickListener(this);
        district26.setOnClickListener(this);
        district27.setOnClickListener(this);
        district28.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_district_map);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_district_map);
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_district_map);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            finish();
        }
    }

    /**
     * Menu for nagigation drawer.
     * @param item
     */

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        /*if (id == R.id.district_map) {

        }*/

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

        else if (id == R.id.main_menu) {
            Intent intent = new Intent(this, Member_Main.class);
            startActivity(intent);
        }

        else if (id == R.id.sign_out){
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, Guest_Main.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_district_map);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Sends view and district number to post listing.
     * @param view
     */

    @Override
    public boolean onLongClick(View view) {
        Intent intent = new Intent(this, View_Post.class);
        switch(view.getId()){
            case R.id.button_district_1:
                district = "1";
                intent.putExtra("district", district);
                startActivity(intent);
                break;
            case R.id.button_district_2:
                district = "2";
                intent.putExtra("district", district);
                startActivity(intent);
                break;
            case R.id.button_district_3:
                district = "3";
                intent.putExtra("district", district);
                startActivity(intent);
                break;
            case R.id.button_district_4:
                district = "4";
                intent.putExtra("district", district);
                startActivity(intent);
                break;
            case R.id.button_district_5:
                district = "5";
                intent.putExtra("district", district);
                startActivity(intent);
                break;
            case R.id.button_district_6:
                district = "6";
                intent.putExtra("district", district);
                startActivity(intent);
                break;
            case R.id.button_district_7:
                district = "7";
                intent.putExtra("district", district);
                startActivity(intent);
                break;
            case R.id.button_district_8:
                district = "8";
                intent.putExtra("district", district);
                startActivity(intent);
                break;
            case R.id.button_district_9:
                district = "9";
                intent.putExtra("district", district);
                startActivity(intent);
                break;
            case R.id.button_district_10:
                district = "10";
                intent.putExtra("district", district);
                startActivity(intent);
                break;
            case R.id.button_district_11:
                district = "11";
                intent.putExtra("district", district);
                startActivity(intent);
                break;
            case R.id.button_district_12:
                district = "12";
                intent.putExtra("district", district);
                startActivity(intent);
                break;
            case R.id.button_district_13:
                district = "13";
                intent.putExtra("district", district);
                startActivity(intent);
                break;
            case R.id.button_district_14:
                district = "14";
                intent.putExtra("district", district);
                startActivity(intent);
                break;
            case R.id.button_district_15:
                district = "15";
                intent.putExtra("district", district);
                startActivity(intent);
                break;
            case R.id.button_district_16:
                district = "16";
                intent.putExtra("district", district);
                startActivity(intent);
                break;
            case R.id.button_district_17:
                district = "17";
                intent.putExtra("district", district);
                startActivity(intent);
                break;
            case R.id.button_district_18:
                district = "18";
                intent.putExtra("district", district);
                startActivity(intent);
                break;
            case R.id.button_district_19:
                district = "19";
                intent.putExtra("district", district);
                startActivity(intent);
                break;
            case R.id.button_district_20:
                district = "20";
                intent.putExtra("district", district);
                startActivity(intent);
                break;
            case R.id.button_district_21:
                district = "21";
                intent.putExtra("district", district);
                startActivity(intent);
                break;
            case R.id.button_district_22:
                district = "22";
                intent.putExtra("district", district);
                startActivity(intent);
                break;
            case R.id.button_district_23:
                district = "23";
                intent.putExtra("district", district);
                startActivity(intent);
                break;
            case R.id.button_district_24:
                district = "24";
                intent.putExtra("district", district);
                startActivity(intent);
                break;
            case R.id.button_district_25:
                district = "25";
                intent.putExtra("district", district);
                startActivity(intent);
                break;
            case R.id.button_district_26:
                district = "26";
                intent.putExtra("district", district);
                startActivity(intent);
                break;
            case R.id.button_district_27:
                district = "27";
                intent.putExtra("district", district);
                startActivity(intent);
                break;
            case R.id.button_district_28:
                district = "28";
                intent.putExtra("district", district);
                startActivity(intent);
                break;
        }
        return false;
    }

    /**
     * Display district name on click.
     * @param view
     */

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, Member_Main.class);
        if (temp != null) {
            temp.setVisibility(View.INVISIBLE);
        }

        switch(view.getId()) {
            case R.id.button_district_1:
                district1text = (TextView) findViewById(R.id.text_view_district_1);
                district1text.setVisibility(View.VISIBLE);
                temp = district1text;
                break;
            case R.id.button_district_2:
                district2text = (TextView) findViewById(R.id.text_view_district_2);
                district2text.setVisibility(View.VISIBLE);
                temp = district2text;
                break;
            case R.id.button_district_3:
                district3text = (TextView) findViewById(R.id.text_view_district_3);
                district3text.setVisibility(View.VISIBLE);
                temp = district3text;
                break;
            case R.id.button_district_4:
                district4text = (TextView) findViewById(R.id.text_view_district_4);
                district4text.setVisibility(View.VISIBLE);
                temp = district4text;
                break;
            case R.id.button_district_5:
                district5text = (TextView) findViewById(R.id.text_view_district_5);
                district5text.setVisibility(View.VISIBLE);
                temp = district5text;
                break;
            case R.id.button_district_6:
                district6text = (TextView) findViewById(R.id.text_view_district_6);
                district6text.setVisibility(View.VISIBLE);
                temp = district6text;
                break;
            case R.id.button_district_7:
                district7text = (TextView) findViewById(R.id.text_view_district_7);
                district7text.setVisibility(View.VISIBLE);
                temp = district7text;
                break;
            case R.id.button_district_8:
                district8text = (TextView) findViewById(R.id.text_view_district_8);
                district8text.setVisibility(View.VISIBLE);
                temp = district8text;
                break;
            case R.id.button_district_9:
                district9text = (TextView) findViewById(R.id.text_view_district_9);
                district9text.setVisibility(View.VISIBLE);
                temp = district9text;
                break;
            case R.id.button_district_10:
                district10text = (TextView) findViewById(R.id.text_view_district_10);
                district10text.setVisibility(View.VISIBLE);
                temp = district10text;
                break;
            case R.id.button_district_11:
                district11text = (TextView) findViewById(R.id.text_view_district_11);
                district11text.setVisibility(View.VISIBLE);
                temp = district11text;
                break;
            case R.id.button_district_12:
                district12text = (TextView) findViewById(R.id.text_view_district_12);
                district12text.setVisibility(View.VISIBLE);
                temp = district12text;
                break;
            case R.id.button_district_13:
                district13text = (TextView) findViewById(R.id.text_view_district_13);
                district13text.setVisibility(View.VISIBLE);
                temp = district13text;
                break;
            case R.id.button_district_14:
                district14text = (TextView) findViewById(R.id.text_view_district_14);
                district14text.setVisibility(View.VISIBLE);
                temp = district14text;
                break;
            case R.id.button_district_15:
                district15text = (TextView) findViewById(R.id.text_view_district_15);
                district15text.setVisibility(View.VISIBLE);
                temp = district15text;
                break;
            case R.id.button_district_16:
                district16text = (TextView) findViewById(R.id.text_view_district_16);
                district16text.setVisibility(View.VISIBLE);
                temp = district16text;
                break;
            case R.id.button_district_17:
                district17text = (TextView) findViewById(R.id.text_view_district_17);
                district17text.setVisibility(View.VISIBLE);
                temp = district17text;
                break;
            case R.id.button_district_18:
                district18text = (TextView) findViewById(R.id.text_view_district_18);
                district18text.setVisibility(View.VISIBLE);
                temp = district18text;
                break;
            case R.id.button_district_19:
                district19text = (TextView) findViewById(R.id.text_view_district_19);
                district19text.setVisibility(View.VISIBLE);
                temp = district19text;
                break;
            case R.id.button_district_20:
                district20text = (TextView) findViewById(R.id.text_view_district_20);
                district20text.setVisibility(View.VISIBLE);
                temp = district20text;
                break;
            case R.id.button_district_21:
                district21text = (TextView) findViewById(R.id.text_view_district_21);
                district21text.setVisibility(View.VISIBLE);
                temp = district21text;
                break;
            case R.id.button_district_22:
                district22text = (TextView) findViewById(R.id.text_view_district_22);
                district22text.setVisibility(View.VISIBLE);
                temp = district22text;
                break;
            case R.id.button_district_23:
                district23text = (TextView) findViewById(R.id.text_view_district_23);
                district23text.setVisibility(View.VISIBLE);
                temp = district23text;
                break;
            case R.id.button_district_24:
                district24text = (TextView) findViewById(R.id.text_view_district_24);
                district24text.setVisibility(View.VISIBLE);
                temp = district24text;
                break;
            case R.id.button_district_25:
                district25text = (TextView) findViewById(R.id.text_view_district_25);
                district25text.setVisibility(View.VISIBLE);
                temp = district25text;
                break;
            case R.id.button_district_26:
                district26text = (TextView) findViewById(R.id.text_view_district_26);
                district26text.setVisibility(View.VISIBLE);
                temp = district26text;
                break;
            case R.id.button_district_27:
                district27text = (TextView) findViewById(R.id.text_view_district_27);
                district27text.setVisibility(View.VISIBLE);
                temp = district27text;
                break;
            case R.id.button_district_28:
                district28text = (TextView) findViewById(R.id.text_view_district_28);
                district28text.setVisibility(View.VISIBLE);
                temp = district28text;
                break;
            default:
                if (temp != null){
                    temp.setVisibility(View.GONE);
                }
        }
    }

    /**
     * Sends view to post listing
     * Sends district number = 0 to view all.
     * @param view
     */

    public void propertyList(View view){
        Intent intent = new Intent(this, View_Post.class);
        intent.putExtra("district", "0");
        startActivity(intent);
    }

}
