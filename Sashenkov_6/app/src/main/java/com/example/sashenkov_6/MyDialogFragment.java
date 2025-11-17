package com.example.sashenkov_6;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

public class MyDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_message);

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View layout = inflater.inflate(R.layout.dialog_fragment_layout, null);
        builder.setView(layout);

        EditText editText = layout.findViewById(R.id.edit);

        builder.setTitle("Edit info");

        builder.setPositiveButton(R.string.exit, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                getActivity().finish();
            }
        });
        builder.setNeutralButton(R.string.edit, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                MyDialogFragmentListener activity = (MyDialogFragmentListener) getActivity();
                activity.onReturnValue(editText.getText().toString());
            }
        });
        builder.setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        return builder.create();
    }

    public interface MyDialogFragmentListener {
        void onReturnValue(String value);
    }
}
