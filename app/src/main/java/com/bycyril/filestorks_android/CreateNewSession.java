package com.bycyril.filestorks_android;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.*;
import java.text.*;

/**
 * Created by Cy on 2/11/18.
 */

public class CreateNewSession {

    private String pipelinecode;
    private DatabaseReference ref;
    private Random ran;
    private Context context;

    public CreateNewSession(Context context) {
        this.context = context;
        ran = new Random();
        ref = FirebaseDatabase.getInstance().getReference();

    }

    public void createNewSession() {
        final String pipeline = generatePipelineCode();
        String timestamp = getTimeStamp();

        Map<String, String> info = new HashMap<String, String>();

        info.put("Date", timestamp);

        ref.child("PipeLineCodes").child(pipeline).setValue(info, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                if (databaseError != null) {
                    showError(databaseError.getMessage().toString());
                } else {

                    switchActivity();
                }

            }
        });

    }

    public String getPipelineCode() {
        return this.pipelinecode;
    }

    private void showError(String err) {
        AlertDialog ad = new AlertDialog.Builder(context).create();
        ad.setTitle("Error");
        ad.setMessage(err);
        ad.setButton(AlertDialog.BUTTON_NEUTRAL, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        ad.show();

    }
    private String getTimeStamp() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();

        return dateFormat.format(date);
    }

    private String generatePipelineCode() {
        String characters = "abcdefghijklmnopqrstuvwxyz1234567890";
        String[] charArray = characters.split("");
        String pipeline = "";
        System.out.println("Here I am");

        for (int i = 0; i < 4; i++) {
            int n = ran.nextInt(charArray.length);
            System.out.println(n);
            pipeline += charArray[n];
        }

        return pipeline;

    }

    private void switchActivity() {
        Intent fileActivityIntent = new Intent(context, FileActivity.class);
        fileActivityIntent.putExtra("pipeline", getPipelineCode());
        context.startActivity(fileActivityIntent);
    }



}
