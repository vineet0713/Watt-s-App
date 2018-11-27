package com.example.vjoshi.wattsapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignInActivity extends AppCompatActivity {
    private static final String TAG = "SignInActivity";

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private EditText emailField, passwordField;
    private Button signinButton, joinButton;

    private ProgressBar loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        auth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // user is signed in!
                    Log.d(TAG, "user is signed in!");
                    Log.d(TAG, "display name: " + user.getDisplayName());
                    Log.d(TAG, "email: " + user.getEmail());
                } else {
                    // user is not signed in
                    Log.d(TAG, "user is not signed in");
                }
            }
        };

        emailField = findViewById(R.id.editText2);
        passwordField = findViewById(R.id.editText3);
        clearTextFields();

        signinButton = findViewById(R.id.button3);
        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signinPressed();
            }
        });

        joinButton = findViewById(R.id.button4);
        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joinPressed();
            }
        });

        loadingBar = findViewById(R.id.progressBar);
        loadingBar.setVisibility(ProgressBar.INVISIBLE);
    }

    @Override
    protected void onStart() {
        super.onStart();

        auth.addAuthStateListener(authStateListener);
    }

    public void finish(View view) {
//        Intent intent = new Intent(this, MainList.class);
//        startActivity(intent);
    }

    private void signinPressed() {
        hideKeyboard();

        loadingBar.setVisibility(ProgressBar.VISIBLE);

        final String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String username = email.substring(0, email.indexOf('@'));
                    Backend.getInstance().setUsername(username);
                    saveUsername(username);

                    Log.d(TAG, "sign in successful!");
                    goToHomePage();
                } else {
                    Log.d(TAG, "sign in failed: " + task.getException());
                    displayAlert("Sign In Failed", task.getException().getMessage(), false);
                }

                clearTextFields();
                loadingBar.setVisibility(ProgressBar.INVISIBLE);
            }
        });
    }

    private void saveUsername(String username) {
        MySQLiteHelper myDB = new MySQLiteHelper(this);
        // maybe do something if 'setUsername' returns false?
        myDB.setUsername(username);
    }

    private void createAlert(String title, String message){
        AlertDialog alertDialog = new AlertDialog.Builder(SignInActivity.this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    private void joinPressed() {
        hideKeyboard();
        loadingBar.setVisibility(ProgressBar.VISIBLE);

        Log.d(TAG, "joinPressed");

        final String email = emailField.getText().toString();
        final String password = passwordField.getText().toString();

        Log.d(TAG, "EMAIL: " + email);
        Log.d(TAG, "PASSWORD: " + password);
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String username = email.substring(0, email.indexOf('@'));

                    // saves username to the local database
                    saveUsername(username);

                    // saves the user to the backend
                    Backend.getInstance().setUsername(username);
                    User u = new User(username, 0);
                    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                    database.child(username).setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Log.d(TAG, "create user successful!");
                            displayAlert("User Created", "Thanks for joining the team!", true);
                        }
                    });
                } else {
                    Log.d(TAG, "create user failed: " + task.getException());
                    displayAlert("Create User Failed", task.getException().getMessage(), false);
                }

                clearTextFields();
                loadingBar.setVisibility(ProgressBar.INVISIBLE);
            }
        });
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager)(getSystemService(Activity.INPUT_METHOD_SERVICE));
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    private void displayAlert(String title, String message, boolean proceed) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        if (proceed == true) {
            builder.setPositiveButton("Get Started", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    goToHomePage();
                }
            });
        } else {
            builder.setPositiveButton("Ok", null);
        }
        builder.show();
    }

    private void goToHomePage() {
        Intent homeIntent = new Intent(this, HomeActivity.class);
        startActivity(homeIntent);
    }

    private void clearTextFields() {
        emailField.setText("");
        passwordField.setText("");
        emailField.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }
}

