package cooldomainname.com.the_best_text_editor_ever;

import android.widget.EditText;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A buffer of text.
 */
public class TextBuffer {

    /**
     * What is each line separated by?
     */
    private CharSequence delimiter = System.getProperty("line.separator");

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

    public TextBuffer(List<String> lines) {
        for (CharSequence line : lines) {
            this.add(line);
        }
    }

    /***
     * Given an {@link EditText} element, return a TextBuffer from it.
     */
    public static TextBuffer fromTextEdit(EditText editText) {
        return fromDelimitedString(editText.toString(), System.getProperty("line.separator"));
    }

    /***
     * Given a string demarcated with delimiters, generate a {@link TextBuffer} from it.
     */
    public static TextBuffer fromDelimitedString(CharSequence string, CharSequence delimiter) {
        // Split the string by the delimiter.
        //
        // We use a limit of -1 here to force a delimiter at the end of the string to yield an empty string.
        List<String> lines = Arrays.asList(string.toString().split((String) delimiter, -1));

        return new TextBuffer(lines);
    }


    /***
     * Get a TextBuffer from a file.
     * Uses the system's newline (i.e. '\n' or "\r\n")
     * @param file The file.
     * @return a TextBuffer.
     */
    public static TextBuffer fromFile(File file) throws IOException {
        return fromFile(file, System.getProperty("line.separator"));
    }

    /***
     * Get a TextBuffer from a file.
     * @param file The file.
     * @param delimiter The delimiter to indicate a new line.
     * @return a TextBuffer.
     */
    public static TextBuffer fromFile(File file, CharSequence delimiter) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(file));

        StringBuilder builder = new StringBuilder();

        // Read all characters until file is empty.
        //
        // The reason we do this instead of reader.readLine() is to capture '\n' and "\r\n".
        //
        // The reader.readLine() will normally discard those sequences.
        while (reader.ready()) {
            builder.append(((char) reader.read()));
        }

        // Create a TextBuffer from the lines, and set its delimiter.
        return fromDelimitedString(builder.toString(), delimiter).setDelimiter(delimiter);
    }

    /***
     * Save this TextBuffer to a file.
     * @param file What file to saveTo it to?
     */
    public void saveTo(File file) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        for (int i = 0; i < this.lines.size(); i++) {

            CharSequence charSequence = this.lines.get(i);

            writer.write(String.valueOf(charSequence));

            if (i < this.lines.size() - 1) {
                writer.write(String.valueOf(this.delimiter));
            }
        }

        writer.close();
    }

    public TextBuffer setDelimiter(CharSequence delimiter) {
        this.delimiter = delimiter;
        return this;
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

    public CharSequence getLine(int location) {
        return this.lines.get(location);
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
