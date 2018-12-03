package cooldomainname.com.the_best_text_editor_ever.DialogFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

public class SaveFileDialogFragment extends FileDialogFragment implements TextView.OnEditorActionListener {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        // Setup a callback when the "Done" button is pressed on keyboard
        mEditText.setOnEditorActionListener(this);
    }


    // Fires whenever the textfield has an action performed
    // In this case, when the "Done" button is pressed
    // REQUIRES a 'soft keyboard' (virtual keyboard)
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE ||
                actionId == EditorInfo.IME_ACTION_NEXT) {
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
