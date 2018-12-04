package cooldomainname.com.thebesttexteditorever.DialogFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.*;
import cooldomainname.com.thebesttexteditorever.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A general fragment for doing...something with a file.
 */
public class FileDialogFragment extends DialogFragment {

    static String DEFAULT_TITLE = "Filename";

    /* The Filename. */
    EditText mEditText;

    /* A ListView of all files we can open/save/overwrite. */
    ListView mListView;

    /* Title. */
    TextView mTextView;

    /* The title of the dialog fragment. */


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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get field from view
        mEditText = view.findViewById(R.id.editTextFilename);
        mListView = view.findViewById(R.id.listViewFiles);
        mTextView = view.findViewById(R.id.textViewTitle);

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

        // List of all files in our directory.
        List<File> files = Arrays.asList(getContext().getFilesDir().listFiles());

        List<String> filenames = new ArrayList<>();

        for (File f : files) {
            filenames.add(f.getName());
        }

        /*
        ArrayAdapter that has a list of our filenames.
        TODO we can't just have a dumb list of strings forever.
             For directories, etc. we will need a custom ListAdapter and custom layout.
        */
        ArrayAdapter arrayAdapterFiles = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_list_item_1, filenames);

        mListView.setAdapter(arrayAdapterFiles);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mEditText.setText((CharSequence) parent.getItemAtPosition(position)); //Set the editText to the filename.
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dialog_file, container);
    }


}
