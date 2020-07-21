package com.example.wrcsearchfilter.ui.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wrcsearchfilter.MainPageActivity;
import com.example.wrcsearchfilter.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class ConnexionActivity extends AppCompatActivity {

    EditText emailId,password;
    Button btnSignUp;
    Button btnSignIn;
    TextView tvSignIn;
    FirebaseAuth mFirebaseAuth;
    //private SignInButton signInButton;
    GoogleSignInClient mGoogleSignInClient;
    private String TAG ="LoginActivity";
    private int RC_SIGN_IN = 1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_login);

        //signInButton = findViewById(R.id.signInButton);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseAuth.signOut();
        emailId = findViewById(R.id.field_email);
        password = findViewById(R.id.field_password);
        tvSignIn = findViewById(R.id.textView);
        btnSignUp = findViewById(R.id.btn_sign_up);



        // Google Sign In Service
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);

        //signInButton.setOnClickListener(v -> signIn());

        // INSCRIPTION
        /*btnSignUp.setOnClickListener(v -> {

            String email = emailId.getText().toString();
            String pwd = password.getText().toString();

            if(email.isEmpty()){
                emailId.setError("Please enter email id");
                emailId.requestFocus();
            }
            else if (pwd.isEmpty()){
                password.setError("Please enter your password");
                password.requestFocus();

            }

            else if (email.isEmpty() && pwd.isEmpty()){
                Toast.makeText(ConnexionActivity.this,"Veuillez remplir les champs",Toast.LENGTH_SHORT).show();

            }
            else if (!(email.isEmpty() && pwd.isEmpty())){

                mFirebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(ConnexionActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()){
                            Toast.makeText(ConnexionActivity.this,"Echec de la connexion veuillez essayer de nouveau",Toast.LENGTH_SHORT).show();
                        }else{
                            startActivity(new Intent(ConnexionActivity.this, MainPageActivity.class));
                        }
                    }
                });

            }else{
                Toast.makeText(ConnexionActivity.this,"Error Occured",Toast.LENGTH_SHORT).show();
            }
        });*/

        //CONNEXION
        btnSignIn = findViewById(R.id.btn_sign_in);
        btnSignIn.setOnClickListener(v -> {


            String email = emailId.getText().toString();
            String pwd = password.getText().toString();

            if (email.isEmpty()) {
                emailId.setError("Please enter email id");
                emailId.requestFocus();
            } else if (pwd.isEmpty()) {
                password.setError("Please enter your password");
                password.requestFocus();

            } else {
                mFirebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(ConnexionActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {

                            Toast.makeText(ConnexionActivity.this, "Login Error, Please Login Again", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(ConnexionActivity.this, "Connexion réussie", Toast.LENGTH_LONG).show();
                            Intent intToHome = new Intent(ConnexionActivity.this, MainPageActivity.class);
                            startActivity(intToHome);

                        }

                    }
                });

            }

        });


        tvSignIn.setOnClickListener(v -> {
            Intent i = new Intent(ConnexionActivity.this, InscriptionActivity.class);
            startActivity(i);
        });


    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent,RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {

            GoogleSignInAccount acc = task.getResult(ApiException.class);
            Toast.makeText(ConnexionActivity.this,"Vous êtes connecté",Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(acc);

        }catch (ApiException e){

            Toast.makeText(ConnexionActivity.this,"Echec de la connection",Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(null);

        }

    }

    private void FirebaseGoogleAuth(GoogleSignInAccount acct) {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(acct.getIdToken(),null);
        mFirebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ConnexionActivity.this,"connecté",Toast.LENGTH_SHORT).show();
                    FirebaseUser user = mFirebaseAuth.getCurrentUser();
                    updateUI(user);
                }else{
                    Toast.makeText(ConnexionActivity.this,"Echec",Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }
        });
    }

    private void updateUI(FirebaseUser fuser) {
        
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        
        if (account != null){
            String PersonName = account.getDisplayName();
            String PersonGivenName = account.getDisplayName();
            String PersonFamillyName = account.getDisplayName();
            String PersonEmail = account.getDisplayName();
            String PersonId = account.getDisplayName();
            Uri personPhoto = account.getPhotoUrl();

            Toast.makeText(this, PersonName + " "+ PersonEmail, Toast.LENGTH_SHORT).show();
        }
    }
}
