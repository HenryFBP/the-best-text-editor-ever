package cooldomainname.com.the_best_text_editor_ever;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;

/**
 * A general fragment for doing...something with a file.
 */
public class FileDialogFragment extends DialogFragment {

    static String DEFAULT_TITLE = "Filename";

    /* The Filename. */
    EditText mEditText;

    public FileDialogFragment() {
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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dialog_filename, container);
    }


}
