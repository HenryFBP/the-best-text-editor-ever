package cooldomainname.com.thebesttexteditorever.syntaxhighlighting;

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
     * @return A list of {@link SyntaxElement}s that all have a color of `color`.
     */
    public static List<SyntaxElement> applyColor(Integer color, String... keywords) {
        List<SyntaxElement> ret = new ArrayList<>();

        for (String s : keywords) {
            ret.add(new SyntaxElement(s, color));
        }

        return ret;
    }

    /**
     * Given a mixed list of either Objects that do NOT extend {@link Collection}, or {@link Object}s that DO extend {@link Collection}, return a flattened list of those objects.
     * <p>
     * If an {@link Object} extends {@link Collection}, it will be flattened (not recursively), and all of its elements will be added to the array that will be returned.
     * <p>
     * If it doesn't extend {@link Collection}, it will just be added to the array that will be returned.
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
