package com.Hecht.ar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.Hecht.ar.Helper.SPmanager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class GoogleLoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CODE_GOOGLE_LOGIN = 200;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    AppCompatButton gButton;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_sheet_login);

        gButton = findViewById(R.id.google_login_btn);

        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        gButton.setOnClickListener(this);

//        if(!SPmanager.getEmail(GoogleLoginActivity.this).isEmpty()){
//            AddActivityAndRemoveIntentTransition(GltfActivity.class);
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //    if (requestCode == REQUEST_CODE_GOOGLE_LOGIN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        AddActivityAndRemoveIntentTransition(MainActivity.class);
        //    try {
                // Google Sign In was successful, authenticate with Firebase
              //  GoogleSignInAccount account = task.getResult(ApiException.class);
               // assert account != null;
               // firebaseAuthWithGoogle(account);
      //      } catch (ApiException e) {
        //        e.printStackTrace();
          //  }
     //   }
    }

    public void googleSignIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, REQUEST_CODE_GOOGLE_LOGIN);
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
    //    AddActivityAndRemoveIntentTransition(MainActivity.class);
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                //    if (task.isSuccessful()) {
                     //   user = mAuth.getCurrentUser();
                        setSPData();
                        AddActivityAndRemoveIntentTransition(MainActivity.class);
               //     } else {
                        // If sign in fails, display a message to the user.
                 //   }

                    // ...
                });
    }

    public void AddActivityAndRemoveIntentTransition(Class activity) {
        Intent intent = new Intent(this, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivityForResult(intent, 0);
        finish();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == gButton.getId()){
           googleSignIn();
//            GltfActivity dialogSheet = new GltfActivity();
//            dialogSheet.show(getSupportFragmentManager(), "");

        }
    }

    private void setSPData(){
        SPmanager.setEmail(GoogleLoginActivity.this, user.getEmail());
        SPmanager.setFirstName(GoogleLoginActivity.this, user.getDisplayName());
        SPmanager.saveAuthID(GoogleLoginActivity.this, user.getUid());
    }
}