package cooldomainname.com.the_best_text_editor_ever;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

public class OpenFileDialogFragment extends FileDialogFragment implements TextView.OnEditorActionListener {


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        // 2. Setup a callback when the "Done" button is pressed on keyboard
        mEditText.setOnEditorActionListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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
            OpenFileDialogFragment.OpenFileDialogListener listener = (OpenFileDialogFragment.OpenFileDialogListener) getActivity();
            listener.onFinishEditOpenDialog(mEditText.getText().toString());
            // Close the dialog and return back to the parent activity
            dismiss();
            return true;
        }
        return false;
    }

    // 1. Defines the listener interface with a method passing back data result.
    public interface OpenFileDialogListener {
        void onFinishEditOpenDialog(String inputText);
    }

}
