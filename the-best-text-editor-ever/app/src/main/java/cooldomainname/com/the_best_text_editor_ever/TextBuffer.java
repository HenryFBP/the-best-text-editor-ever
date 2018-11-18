package cooldomainname.com.the_best_text_editor_ever;

import kotlin.NotImplementedError;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A buffer of text.
 */
public class TextBuffer {

    /**
     * What is each line separated by?
     */
    public CharSequence delimiter = "\n";
    /**
     * The lines.
     */
    private ArrayList<CharSequence> lines = new ArrayList<>();

    public TextBuffer() {
    }

    public TextBuffer(CharSequence... lines) {
        for (CharSequence line : lines) {
            this.add(line);
        }
    }

    public TextBuffer setDelimiter(CharSequence delimiter) {
        this.delimiter = delimiter;
        return this;
    }

    /***
     * Save this TextBuffer to a file.
     * @param path Where to save it?
     */
    public void save(Path path) {
        throw new NotImplementedError("Not implemented.");
    }

    /**
     * Adds zero or more lines to this TextBuffer.
     *
     * @param lines Zero or more lines.
     * @return The TextBuffer.
     */
    public TextBuffer add(CharSequence... lines) {

        this.lines.addAll(Arrays.asList(lines));

        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < lines.size(); i++) {

            CharSequence line = this.lines.get(i);
            sb.append(line.toString());

            if (i < (lines.size() - 1)) { // Avoid dangling newline.
                sb.append(delimiter);
            }
        }

        return sb.toString();
    }
}
