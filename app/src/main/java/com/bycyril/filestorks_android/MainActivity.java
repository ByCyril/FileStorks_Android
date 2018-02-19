package com.bycyril.filestorks_android;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button createnewsessionbutton;
    Button connecttosessionbutton;
    EditText pipelinetextview;

    CreateNewSession createnewsession = new CreateNewSession(MainActivity.this);
    ConnectToSession connecttosession = new ConnectToSession(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setInterface();
        createNewSessionAction();
        connectToPipelineAction();

    }

    void setInterface() {
        createnewsessionbutton = (Button)findViewById(R.id.main_createnewsessionbutton);
        connecttosessionbutton = (Button)findViewById(R.id.main_connecttosessionbutton);
        pipelinetextview = (EditText)findViewById(R.id.main_pipelinetextview);

    }

    void createNewSessionAction() {

        createnewsessionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createnewsession.createNewSession();
            }
        });

    }

    void connectToPipelineAction() {
        connecttosessionbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String getPipelineCode = pipelinetextview.getText().toString();
                connecttosession.connectToSession(getPipelineCode);
            }
        });
    }


}
