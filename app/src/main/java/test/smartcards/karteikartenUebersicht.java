package test.smartcards;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class karteikartenUebersicht extends ListActivity {
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.karteikarten_uebersicht_activity);

        // Use the current directory as title
        path = "/sdcard/smartCards";
        if (getIntent().hasExtra("path")) {
            path = getIntent().getStringExtra("path");
        }

        // Read all files sorted into the values-array
        List values = new ArrayList();
        File dir = new File(path);

        String[] list = dir.list();
        if (list != null) {
            for (String file : list) {
                if (!file.startsWith(".")) {
                    values.add(file);
                }
            }
        }
        Collections.sort(values);

        // Put the data into the list
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_2, android.R.id.text1, values);
        setListAdapter(adapter);

    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String stapelName = (String) getListAdapter().getItem(position);
        ;
        String filename = (String) getListAdapter().getItem(position);

        if (path.endsWith(File.separator)) {
            filename = path + filename;
        } else {
            filename = path + File.separator + filename;
        }
        if (!new File(filename).isDirectory()) {
            Intent karteikarteBearbeitenIntent = new Intent(getApplicationContext(), karteikarteBearbeiten.class);
            karteikarteBearbeitenIntent.putExtra("test.smartcards.STAPELNAME", stapelName);
            startActivity(karteikarteBearbeitenIntent);
        } else {
            Toast.makeText(this, filename + " is not a directory", Toast.LENGTH_LONG).show();
        }

    }
}
