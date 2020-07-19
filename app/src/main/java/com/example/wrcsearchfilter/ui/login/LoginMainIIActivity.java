
package com.example.wrcsearchfilter.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wrcsearchfilter.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginMainIIActivity extends AppCompatActivity {

    EditText emailId,password;
    Button btnSignIn;
    TextView tvSignUp;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main_iv);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        tvSignUp = findViewById(R.id.textView);
        btnSignIn = findViewById(R.id.button);
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFireBaseUser = mFirebaseAuth.getCurrentUser();
                if (mFireBaseUser != null) {

                    Toast.makeText(LoginMainIIActivity.this, "Vous etes connéctés", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginMainIIActivity.this, HomeActivity.class);
                    startActivity(i);
                } else {

                    Toast.makeText(LoginMainIIActivity.this, "Please Login", Toast.LENGTH_SHORT).show();

                }

            }
        };

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email = emailId.getText().toString();
                String pwd = password.getText().toString();

                if (email.isEmpty()) {
                    emailId.setError("Please enter email id");
                    emailId.requestFocus();
                } else if (pwd.isEmpty()) {
                    password.setError("Please enter your password");
                    password.requestFocus();

                } else if (email.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(LoginMainIIActivity.this, "Veuillez remplir les champs", Toast.LENGTH_SHORT).show();

                } else if (!(email.isEmpty() && pwd.isEmpty())) {
                    mFirebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(LoginMainIIActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()) {

                                Toast.makeText(LoginMainIIActivity.this, "Login Error, Please Login Again", Toast.LENGTH_SHORT).show();

                            } else {

                                Intent intToHome = new Intent(LoginMainIIActivity.this, HomeActivity.class);
                                startActivity(intToHome);

                            }

                        }
                    });

                } else {
                    Toast.makeText(LoginMainIIActivity.this, "Error Occured", Toast.LENGTH_SHORT).show();
                }

            }
        });
        tvSignUp.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Intent intSignUp = new Intent(LoginMainIIActivity.this, LoginMainActivity.class);
                startActivity(intSignUp);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
   }
}
