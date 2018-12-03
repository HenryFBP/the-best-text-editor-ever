package cooldomainname.com.the_best_text_editor_ever;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

public class Library {

    public static void toastLong(CharSequence charSequence, Context c) {
        toast(charSequence, c, Toast.LENGTH_LONG);
    }

    public static void toastShort(CharSequence charSequence, Context c) {
        toast(charSequence, c, Toast.LENGTH_SHORT);
    }

    public static void toast(CharSequence charSequence, Context c, int length) {
        Toast.makeText(c, charSequence.toString(), length).show();
    }

    /**
     * Stolen from https://stackoverflow.com/questions/1109022/close-hide-the-android-soft-keyboard/17789187#17789187
     */
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }

        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


}
