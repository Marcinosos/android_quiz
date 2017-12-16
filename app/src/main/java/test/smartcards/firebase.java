package test.smartcards;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**Backup erstellen erstellt eine zip Datei von einem ausgewählten Ordner,
 * Zip Dateien sollen in /smartCards/backup gespeichert werden
 * diese zip Datei lässt sich in Dropbox oder Firebase hochladen.
 */

public class firebase extends ListActivity {

    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);


        path = "/sdcard/smartCards/.backup";

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

    //Hochladen




    @Override
    /**Beim Klick auf den den Stapel, zip-Datei erstellen**/
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String filename = (String) getListAdapter().getItem(position);
        String itemPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/smartCards/.backup/"+filename;
        String backupMessage = "Stapel: " +filename +" wurde erfolgreich hochgeladen.";
        Toast.makeText(getApplicationContext(), backupMessage, Toast.LENGTH_SHORT).show();

    }
}






