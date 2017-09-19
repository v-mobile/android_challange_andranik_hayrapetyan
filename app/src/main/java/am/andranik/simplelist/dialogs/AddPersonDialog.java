package am.andranik.simplelist.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import am.andranik.simplelist.R;
import am.andranik.simplelist.models.Person;

/**
 * Created by andranik on 9/18/17.
 */

public class AddPersonDialog extends DialogFragment {

    private static final String TAG = "am.andranik.simplelist.dialogs.AddPersonDialog";

    public static void show(FragmentManager fm){
        DialogFragment dialog = new AddPersonDialog();
        dialog.show(fm, TAG);
    }

    private AddPersonDialogListener callback;

    private EditText fNameEt;
    private EditText lNameEt;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            callback = (AddPersonDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement AddPersonDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_add_person, null);

        fNameEt = (EditText) view.findViewById(R.id.f_name_et);
        lNameEt = (EditText) view.findViewById(R.id.l_name_et);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(view)
                .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if(isInputValid()) {
                            callback.onPersonAdded(
                                    Person.create(getIplText(fNameEt), getIplText(lNameEt)));
                        } else {
                            Toast.makeText(getContext(), R.string.warning_fname_lname_required, Toast.LENGTH_SHORT).show();
                        }

                        dialog.cancel();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        return builder.create();
    }

    private boolean isInputValid() {
        return !getIplText(fNameEt).isEmpty() && !getIplText(lNameEt).isEmpty();
    }

    public String getIplText(EditText et){
        return et.getText().toString();
    }


    public interface AddPersonDialogListener {
        void onPersonAdded(Person person);
    }
}
