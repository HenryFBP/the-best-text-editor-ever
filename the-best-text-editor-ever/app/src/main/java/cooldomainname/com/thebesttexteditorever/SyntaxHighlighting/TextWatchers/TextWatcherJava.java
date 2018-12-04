package cooldomainname.com.thebesttexteditorever.SyntaxHighlighting.TextWatchers;

import android.text.Editable;
import android.text.Spannable;
import android.text.TextWatcher;
import android.text.style.BackgroundColorSpan;
import cooldomainname.com.thebesttexteditorever.SyntaxHighlighting.SEUtils;
import cooldomainname.com.thebesttexteditorever.SyntaxHighlighting.SyntaxElement;

public class TextWatcherJava implements TextWatcher {

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String string = s.toString();

        // For all SyntaxElements,
        for (SyntaxElement element : SEUtils.javaSyntaxElements) {

            int index = string.indexOf(element.identifier);

            // While we still find occurrences of our syntax element,
            while (index >= 0) {

                // Highlight it.
                s.setSpan(
                        new BackgroundColorSpan(element.color),
                        index,
                        index + element.identifier.length(),
                        Spannable.SPAN_COMPOSING);

                // Get next occurrence.
                index = string.indexOf(element.identifier, index + 1);
            }
        }


    }
}
