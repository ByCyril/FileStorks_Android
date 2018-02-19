package com.bycyril.filestorks_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.util.*;

public class FileActivity extends AppCompatActivity {

    final ArrayList filename = new ArrayList<Files>();
    final ArrayList filelink = new ArrayList<Files>();

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

//        System.out.println("test: " + getPipeline());

        retrieveData();
        lv = (ListView)findViewById(R.id.fileview);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("passed link: " + filelink.get(i).toString());

                Intent intent = new Intent(FileActivity.this, Web.class);

                intent.putExtra("link", filelink.get(i).toString());
                startActivity(intent);
            }
        });
    }

    private String getPipeline() {
        return getIntent().getStringExtra("pipeline");
    }


    private void retrieveData() {

        FirebaseDatabase.getInstance().getReference().child("PipeLine").child(getPipeline()).addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                System.out.println("here i am");
                Files file = dataSnapshot.getValue(Files.class);
                System.out.println("filelink: " + file.URL);
                filelink.add(file.URL);
                filename.add(file.FileName);

                ArrayAdapter<Files> adapter = new ArrayAdapter<Files>(lv.getContext(), android.R.layout.simple_list_item_1, filename);
                lv.setAdapter(adapter);


            }


            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
