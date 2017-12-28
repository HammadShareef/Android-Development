package com.example.hammad.cartocarcommunication.AccountActivity.Receiver;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hammad.cartocarcommunication.AccountActivity.HomeActivity;
import com.example.hammad.cartocarcommunication.AccountActivity.initator.initLoginActivity;
import com.example.hammad.cartocarcommunication.AccountActivity.initator.initMapsActivity;
import com.example.hammad.cartocarcommunication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class RecLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmail,editTextPassword;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListner;
    private ProgressDialog progressDialog;
    private DatabaseReference db;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec_login);

        firebaseAuth = FirebaseAuth.getInstance();

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        progressDialog = new ProgressDialog(this);

        buttonLogin=(Button)findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(this);
    }

    public void Login() {
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"Enter your Email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Enter Password",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Sign in to your Account");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();

                if(task.isSuccessful())
                {
                    finish();
                    Toast.makeText(RecLoginActivity.this,"Successfully Sign in to your account",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),initMapsActivity.class));
                }
                else {
                    Toast.makeText(RecLoginActivity.this,"SignIn unsucessfull",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {

        if(view==buttonLogin)
        {
            Login();
        }

    }
}
