package com.example.hammad.cartocarcommunication.AccountActivity.initator;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hammad.cartocarcommunication.MainActivity;
import com.example.hammad.cartocarcommunication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InitatorActivity extends AppCompatActivity {

    private EditText editTextEmail,editTextPassword;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListner;
    private ProgressDialog progressDialog;
    private DatabaseReference db;
    private TextView textView;
    private Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initator);

        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance().getReference();


        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textView=(TextView)findViewById(R.id.textView);


        progressDialog = new ProgressDialog(this);


        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),initLoginActivity.class));
            }
        });
    }
    public void Register()
    {
        final String email=editTextEmail.getText().toString().trim();
        final String password=editTextPassword.getText().toString().trim();

        progressDialog.setMessage("Registering........");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {
                    finish();
                    onAuthSuccess(task.getResult().getUser(),email);
                    Toast.makeText(InitatorActivity.this,"SucessFully Register.....",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(InitatorActivity.this,"Try Again.....",Toast.LENGTH_SHORT).show();

                }
                progressDialog.dismiss();
            }
        });
    }
     public void onAuthSuccess(FirebaseUser user, String email)
                {
                    String userId=user.getUid();
                    db.child("User").child("initUser").child(userId).setValue(email);
                }
}
