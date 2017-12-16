package test.smartcards;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    //Requestcode für MainActivity
    final int REQUEST_CODE_EXTERNAL_STORAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Prüft ob Speicherrechte gegeben sind
        if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
            //Speicherrechte bereits vorhanden, dann createAppFolder() ausführen
            createAppFolder();
        }
        else{
            //Speicherrechte nicht vorhanden
            //Zusatzinformationen warum Speicherrechte benötigt werden.
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_EXTERNAL_STORAGE);
        }

        //Button zur Quiz Activity
        final Button lernenButton = (Button) findViewById(R.id.lernenButton);
        lernenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lernenIntent = new Intent(getApplicationContext(), stapelUebersichtQuiz.class);
                startActivity(lernenIntent);
            }
        });

        //Buttons zur StapelUebersicht
        final Button stapelBearbeitenButton = (Button) findViewById(R.id.stapelUebersichtButton);
        stapelBearbeitenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stapelUebersichtIntent = new Intent(getApplicationContext(), stapelUebersicht.class);
                startActivity(stapelUebersichtIntent);
            }
        });

        //Button zur Stapel erstellen Activity
        final Button stapelErstellenButton = (Button) findViewById(R.id.stapelErstellenButton);
        stapelErstellenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stapelErstellenIntent = new Intent(getApplicationContext(), stapelErstellen.class);
                startActivity(stapelErstellenIntent);
            }
        });
        //Button zur Einstellung Activity
        final Button einstellungButton = (Button) findViewById(R.id.einstellungButton);
        einstellungButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent einstellungsIntent = new Intent(getApplicationContext(), einstellung.class);
                startActivity(einstellungsIntent);
            }
        });

    }

    private void createAppFolder() {
        //App Ordner wird erstellt wenn nicht vorhanden
        File applicationOrdner = new File(Environment.getExternalStorageDirectory(),
                "smartCards");
        if (!applicationOrdner.exists()) {
            applicationOrdner.mkdirs();
            boolean wasSuccessful = applicationOrdner.mkdirs();
            if (wasSuccessful) {
                Toast.makeText(getApplicationContext(), "App Ordner wurde erstellt!", Toast.LENGTH_SHORT).show();
            }
        }
    }
        @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == REQUEST_CODE_EXTERNAL_STORAGE && grantResults.length >0  &&grantResults[0] == PackageManager.PERMISSION_GRANTED){
            createAppFolder();
        }
        else {
            Toast.makeText(this, "Die App benötigt Schreibrechte um richtig zu funktionieren", Toast.LENGTH_SHORT).show();
        }

    }
}
