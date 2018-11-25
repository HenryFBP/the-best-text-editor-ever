package cooldomainname.com.the_best_text_editor_ever;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.*;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class SaveFileDialogFragment extends DialogFragment implements TextView.OnEditorActionListener {
    private static String DEFAULT_TITLE = "Filename";
    private EditText mEditText;

    public SaveFileDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static SaveFileDialogFragment newInstance(String title) {
        SaveFileDialogFragment frag = new SaveFileDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    // ...
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get field from view
        mEditText = view.findViewById(R.id.editTextFilename);

        // Fetch arguments from bundle and set title
        String title = (getArguments() != null) ?
                getArguments().getString("title", DEFAULT_TITLE)
                :
                DEFAULT_TITLE;

        getDialog().setTitle(title);

        // Show soft keyboard automatically and request focus to field
        mEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        // 2. Setup a callback when the "Done" button is pressed on keyboard
        mEditText.setOnEditorActionListener(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dialog_filename, container);
    }


    // Fires whenever the textfield has an action performed
    // In this case, when the "Done" button is pressed
    // REQUIRES a 'soft keyboard' (virtual keyboard)
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            // Return input text back to activity through the implemented listener
            SaveFileDialogListener listener = (SaveFileDialogListener) getActivity();
            listener.onFinishEditSaveDialog(mEditText.getText().toString());
            // Close the dialog and return back to the parent activity
            dismiss();
            return true;
        }
        return false;
    }

    // 1. Defines the listener interface with a method passing back data result.
    public interface SaveFileDialogListener {
        void onFinishEditSaveDialog(String inputText);

    }
}
