/**
 * Displays details of individual property listed.
 */

package com.example.soh.cz2006testapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Single_Post_Listing extends AppCompatActivity {

    FloatingActionButton chat;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    /**
     * Creates the view using the activity_single__post_listing xml.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single__post_listing);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ImageView mPostImage = (ImageView) findViewById(R.id.post_image);
        final TextView mPostTitle = (TextView) findViewById(R.id.text_view_post_title);
        final TextView mPostPrice = (TextView) findViewById(R.id.text_view_post_price);
        final TextView mPostAdd = (TextView) findViewById(R.id.text_view_post_add);
        final TextView mPostDistrict = (TextView) findViewById(R.id.text_view_post_district);
        final TextView mPostRoom = (TextView) findViewById(R.id.text_view_post_room);
        final TextView mPostSize = (TextView) findViewById(R.id.text_view_post_size);
        final TextView mPostTenure = (TextView) findViewById(R.id.text_view_post_tenure);
        final RelativeLayout mAmenitiesLayout = (RelativeLayout) findViewById(R.id.amenities_layout);
        final TextView mPostAmenities = (TextView) findViewById(R.id.text_view_post_amenities);

        chat = (FloatingActionButton) findViewById(R.id.fab_chat);
        String postKey = getIntent().getExtras().getString("postID");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Posts").child(postKey);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        mDatabase.addValueEventListener(new ValueEventListener() {

            /**
             * Sets property details.
             * @param dataSnapshot
             */

            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                String title = (String) dataSnapshot.child("Title").getValue();
                String price = (String) dataSnapshot.child("Price").getValue();
                String add = (String) dataSnapshot.child("Add").getValue();
                String district = (String) dataSnapshot.child("District").getValue();
                String rooms = (String) dataSnapshot.child("Rooms").getValue();
                String size = (String) dataSnapshot.child("Size").getValue();
                String tenure = (String) dataSnapshot.child("Tenure").getValue();

                Picasso.with(getApplicationContext()).load(dataSnapshot.child("Image").getValue().toString()).into(mPostImage);
                mPostTitle.setText(title);
                mPostPrice.append(price);
                mPostDistrict.append(district);
                mPostAdd.append(add);
                mPostRoom.append(rooms);
                mPostSize.append(size);
                mPostTenure.append(tenure);

                if(dataSnapshot.child("Renting").getValue().toString().equals("Y")){
                    mAmenitiesLayout.setVisibility(View.VISIBLE);
                    String amenities = (String) dataSnapshot.child("Amenities").getValue();
                    mPostAmenities.append(amenities);
                }

                if(dataSnapshot.child("Uid").getValue().toString().equals(mUser.getUid()))
                    chat.hide();
                chat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String targetUid = (String) dataSnapshot.child("Uid").getValue();
                        String targetDisplayName = (String) dataSnapshot.child("DisplayName").getValue();
                        Intent intent = new Intent(Single_Post_Listing.this,Chat.class);
                        intent.putExtra("targetUid",targetUid);
                        intent.putExtra("targetDisplayName",targetDisplayName);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


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
