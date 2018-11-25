package cooldomainname.com.the_best_text_editor_ever;

import android.graphics.Color;
import android.text.Editable;
import android.text.Spannable;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;

public class TextWatcherPorkdown implements TextWatcher {
    final String DELICIOUS = "PORK";

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {

        String string = s.toString();

        int index = string.indexOf(DELICIOUS);

        // While we still find occurrences of our syntax element,
        while (index >= 0) {

            // Highlight it.
            s.setSpan(
                    new ForegroundColorSpan(Color.CYAN),
                    index,
                    index + DELICIOUS.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            // Get next occurrence.
            index = string.indexOf(DELICIOUS, index + 1);
        }
    }
}
