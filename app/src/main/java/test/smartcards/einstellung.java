package test.smartcards;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;

public class einstellung extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.einstellung_activity);

        //Backup Button
        final Button backupButton = (Button)findViewById(R.id.backupButton);
        backupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backupIntent = new Intent(getApplicationContext(), backup.class);
                startActivity(backupIntent);
            }
        });

        //Prüfen ob Backup Ordner existiert, erst dann die Buttons für Dropbox und Firebase implementieren
        //Backup
        final File backupOrdner = new File(Environment.getExternalStorageDirectory(),
                "/smartCards/.backup");
            //Dropbox Button
            final ImageButton dropboxButton = (ImageButton) findViewById(R.id.dropboxButton);
            dropboxButton.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     if (!backupOrdner.exists()) {
                         Toast.makeText(getApplicationContext(), "Klicken Sie erst auf Backup erstellen.", Toast.LENGTH_SHORT).show();
                     } else {
                         Intent dropboxIntent = new Intent(getApplicationContext(), dropbox.class);
                         startActivity(dropboxIntent);
                     }
                 }
             });
            //Firebase Button
            final ImageButton firebaseButton = (ImageButton) findViewById(R.id.firebaseButton);
            firebaseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!backupOrdner.exists()) {
                        Toast.makeText(getApplicationContext(), "Klicken Sie erst auf Backup erstellen.", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent firebaseIntent = new Intent(getApplicationContext(), firebase.class);
                        startActivity(firebaseIntent);
                    }
                }
            });
    }
}
