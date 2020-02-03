/**
 * Login page that request user to enter email address
 * and password that has been registered in the database.
 */

package com.example.soh.cz2006testapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Form extends AppCompatActivity {

    private EditText mEmail;
    private EditText mPassword;
    private Button mButton;

    private ProgressDialog mProgress;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    private TextView login_text;
    private String username;
    private RelativeLayout login_screen, login_success;

    /**
     * Creates the view using the activity_login__form xml.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__form);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        login_text = (TextView) findViewById(R.id.text_view_login);
        login_screen = (RelativeLayout) findViewById(R.id.login_screen);
        login_success = (RelativeLayout) findViewById(R.id.login_success);

        mAuth = FirebaseAuth.getInstance();

        mProgress = new ProgressDialog(this);
        mEmail = (EditText) findViewById(R.id.edit_text_login_email);
        mPassword = (EditText) findViewById(R.id.edit_text_login_password);
        mButton = (Button) findViewById(R.id.button_login);

        mAuthStateListener = new FirebaseAuth.AuthStateListener(){

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){
                    login_text.setText("Welcome back \n"+ mAuth.getCurrentUser().getDisplayName());
                    login_screen.setVisibility(View.GONE);
                    login_success.setVisibility(View.VISIBLE);
                }
            }
        };


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSignIn();
            }
        });
    }

    /**
     * Creates an authenticator when the login form instance start.
     */

    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthStateListener);
    }

    /**
     * Sends the view to the register form.
     * @param view
     */

    public void register(View view){
        Intent intent = new Intent(this, Register_Form.class);
        startActivity(intent);
        finish();
    }

    /**
     * Sends view to member main menu.
     * @param view
     */

    public void loginContinue(View view) {
        Intent intent = new Intent(this, Member_Main.class);
        startActivity(intent);
        finish();
    }

    /**
     * Authenticate the input against the registered accounts.
     */

    private void startSignIn() {
        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            mProgress.setMessage("Signing in...");
            mProgress.show();
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(Login_Form.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(Login_Form.this, "Sign In Problem", Toast.LENGTH_LONG).show();
                                mProgress.dismiss();
                            }
                            else{
                                mProgress.dismiss();
                                login_text.setText("Welcome back \n"+ mAuth.getCurrentUser().getDisplayName());
                                login_screen.setVisibility(View.GONE);
                                login_success.setVisibility(View.VISIBLE);
                            }
                        }
                    });
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

