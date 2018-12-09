package bobby.hobby.hel.hel_project.ui.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import bobby.hobby.hel.hel_project.R;
import bobby.hobby.hel.hel_project.Util;

public class DialoggFragment extends DialogFragment {

    private Activity a;
    private String message, positive;
    public DialoggFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.dialogTheme);

    }

    public void setup(Activity a, String message, String positive) {
        this.a = a;
        this.message = message;
        this.positive = positive;
    }
    @Override
    public void onStart() {
        super.onStart();
        //View view = ((AlertDialog) getDialog()).getButton(AlertDialog.BUTTON_POSITIVE);
        //Util.changeBgColor(a,view,R.color.colorAccent);
        ((AlertDialog) getDialog()).getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorAccent,null));
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(a);
        builder.setMessage(message)
                .setPositiveButton(positive, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        return builder.create();
    }
}
