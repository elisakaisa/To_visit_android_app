package com.example.to_visit_app.utils;
/*
class used by MainActivity to get alert Dialogs
Elisa Perini
 */
import android.content.Context;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AlertDial extends AppCompatActivity {
    private AlertInterface listener;

    // Alert dialogs for error messages
    public AlertDialog createMsgDialog(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Ok", (dialog, id) -> {});
        return builder.create();
    }

    // Alert dialogs for error messages with yes or no
    public AlertDialog createCancellableMsgDialog(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", (dialog, id) -> listener.onAlert(true));
        builder.setNegativeButton("No", (dialog, id) -> listener.onAlert(false));
        return builder.create();
    }

    public interface AlertInterface {
        void onAlert(boolean ok);
    }

    public void setAlertListener(AlertDial.AlertInterface listener) {
        // Assign the listener implementing events interface that will receive the events
        this.listener = listener;
    }
}

