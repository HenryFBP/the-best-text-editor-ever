package cooldomainname.com.the_best_text_editor_ever;

import android.content.Context;
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

}
