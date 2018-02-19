package com.bycyril.filestorks_android;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Cy on 2/11/18.
 */

public class ConnectToSession {

    private Context context;
    private DatabaseReference ref;

    public ConnectToSession(Context context) {
        this.context = context;
        ref = FirebaseDatabase.getInstance().getReference();
    }

    public void connectToSession(final String pipeline) {

        ref.child("PipeLineCodes").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChild(pipeline)) {
                    switchActivity(pipeline);
                } else {
                    showError("Pipeline code does not exist. Try a different pipeline code or create a new session");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void switchActivity(String pipeline) {
        Intent fileActivityIntent = new Intent(context, FileActivity.class);
        fileActivityIntent.putExtra("pipeline", pipeline);
        context.startActivity(fileActivityIntent);
    }

    private void showError(String error) {
        AlertDialog ad = new AlertDialog.Builder(context).create();
        ad.setTitle("Error");
        ad.setMessage(error);
        ad.setButton(AlertDialog.BUTTON_NEUTRAL, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        ad.show();
    }



}
