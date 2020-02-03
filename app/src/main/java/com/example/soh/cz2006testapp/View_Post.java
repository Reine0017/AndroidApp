/**
 * Display the list of properties in the district.
 */

package com.example.soh.cz2006testapp;

import android.content.Context;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class View_Post extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private RecyclerView mPostList;
    private DatabaseReference mDatabase;
    private Query mQuery;
    private FirebaseAuth mAuth;

    /**
     * Creates the view using the activity_view__post xml.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__post);

        mAuth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Posts");

        mPostList = (RecyclerView) findViewById(R.id.post_list);
        mPostList.setHasFixedSize(true);
        mPostList.setLayoutManager(new LinearLayoutManager(this));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_view_post);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_view_post);
        View hView =  navigationView.getHeaderView(0);
        TextView nav_user = (TextView)hView.findViewById(R.id.nav_view_display_name);
        nav_user.setText(mAuth.getCurrentUser().getDisplayName());
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * Closes the navigation drawer on pressing back.
     */

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_view_post);
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

        else if (id == R.id.listed_property) {
            Intent intent = new Intent(this, View_User_Post.class);
            startActivity(intent);
            finish();
        }

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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_view_post);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Request for data from database to display properties.
     */

    @Override
    protected void onStart() {
        super.onStart();

        //String district = "0";
        String district = getIntent().getExtras().getString("district");
        if(district.equals("0"))
            mQuery = mDatabase.orderByKey();
        else
            mQuery = mDatabase.orderByChild("District").equalTo(district);
        //mQuery = mDatabase.orderByKey();
        FirebaseRecyclerAdapter<Post, PostViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Post, PostViewHolder>(
                Post.class,R.layout.card_view_post_row,PostViewHolder.class,mQuery) {
            @Override
            protected void populateViewHolder(PostViewHolder viewHolder, Post model, int position) {
                final String postKey = getRef(position).getKey();

                viewHolder.setTitle(model.getTitle());
                viewHolder.setPrice(model.getPrice());
                viewHolder.setDisplayName(model.getDisplayName());
                viewHolder.setImage(getApplicationContext(),model.getImage());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent singlePostIntent = new Intent(View_Post.this, Single_Post_Listing.class);
                        singlePostIntent.putExtra("postID",postKey);
                        startActivity(singlePostIntent);
                    }
                });
            }
        };

        mPostList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {

        View mView;

        /**
         * Constructor of view holder.
         * @param itemView
         */

        public PostViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

        }

        /**
         * Sets title of property.
         * @param title
         */

        public void setTitle(String title){
            TextView postTitle = (TextView) mView.findViewById(R.id.text_view_post_title);
            postTitle.setText(title);
        }

        /**
         * Sets price of property.
         * @param desc
         */

        public void setPrice(String desc){
            TextView postPrice = (TextView) mView.findViewById(R.id.text_view_post_price);
            postPrice.setText(desc);
        }

        /**
         * Sets image of property.
         * @param ctx
         * @param image
         */

        public void setImage(Context ctx, String image){

            ImageView postImage = (ImageView) mView.findViewById(R.id.post_image);
            Picasso.with(ctx).load(image).into(postImage);
        }

        /**
         * Sets display name of property.
         * @param displayName
         */

        public void setDisplayName(String displayName){
            TextView postDisplayName = (TextView) mView.findViewById(R.id.text_view_post_display_name);
            postDisplayName.setText(displayName);
        }
    }
}
