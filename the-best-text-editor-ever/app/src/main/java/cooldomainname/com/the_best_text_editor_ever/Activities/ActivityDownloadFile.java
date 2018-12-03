package cooldomainname.com.the_best_text_editor_ever.Activities;

import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import cooldomainname.com.the_best_text_editor_ever.R;

import static cooldomainname.com.the_best_text_editor_ever.Library.hideKeyboard;
import static cooldomainname.com.the_best_text_editor_ever.Library.toastLong;

public class ActivityDownloadFile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_file);

        // Set up UI elements
        TextView textViewTitle = findViewById(R.id.textViewTitle);
        EditText editTextFilename = findViewById(R.id.editTextFilename);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        TextView textViewStatus = findViewById(R.id.textViewStatus);

        // When they do something with the keyboard,
        editTextFilename.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //If it's 'done' or 'next',
                if (actionId == EditorInfo.IME_ACTION_DONE ||
                        actionId == EditorInfo.IME_ACTION_NEXT) {

                    toastLong("ok well download it urself then :P", getApplicationContext());

                    hideKeyboard(ActivityDownloadFile.this);

                    return true;
                }
                return false;
            }
        });
    }
}