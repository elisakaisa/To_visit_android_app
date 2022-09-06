package com.example.to_visit_app.utils;
/*
class used by MainActivity to get alert Dialogs
Elisa Perini
 */
import android.content.Context;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AlertDial extends AppCompatActivity {

    // Alert dialogs for error messages
    public AlertDialog createMsgDialog(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Ok", (dialog, id) -> {});
        return builder.create();
    }

    // Alert dialogs for error messages
    //TODO: implement listener to make sure the thing is cancellable
    public AlertDialog createCancellableMsgDialog(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.setPositiveButton("Ok", (dialog, id) -> {});
        builder.setNegativeButton("Cancel", (dialog, id) -> dialog.cancel());
        return builder.create();
    }
}

