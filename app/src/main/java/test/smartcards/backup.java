package test.smartcards;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**Backup erstellen erstellt eine zip Datei von einem ausgewählten Ordner,
 * Zip Dateien sollen in /smartCards/backup gespeichert werden
 * diese zip Datei lässt sich in Dropbox oder Firebase hochladen.
 */

public class backup extends ListActivity {

    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backup);

        //Backupdordner wird erstellt wenn nicht vorhanden
        File backupOrdner = new File(Environment.getExternalStorageDirectory(),
                "/smartCards/.backup");
        if (!backupOrdner.exists()) {
            backupOrdner.mkdirs();
            Toast.makeText(getApplicationContext(), "Backup Ordner wurde erstellt!", Toast.LENGTH_SHORT).show();
            }

        path = "/sdcard/smartCards";

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

    //Zippen
    private static void zipFolder(String inputFolderPath, String outZipPath) {
        try {
            FileOutputStream fos = new FileOutputStream(outZipPath);
            ZipOutputStream zos = new ZipOutputStream(fos);
            File srcFile = new File(inputFolderPath);
            File[] files = srcFile.listFiles();
            Log.d("", "Zip directory: " + srcFile.getName());
            for (int i = 0; i < files.length; i++) {
                Log.d("", "Adding file: " + files[i].getName());
                byte[] buffer = new byte[1024];
                FileInputStream fis = new FileInputStream(files[i]);
                zos.putNextEntry(new ZipEntry(files[i].getName()));
                int length;
                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }
                zos.closeEntry();
                fis.close();
            }
            zos.close();
        } catch (IOException ioe) {
            Log.e("", ioe.getMessage());
        }
    }

    @Override
    /**Beim Klick auf den den Stapel, zip-Datei erstellen**/
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String filename = (String) getListAdapter().getItem(position);
        String itemPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/smartCards/"+filename;
        String outputPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/smartCards/.backup/"+filename;
        String backupMessage = "Backup vom Stapel: " +filename +" wurde erstellt.";
        Toast.makeText(getApplicationContext(), backupMessage, Toast.LENGTH_SHORT).show();
        /**Zippen**/
        zipFolder(itemPath,outputPath );
        }
    }






