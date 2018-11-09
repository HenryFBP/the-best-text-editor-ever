package cooldomainname.com.the_best_text_editor_ever;


import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity {

    spinner1 = (Spinner) findViewById(R.id.spinner1);
    spinner1.setOnItemSelectedListener(this);
    List<String> menu = new ArrayList<String>();
    menu.add("Save");
    menu.add("Rename");


    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, menu);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner1.setAdapter(adapter);


    spinner2 = (Spinner) findViewById(R.id.spinner2);
    spinner2.setOnItemSelectedListener(this);
    List<String> edit = new ArrayList<String>();
    edit.add("Cut");
    edit.add("Select");
    edit.add("Move");


    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, edit);
    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner_2.setAdapter(adapter2);



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        // TODO Auto-generated method stub
        Toast.makeText(this, "YOUR SELECTION IS : " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub

    }


}
