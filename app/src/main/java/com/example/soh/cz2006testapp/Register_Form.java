/**
 * Register form that allows new users to create an account.
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register_Form extends AppCompatActivity {

    private EditText mRegisterEmail;
    private EditText mRegisterPassword;
    private EditText mRegisterDisplayName;
    private Button mRegisterButton;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private ProgressDialog mProgress;

    /**
     * Creates the view using the activity_register_form xml.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__form);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        mProgress = new ProgressDialog(this);

        mRegisterEmail = (EditText) findViewById(R.id.edit_text_register_email);
        mRegisterPassword = (EditText) findViewById(R.id.edit_text_register_password);
        mRegisterDisplayName = (EditText) findViewById(R.id.edit_text_register_display_name);
        mRegisterButton = (Button) findViewById(R.id.button_register);

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRegister();
            }
        });
    }

    /**
     * Sends user inputs to database to be added as an account.
     */

    private void startRegister() {
        String email = mRegisterEmail.getText().toString().trim();
        String password = mRegisterPassword.getText().toString().trim();
        final String displayName = mRegisterDisplayName.getText().toString().trim();

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(displayName)) {
            mProgress.setMessage("Signing up...");
            mProgress.show();
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(Register_Form.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        mProgress.dismiss();
                    } else {
                        UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder().setDisplayName(displayName).build();
                        mAuth.getCurrentUser().updateProfile(profileUpdate);
                        mProgress.dismiss();
                        Toast.makeText(Register_Form.this, "Account successfully created", Toast.LENGTH_LONG).show();
                        Intent mainIntent = new Intent(Register_Form.this, Member_Main.class);
                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(mainIntent);
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
