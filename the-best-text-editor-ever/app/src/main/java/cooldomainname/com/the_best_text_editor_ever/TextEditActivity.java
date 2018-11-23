package cooldomainname.com.the_best_text_editor_ever;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class TextEditActivity extends AppCompatActivity implements SaveFileDialogFragment.SaveFileDialogListener {

    /***
     * The TextBuffer that stores our text.
     */
    private TextBuffer textBuffer;

    /**
     * Actions you can perform on a file.
     */
    private List<String> listFileActions = Arrays.asList("", "save", "rename");

    /**
     * Actions you can perform on text.
     */
    private List<String> listTextActions = Arrays.asList("", "cut", "select", "move");

    /**
     * We want to save the contents of our {@link TextEditActivity#textBuffer} to a file.
     */
    private void saveFile() {
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
                            saveFile();
                            break;
                        }

                        case "rename": {
                            break;
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
        Toast.makeText(this, String.format("We should save the file to '%s'.", inputText), Toast.LENGTH_SHORT).show();
    }
}
