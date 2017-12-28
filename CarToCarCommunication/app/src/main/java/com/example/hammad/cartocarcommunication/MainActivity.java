package com.example.hammad.cartocarcommunication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hammad.cartocarcommunication.AccountActivity.initator.InitatorActivity;
import com.example.hammad.cartocarcommunication.AccountActivity.Receiver.ReceiverActivity;

public class MainActivity extends AppCompatActivity {

    private Button buttonInitator;
    private Button buttonReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonInitator = (Button) findViewById(R.id.buttonInitator);
        buttonReceiver=(Button)findViewById(R.id.buttonReceiver);

        buttonInitator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,InitatorActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });


        buttonReceiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ReceiverActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });



    }
}
