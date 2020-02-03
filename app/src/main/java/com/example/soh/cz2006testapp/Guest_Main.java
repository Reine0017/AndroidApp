/**
 * First page that users would view on entering the application.
 * Provides navigations to the help guide or the login page.
 */

package com.example.soh.cz2006testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Guest_Main extends AppCompatActivity {

    /**
     * Creates the view using the activity_guide_main xml.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_main);
    }

    /**
     * Sends the view to login page.
     * @param view
     */

    public void startApplication(View view){
        //FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, Login_Form.class);
        startActivity(intent);
    }

    /**
     * Sends the view to help guide
     * @param view
     */

    public void helpStart(View view) {
        Intent intent = new Intent(this, Guide_Control.class);
        startActivity(intent);
    }
}
