/**
 * Display all chats of the user.
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

public class View_Chat extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView mChatList;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    /**
     * Creates the view using the activity_view__chat xml.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__chat);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(mUser.getUid());
        mChatList = (RecyclerView) findViewById(R.id.chat_list);
        mChatList.setHasFixedSize(true);
        mChatList.setLayoutManager(new LinearLayoutManager(this));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_view_chat);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_view_chat);
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_view_chat);
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
        /*
        else if (id == R.id.messages) {

        }*/
        else if (id == R.id.listed_property) {
            Intent intent = new Intent(this, View_User_Post.class);
            intent.putExtra("district", "0");
            startActivity(intent);
            finish();
        }

        else if (id == R.id.property_list) {
            Intent intent = new Intent(this, Post_Listing_Form.class);
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_view_chat);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    /**
     *  Request for chat information.
     */

    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Single_Chat,ChatViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Single_Chat, ChatViewHolder>(
                Single_Chat.class,R.layout.card_view_chat_row,ChatViewHolder.class,mDatabase.child("Chats")
        ) {
            @Override
            protected void populateViewHolder(ChatViewHolder viewHolder, Single_Chat model, int position) {
                final String targetUid = getRef(position).getKey();
                viewHolder.setDisplayName(model.getDisplayName());
                final String chatID = model.getChatID();

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent chatIntent = new Intent(View_Chat.this,Chat.class);
                        chatIntent.putExtra("ChatID",chatID);
                        chatIntent.putExtra("targetUid",targetUid);
                        startActivity(chatIntent);
                    }
                });
            }
        };
        mChatList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class ChatViewHolder extends RecyclerView.ViewHolder{

        View mView;

        /**
         * Constructor for view holder.
         * @param itemView
         */

        public ChatViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
        }

        /**
         * Set user display name.
         * @param displayName
         */

        public void setDisplayName(String displayName){
            TextView mDisplayName = (TextView) mView.findViewById(R.id.text_view_chat_display_name);
            mDisplayName.setText(displayName);
        }
    }
}

