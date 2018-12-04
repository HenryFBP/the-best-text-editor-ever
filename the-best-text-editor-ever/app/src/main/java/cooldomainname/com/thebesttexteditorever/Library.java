package cooldomainname.com.thebesttexteditorever;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import com.google.common.base.Throwables;

public class Library {

    public static final CharSequence unsafeFileCharacters = "|\\?*<\":>+[]/'";

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

    public static void showKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        InputMethodManager methodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        assert methodManager != null && view != null;
        methodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }


    /***
     * Make a string a safe file name.
     */
    public static String fileSafeString(String unsafe) {

        for (Character c : unsafeFileCharacters.toString().toCharArray()) {
            unsafe = unsafe.replace(c.toString(), "");
        }

        return unsafe;
    }

    public static void logException(Exception e) {
        Log.e(e.getClass().getName(), e.getMessage(), e.getCause());
    }

    public static void logExceptionStacktrace(Exception e) {
        Log.e(e.getClass().getName(), Throwables.getStackTraceAsString(e), e.getCause());
    }

}
