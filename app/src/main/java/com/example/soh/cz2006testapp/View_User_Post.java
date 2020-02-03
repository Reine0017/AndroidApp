/**
 * Display property listed by the user.
 */

package com.example.soh.cz2006testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class View_User_Post extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private RecyclerView mUserPostList;
    private DatabaseReference mDatabase;
    private Query mQuery;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    /**
     * Creates the view using the activity_view__user__post xml.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__user__post);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Posts");
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mQuery = mDatabase.orderByChild("Uid").equalTo(mUser.getUid());
        mUserPostList = (RecyclerView) findViewById(R.id.user_post_list);
        mUserPostList.setHasFixedSize(true);
        mUserPostList.setLayoutManager(new LinearLayoutManager(this));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_view_user_post);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_view_user_post);
        View hView =  navigationView.getHeaderView(0);
        TextView nav_user = (TextView)hView.findViewById(R.id.nav_view_display_name);
        nav_user.setText(mAuth.getCurrentUser().getDisplayName());
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(View_User_Post.this);
    }

    /**
     * Closes the navigation drawer on pressing back.
     */

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_view_user_post);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            finish();
        }
    }

    /**
     * Menu for navigation drawer.
     * @param item
     */

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        if (id == R.id.district_map) {
            Intent intent = new Intent(this, District_Map.class);
            startActivity(intent);
            finish();
        }

        else if (id == R.id.calculator) {
            Intent intent = new Intent(this, Calculator_Form.class);
            startActivity(intent);
            finish();
        }

        else if (id == R.id.messages) {
            Intent intent = new Intent(this, View_Chat.class);
            startActivity(intent);
            finish();
        }


        else if (id == R.id.property_list) {
            Intent intent = new Intent(this, Post_Listing_Form.class);
            startActivity(intent);
        }

        /*
        else if (id == R.id.listed_property) {
            Intent intent = new Intent(this, View_User_Post.class);
            startActivity(intent);
            finish();
        }*/

        else if (id == R.id.main_menu) {
            Intent intent = new Intent(this, Member_Main.class);
            startActivity(intent);
            finish();
        }

        else if (id == R.id.sign_out){
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, Guest_Main.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_view_user_post);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Request data from database to display user listed property.
     */

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Post, View_Post.PostViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Post, View_Post.PostViewHolder>(
                Post.class, R.layout.card_view_post_row, View_Post.PostViewHolder.class, mQuery) {

            @Override
            protected void populateViewHolder(View_Post.PostViewHolder viewHolder, Post model, int position) {
                final String postKey = getRef(position).getKey();

                viewHolder.setTitle(model.getTitle());
                viewHolder.setPrice(model.getPrice());
                viewHolder.setDisplayName(model.getDisplayName());
                viewHolder.setImage(getApplicationContext(), model.getImage());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent singlePostIntent = new Intent(View_User_Post.this, Single_Post_Listing.class);
                        singlePostIntent.putExtra("postID", postKey);
                        startActivity(singlePostIntent);
                    }
                });
            }
        };
        mUserPostList.setAdapter(firebaseRecyclerAdapter);
    }
}
