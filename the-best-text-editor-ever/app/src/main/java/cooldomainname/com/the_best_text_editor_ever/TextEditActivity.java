package cooldomainname.com.the_best_text_editor_ever;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import static cooldomainname.com.the_best_text_editor_ever.Library.toastLong;

public class TextEditActivity extends AppCompatActivity implements SaveFileDialogFragment.SaveFileDialogListener {

    /***
     * The TextBuffer that stores our text.
     */
    private TextBuffer textBuffer;

    /**
     * Actions you can perform on a file.
     */
    private List<String> listFileActions = Arrays.asList("", "save", "rename", "run tests");

    /**
     * Actions you can perform on text.
     */
    private List<String> listTextActions = Arrays.asList("", "cut", "select", "move");

    private EditText editText;

    /**
     * We want to open the 'save file' dialog.
     */
    private void openSaveFileDialog() {
        FragmentManager fm = getSupportFragmentManager();

        SaveFileDialogFragment saveFileDialogFragment = new SaveFileDialogFragment();

        saveFileDialogFragment.show(fm, "title");
    }

    /**
     * When this Activity is created.
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_edit);

        // If we have a TextBuffer left over, use it.
        if (savedInstanceState != null) {

            if (savedInstanceState.get("TextBuffer") != null) {
                this.textBuffer = (TextBuffer) savedInstanceState.get("TextBuffer");
            }

        }

        // Our EditText element that has a bunch of text.
        editText = findViewById(R.id.editTextEditor);

        // Spinner for actions that can be performed on a file.
        final BetterSpinner spinnerFileActions = findViewById(R.id.spinnerFileActions);
        ArrayAdapter<String> adapterFileActions = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listFileActions);
        adapterFileActions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFileActions.setAdapter(adapterFileActions);

        spinnerFileActions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //Prevents initialization from triggering this {@link ArrayAdapter.onItemSelected} event.
                if (spinnerFileActions.initialized) {

                    String selection = ((String) parent.getItemAtPosition(position));

                    Toast.makeText(getApplicationContext(), String.format("Your file action selection is '%s'.", selection), Toast.LENGTH_SHORT).show();

                    switch (selection.toLowerCase()) {
                        case "save": {
                            openSaveFileDialog();
                            break;
                        }

                        case "rename": {
                            break;
                        }

                        case "run tests": {
                            testEditTextFileSaving();
                            toastLong("Tests worked. Woo!", getApplicationContext());
                        }

                        default: {
                            break;
                        }
                    }
                } else {
                    spinnerFileActions.initialized = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        // Spinner for actions that can be performed on text.
        final BetterSpinner spinnerTextActions = findViewById(R.id.spinnerTextActions);
        ArrayAdapter<String> adapterTextActions = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listTextActions);
        adapterTextActions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTextActions.setAdapter(adapterTextActions);

        spinnerTextActions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //Prevents initialization from triggering this {@link ArrayAdapter.onItemSelected} event.
                if (spinnerTextActions.initialized) {
                    String selection = ((String) parent.getItemAtPosition(position));

                    Toast.makeText(getApplicationContext(), String.format("Your text editing selection is '%s'.", selection), Toast.LENGTH_SHORT).show();

                    switch (selection.toLowerCase()) {
                        case "cut": {
                            break;
                        }
                        case "select": {
                            break;
                        }
                        case "move": {
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                } else {
                    spinnerTextActions.initialized = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

    }


    /***
     * When the user wishes to save the file.
     * @param inputText The text that was input.
     */
    // 3. This method is invoked in the activity when the listener is triggered
    // Access the data result passed to the activity here
    @Override
    public void onFinishEditDialog(String inputText) {
//        toastLong(String.format("We should save the file to '%s'.", inputText), getApplicationContext());

        File file = new File(getApplicationContext().getFilesDir(), inputText);

        TextBuffer textBuffer = TextBuffer.fromEditText(editText);

        try {
            textBuffer.saveTo(file);
            Log.i(this.getClass().getSimpleName(), String.format("Saving file to '%s.'", file.getAbsolutePath()));
        } catch (IOException e) {
            e.printStackTrace();
            toastLong("Couldn't save file.", getApplicationContext());
        }
    }

    /***
     * Test that we can save a file to disk given an {@link EditText} object with text.
     */
    public void testEditTextFileSaving() {

        EditText editText = new EditText(getApplicationContext());
        CharSequence coolDelim = "WEGHDFGBDHGDFGHBDG";

        CharSequence coolText = ("I am a really cool snippet of text.\n" +
                "Also, I'm separated by something rad.\n" +
                "Don't you still like edge cases?\n" +
                "\n" +
                "I do.\n" +
                "F").replace("\n", coolDelim);

        editText.append(coolText);

        // Hide it.
        editText.setVisibility(View.GONE);

        // Make a file.
        BetterFile file = new BetterFile(getApplicationContext().getFilesDir(), "pls_delet_kthxbai.txt").deleteIfExists();

        // Get the text from the EditText.
        TextBuffer textBuffer = TextBuffer.fromEditText(editText, coolDelim);

        // Try to save the file.
        try {
            textBuffer.saveTo(file);
        } catch (IOException e) {
            e.printStackTrace();

            // We should be able to save the file.
            throw new AssertionError("Something happened while saving file >:(");
        }

        if ((!file.exists())) throw new AssertionError(); // File should exist after saving it.

        // Next, to read it back.
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(file));

            // First char should be 'I'.
            if ((reader.read() != 'I')) throw new AssertionError();

            CharSequence theRest = reader.readLine();

            // As we have no newlines (We replaced it with gobbledygook),
            // we should have consumed the rest of the input by asking for one line.
            if (reader.ready()) throw new AssertionError();

            // How many times the delimiter occurs.
            int delimOccurrences = ((String) theRest).split((String) coolDelim).length;

            // We should have exactly 5 delimiters, plus one for splitting the list.
            if (delimOccurrences != (5 + 1))
                throw new AssertionError(String.format("%d != %d", delimOccurrences, (5 + 1)));

            // Last character should be 'F'.
            if (theRest.charAt(theRest.length() - 1) != 'F') throw new AssertionError();

            // Second-to-last character should be the end of our delimiter sequence.
            if (theRest.charAt(theRest.length() - 2) != coolDelim.charAt(coolDelim.length() - 1))
                throw new AssertionError();

        } catch (FileNotFoundException e) {
            e.printStackTrace();

            // We should be able to open the file.
            throw new AssertionError("Can't open da file?!");
        } catch (IOException e) {
            e.printStackTrace();
            throw new AssertionError("YA PIPE IS BROKEN!!!");
        }

    }
}
