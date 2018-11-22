package cooldomainname.com.the_best_text_editor_ever;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    /***
     * Get a TextBuffer from a file.
     * @param path The file.
     * @return a TextBuffer.
     */
    public static TextBuffer fromFile(Path path) {
        TextBuffer textBuffer = new TextBuffer();

        textBuffer.add("Not implemented.");

        return textBuffer;
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
