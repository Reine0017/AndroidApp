/**
 * Post listing form request user input,
 * sending the property details to the database.
 */

package com.example.soh.cz2006testapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Post_Listing_Form extends AppCompatActivity implements AdapterView.OnItemSelectedListener, NavigationView.OnNavigationItemSelectedListener{

    private ImageButton mSelectImage;
    private EditText mPostTitle;
    private EditText mPostAdd;
    private EditText mPostPrice;
    private EditText mPostSize;
    private EditText mPostAmenities;
    private Spinner mSellRent;
    private Spinner mPostTenure;
    private Spinner mPostRoom;
    private Spinner mPostDistrict;
    private Button mSubmitBtn;
    private String district;
    private String room;
    private String tenure;

    private RelativeLayout mRentalForm;
    private Uri mImageUri = null;
    private ProgressDialog mProgress;

    private static final int GALLERY_REQUEST = 1;

    private StorageReference mStorage;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private DatabaseReference mDatabaseUser;

    /**
     * Creates the view using the activity_post__listing__form xml.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post__listing__form);

        mStorage = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Posts");
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabaseUser = FirebaseDatabase.getInstance().getReference().child("Users").child(mUser.getUid());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_post_listing_form);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_post_listing_form);
        View hView =  navigationView.getHeaderView(0);
        TextView nav_user = (TextView)hView.findViewById(R.id.nav_view_display_name);
        nav_user.setText(mAuth.getCurrentUser().getDisplayName());
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        mPostTitle = (EditText) findViewById(R.id.edit_text_post_title);
        mPostAdd = (EditText) findViewById(R.id.edit_text_post_add);
        mPostPrice = (EditText) findViewById(R.id.edit_text_post_price);
        mPostSize = (EditText) findViewById(R.id.edit_text_post_size);
        mPostAmenities = (EditText) findViewById(R.id.edit_text_post_amenities);
        mSellRent = (Spinner) findViewById(R.id.spinner_sell_rent);
        mPostDistrict = (Spinner) findViewById(R.id.spinner_district);
        mPostRoom = (Spinner) findViewById(R.id.spinner_room);
        mPostTenure = (Spinner) findViewById(R.id.spinner_tenure);
        mSubmitBtn = (Button) findViewById(R.id.button_submit);
        mSelectImage = (ImageButton) findViewById(R.id.image_select);

        mRentalForm = (RelativeLayout) findViewById(R.id.rental_form);

        mProgress = new ProgressDialog(this);
        mSellRent.setOnItemSelectedListener(this);
        mPostDistrict.setOnItemSelectedListener(this);
        mPostTenure.setOnItemSelectedListener(this);
        mPostRoom.setOnItemSelectedListener(this);

        mSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST);
            }
        });

        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPosting();
            }
        });
    }

    /**
     * Closes the navigation drawer on pressing back.
     */

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_post_listing_form);
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

        /*
        else if (id == R.id.property_list) {
            Intent intent = new Intent(this, View_Post.class);
            startActivity(intent);
            finish();
        }*/

        else if (id == R.id.listed_property) {
            Intent intent = new Intent(this, View_User_Post.class);
            intent.putExtra("district", "0");
            startActivity(intent);
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_post_listing_form);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Reads user input and sends the data to the database.
     */

    private void startPosting() {
        mProgress.setMessage("Posting...");

        final String title = mPostTitle.getText().toString().trim();
        final String add = mPostAdd.getText().toString().trim();
        final String price = mPostPrice.getText().toString().trim();
        final String size = mPostSize.getText().toString().trim();

        if(TextUtils.isEmpty(title)) {
            Toast.makeText(this, "Please fill in title", Toast.LENGTH_SHORT).show();
            focusText(mPostTitle);
        }
        else if(TextUtils.isEmpty(add)){
            Toast.makeText(this, "Please fill in address", Toast.LENGTH_SHORT).show();
            focusText(mPostAdd);
        }
        else if(TextUtils.isEmpty(price)) {
            Toast.makeText(this, "Please fill in price", Toast.LENGTH_SHORT).show();
            focusText(mPostPrice);
        }
        else if(TextUtils.isEmpty(size)){
            Toast.makeText(this, "Please fill in size", Toast.LENGTH_SHORT).show();
            focusText(mPostSize);
        }

        if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(add) && mImageUri != null && !TextUtils.isEmpty(price) && !TextUtils.isEmpty(size)){
            mProgress.show();
            StorageReference filepath = mStorage.child("Post Images").child(mImageUri.getLastPathSegment());
            filepath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    final Uri downloadUrl = taskSnapshot.getDownloadUrl();

                    final DatabaseReference newPost = mDatabase.push();
                    newPost.child("Title").setValue(title);
                    newPost.child("Add").setValue(add);
                    newPost.child("Price").setValue(price);
                    newPost.child("Size").setValue(size);
                    newPost.child("District").setValue(district);
                    newPost.child("Rooms").setValue(room);
                    newPost.child("Tenure").setValue(tenure);
                    newPost.child("Image").setValue(downloadUrl.toString());
                    newPost.child("Uid").setValue(mUser.getUid());
                    newPost.child("DisplayName").setValue(mUser.getDisplayName());
                    if(mSellRent.getSelectedItem().toString().equals("Renting")){
                        String amenities = mPostAmenities.getText().toString().trim();
                        newPost.child("Renting").setValue("Y");
                        newPost.child("Amenities").setValue(amenities);
                    }
                    else
                        newPost.child("Renting").setValue("N");
                    Intent intent = new Intent(Post_Listing_Form.this,Member_Main.class);
                    startActivity(intent);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Post_Listing_Form.this,e.getMessage(),Toast.LENGTH_LONG).show();
                }
            });

            mProgress.dismiss();
        }
    }

    /**
     * Sets property image read from gallery.
     * @param requestCode
     * @param resultCode
     * @param data
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){
            mImageUri = data.getData();
            mSelectImage.setImageURI(mImageUri);
        }
    }

    /**
     * Change focus of view to empty text.
     * @param etText
     */

    private void focusText(EditText etText)
    {
        //Focus on the EditText that is empty or contain invalid characters
        etText.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * Method for sending spinner choice input.
     * @param parent
     * @param view
     * @param position
     * @param id
     */

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.spinner_district:
                district = String.valueOf(position+1);
                break;
            case R.id.spinner_room:
                room = parent.getSelectedItem().toString();
                break;
            case R.id.spinner_tenure:
                tenure = parent.getSelectedItem().toString();
                break;
            case R.id.spinner_sell_rent:
                if (parent.getSelectedItem().toString().equals("Renting")){
                    mRentalForm.setVisibility(View.VISIBLE);
                    Toast.makeText(Post_Listing_Form.this,"RENTING",Toast.LENGTH_LONG);
                }
                else if (parent.getSelectedItem().toString().equals("Selling")) {
                    mRentalForm.setVisibility(View.GONE);
                    Toast.makeText(Post_Listing_Form.this,"SELLING",Toast.LENGTH_LONG);
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show();
        parent.requestFocus();
    }

}