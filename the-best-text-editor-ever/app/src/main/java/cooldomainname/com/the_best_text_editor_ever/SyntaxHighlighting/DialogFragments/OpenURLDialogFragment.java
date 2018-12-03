package cooldomainname.com.the_best_text_editor_ever.SyntaxHighlighting.DialogFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.*;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import cooldomainname.com.the_best_text_editor_ever.R;

/**
 * A general fragment for doing...something with a file.
 */
public class OpenURLDialogFragment extends DialogFragment implements TextView.OnEditorActionListener {
    static String DEFAULT_TITLE = "Filename";

    /* The URL. */
    EditText mEditText;

    /* Title. */
    TextView mTextView;

    /* Progress bar. */
    ProgressBar mProgressBar;

    /**
     * Status of getting URL.
     * Use for errors and such.
     **/
    TextView mEditTextStatus;


    public OpenURLDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }


    public static FileDialogFragment newInstance(String title) {
        FileDialogFragment frag = new FileDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get UI elements from view
        mEditText = view.findViewById(R.id.editTextFilename);
        mTextView = view.findViewById(R.id.textViewTitle);
        mProgressBar = view.findViewById(R.id.progressBar);
        mEditTextStatus = view.findViewById(R.id.textViewStatus);

        // Fetch arguments from bundle and set title
        String title = (getArguments() != null) ?
                getArguments().getString("title", DEFAULT_TITLE)
                :
                DEFAULT_TITLE;

        getDialog().setTitle(title);
        mTextView.setText(title);

        // Show soft keyboard automatically and request focus to field
        mEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        // Setup a callback when the "Done" button is pressed on keyboard
        mEditText.setOnEditorActionListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dialog_url, container);
    }

    // Fires whenever the textfield has an action performed
    // In this case, when the "Done" button is pressed
    // REQUIRES a 'soft keyboard' (virtual keyboard)
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE ||
                actionId == EditorInfo.IME_ACTION_NEXT) {
            // Return input text back to activity through the implemented listener
            OpenURLDialogFragment.OpenURLDialogListener listener = (OpenURLDialogFragment.OpenURLDialogListener) getActivity();
            listener.onFinishEditURLOpenDialog(mEditText.getText().toString());
            // Close the dialog and return back to the parent activity
            dismiss();
            return true;
        }
        return false;
    }

    // 1. Defines the listener interface with a method passing back data result.
    public interface OpenURLDialogListener {
        void onFinishEditURLOpenDialog(String inputText);
    }


}
