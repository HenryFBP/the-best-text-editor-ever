package cooldomainname.com.the_best_text_editor_ever;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class TextEditActivity extends AppCompatActivity {

    /**
     * Actions you can perform on a file.
     */
    private List<String> listFileActions = Arrays.asList("save", "rename");

    /**
     * Actions you can perform on text.
     */
    private List<String> listTextActions = Arrays.asList("cut", "select", "move");


    /**
     * When this Activity is created.
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_edit);

        Spinner spinnerFileActions = findViewById(R.id.spinnerFileActions);
        ArrayAdapter<String> adapterFileActions = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listFileActions);
        adapterFileActions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFileActions.setAdapter(adapterFileActions);

        Spinner spinnerTextActions = findViewById(R.id.spinnerTextActions);
        ArrayAdapter<String> adapterTextActions = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listTextActions);
        adapterTextActions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTextActions.setAdapter(adapterTextActions);

        spinnerFileActions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), String.format("Your file action selection is '%s'.", parent.getItemAtPosition(position).toString()), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        spinnerTextActions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), String.format("Your text editing selection is '%s'.", parent.getItemAtPosition(position).toString()), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

    }


}
