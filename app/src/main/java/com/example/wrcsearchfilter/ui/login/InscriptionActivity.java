
package com.example.wrcsearchfilter.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wrcsearchfilter.MainPageActivity;
import com.example.wrcsearchfilter.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class InscriptionActivity extends AppCompatActivity {

    EditText emailId,password;
    Button btnSignUp;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_register);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.field_email);
        password = findViewById(R.id.field_password);

        mFirebaseAuth.signOut();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFireBaseUser = mFirebaseAuth.getCurrentUser();
                if (mFireBaseUser != null) {
                    Intent i = new Intent(InscriptionActivity.this, MainPageActivity.class);
                    Toast.makeText(InscriptionActivity.this, "Inscription réussie", Toast.LENGTH_LONG).show();
                    startActivity(i);
                }
            }
        };


        // INSCRIPTION
        btnSignUp = findViewById(R.id.btn_sign_up);
        /*btnSignUp.setOnClickListener(v -> {

            String email = emailId.getText().toString();
            String pwd = password.getText().toString();

            if(email.isEmpty()){
                emailId.setError("Please enter email id");
                emailId.requestFocus();
            }
            else if (pwd.isEmpty()){
                password.setError("Please enter a password");
                password.requestFocus();
            }
            else if(pwd.length() < 6){
                password.setError("The password must be at least 6 characters long");
            }
            else {
                mFirebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(InscriptionActivity.this, task -> {
                    if (task.isSuccessful()){
                        startActivity(new Intent(InscriptionActivity.this, MainPageActivity.class));
                    }else{
                        Toast.makeText(InscriptionActivity.this,"Echec de la connexion veuillez essayer de nouveau",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });*/

        //CONNEXION
        /*btnSignIn = findViewById(R.id.btn_sign_in);
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
                    Toast.makeText(InscriptionActivity.this, "Veuillez remplir les champs", Toast.LENGTH_SHORT).show();

                } else if (!(email.isEmpty() && pwd.isEmpty())) {
                    mFirebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(InscriptionActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()) {

                                Toast.makeText(InscriptionActivity.this, "Login Error, Please Login Again", Toast.LENGTH_SHORT).show();

                            } else {

                                Intent intToHome = new Intent(InscriptionActivity.this, MainPageActivity.class);
                                startActivity(intToHome);

                            }

                        }
                    });

                } else {
                    Toast.makeText(InscriptionActivity.this, "Error Occured", Toast.LENGTH_SHORT).show();
                }

            }
        });*/
    }

    public void register(View v){
        String email = emailId.getText().toString();
        String pwd = password.getText().toString();

        if(email.isEmpty()){
            emailId.setError("Please enter email id");
            emailId.requestFocus();
        }
        else if (pwd.isEmpty()){
            password.setError("Please enter a password");
            password.requestFocus();
        }
        else if(pwd.length() < 6){
            password.setError("The password must be at least 6 characters long");
        }
        else {
            mFirebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(InscriptionActivity.this, task -> {
                if (task.isSuccessful()){
                    startActivity(new Intent(InscriptionActivity.this, MainPageActivity.class));
                }else{
                    emailId.setError("Cette adresse email existe déjà pour un autre utilisateur");
                    emailId.requestFocus();
                    //Toast.makeText(InscriptionActivity.this,"Echec de la connexion veuillez essayer de nouveau",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
   }
}
