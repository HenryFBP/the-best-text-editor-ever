package cooldomainname.com.the_best_text_editor_ever.SyntaxHighlighting;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/***
 * Utility class for {@link SyntaxElement}s.
 */
@SuppressWarnings("unchecked")
public class SEUtils {

    public static final HashMap<String, Integer> javaColors = new HashMap<String, Integer>() {{
        put("keyword", Color.MAGENTA);
        put("primitive", Color.BLUE);
        put("type", Color.GREEN);
    }};
    public static final List<SyntaxElement> javaSyntaxElements = (List<SyntaxElement>) addMixed(
            applyColor(javaColors.get("keyword"),
                    "new", "public", "private", "static", "volatile", "synchronized", "do", "while", "case", "class", "extends", "return", "implements"),

            applyColor(javaColors.get("primitive"),
                    "int", "void", "char", "boolean", "byte", "float", "double"),

            applyColor(javaColors.get("type"),
                    "String", "Object", "Integer", "Double", "Float", "Byte")
    );

    /***
     * Apply a single color to many keywords to produce a list of {@link SyntaxElement}s.
     * @param color The color all keywords should be.
     * @param keywords The list of keywords.
     * @return
     */
    public static List<SyntaxElement> applyColor(Integer color, String... keywords) {
        List<SyntaxElement> ret = new ArrayList<>();

        for (String s : keywords) {
            ret.add(new SyntaxElement(s, color));
        }

        return ret;
    }

    /**
     * Given a mixed list of
     *
     * @param args
     * @return
     */
    public static List<?> addMixed(Object... args) {

        List<Object> ret = new ArrayList<>();

        for (Object o : args) {
            if (o instanceof Collection) {
                ret.addAll((Collection) o);
            } else {
                ret.add(o);
            }
        }

        return ret;
    }

}
