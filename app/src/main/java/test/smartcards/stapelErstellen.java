package test.smartcards;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;

public class stapelErstellen extends AppCompatActivity {
    File stapel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stapel_erstellen_activity);

        //Textfeld
        final EditText stapelEditText = (EditText) findViewById(R.id.stapelEditText);
        //speichern button
        Button speichernButton = (Button) findViewById(R.id.karteikarteSpeichernButton);
        speichernButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filepath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/smartCards";
                String stapelName = stapelEditText.getText().toString();
                if (stapelEditText.length() > 0) {
                stapel = new File(filepath, stapelName );
                    if (!stapel.exists()) {
                        stapel.mkdirs();
                        stapelEditText.setText(null);
                        Toast.makeText(getApplicationContext(), "Stapel wurde erstellt!", Toast.LENGTH_SHORT).show();

                        Intent karteikartenErstellenIntent = new Intent(getApplicationContext(), karteikarteErstellen.class);
                        karteikartenErstellenIntent.putExtra("test.smartcards.STAPELNAME", stapelName);
                        startActivity(karteikartenErstellenIntent);
                    }
                      //Prüft ob Stapel bereits existiert, wenn ja, zur karteikarteErstellen.class wechseln
                    else if (stapel.exists()) {
                        Toast.makeText(getApplicationContext(), "Stapel bereits vorhanden", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                    Toast.makeText(getApplicationContext(), "Geben Sie einen Stapelnamen ein", Toast.LENGTH_SHORT).show();

            }
        });
        //Abbrechen Button
        Button abbrechenButton = (Button) findViewById(R.id.abbrechenButton);
        abbrechenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abbrechenIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(abbrechenIntent);
            }
        });
        //Karteikarten anzeigen Button
        Button karteikartenAnzeigenButton = (Button) findViewById(R.id.karteikartenAnzeigenButton);
        karteikartenAnzeigenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent karteikartenAnzeigenIntent = new Intent(getApplicationContext(), karteikartenUebersicht.class);
                startActivity(karteikartenAnzeigenIntent);
            }
        });
    }}

