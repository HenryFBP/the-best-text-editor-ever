package cooldomainname.com.the_best_text_editor_ever.SyntaxHighlighting;

import android.graphics.Color;

public class SyntaxElement {

    public String identifier;
    public int color;

    public SyntaxElement(String identifier, Integer color) {
        this.identifier = identifier;
        this.color = color;
    }
}
