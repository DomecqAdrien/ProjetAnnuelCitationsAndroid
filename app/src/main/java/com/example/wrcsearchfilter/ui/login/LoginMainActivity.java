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
import com.google.firebase.auth.GoogleAuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginMainActivity  extends AppCompatActivity {

    EditText emailId,password;
    Button btnSignUp;
    TextView tvSignIn;
    FirebaseAuth mFirebaseAuth;
    private SignInButton signInButton;
    GoogleSignInClient mGoogleSignInClient;
    private String TAG ="LoginActivity";
    private int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_mainii);

        signInButton = findViewById(R.id.signInButton);
        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        tvSignIn = findViewById(R.id.textView);
        btnSignUp = findViewById(R.id.button);

        // Google Sign In Service
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    signIn();
            }
        });



        btnSignUp.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

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
                    Toast.makeText(LoginMainActivity.this,"Veuillez remplir les champs",Toast.LENGTH_SHORT).show();

                }
                else if (!(email.isEmpty() && pwd.isEmpty())){

                    mFirebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(LoginMainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){

                                Toast.makeText(LoginMainActivity.this,"Echec de la connection veuillez essayer de nouveau",Toast.LENGTH_SHORT).show();

                            }else{

                                startActivity(new Intent(LoginMainActivity.this,HomeActivity.class));

                            }
                        }
                    });

                }else{
                    Toast.makeText(LoginMainActivity.this,"Error Occured",Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginMainActivity.this,LoginMainIIActivity.class);
                startActivity(i);
            }
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
            Toast.makeText(LoginMainActivity.this,"Vous êtes connéctés",Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(acc);

        }catch (ApiException e){

            Toast.makeText(LoginMainActivity.this,"Echec de la connection",Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(null);

        }

    }

    private void FirebaseGoogleAuth(GoogleSignInAccount acct) {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(acct.getIdToken(),null);
        mFirebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(LoginMainActivity.this,"connéctés",Toast.LENGTH_SHORT).show();
                    FirebaseUser user = mFirebaseAuth.getCurrentUser();
                    updateUI(user);
                }else{
                    Toast.makeText(LoginMainActivity.this,"Echec",Toast.LENGTH_SHORT).show();
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
