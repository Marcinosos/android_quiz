package test.smartcards;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;

public class karteikarteErstellen extends AppCompatActivity {

    EditText vorderseiteEditText,rueckseiteEditText;
    String title;
    Formatter x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.karteikarte_erstellen_activity);

        //Vorder und Rückseite EditText
        vorderseiteEditText = (EditText)findViewById(R.id.vorderseiteEditText);
        rueckseiteEditText  = (EditText)findViewById(R.id.rueckseiteEditText);


        //hasExtra() prüft ob der karteikartenErstellenIntent extra Informationen unter dem Namen "STAPELNAME" hat, die wir auslesen können.
        if (getIntent().hasExtra("test.smartcards.STAPELNAME")){
            TextView tv = (TextView) findViewById(R.id.karteikartenName);
            //Die übergebene Information wird in der Variablen title gespeichert
            title = getIntent().getExtras().getString("test.smartcards.STAPELNAME");
            tv.setText("Stapel: " + title);
        }

        Button karteikarteSpeichernButton = (Button) findViewById(R.id.karteikarteSpeichernButton);
        karteikarteSpeichernButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filepath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/smartCards/" + title;//hier muss noch der Ordner hin
                String frage = vorderseiteEditText.getText().toString();
                String antwort = rueckseiteEditText.getText().toString();
                if (vorderseiteEditText.getText().toString().length()>0 && rueckseiteEditText.getText().toString().length()>0){
                    File karteikarte = new File (filepath + "/" + frage + ".txt");
                    try {
                        //OutputStream outputStream = new FileOutputStream(karteikarte);
                        //outputStream.write(vorderseiteEditText.toString().getBytes());
                        //outputStream.close();

                        x = new Formatter(karteikarte);
                        x.format("%s", frage + "|" + antwort);
                        x.close();

                        vorderseiteEditText.setText(null);
                        rueckseiteEditText.setText(null);
                        Toast.makeText(getApplicationContext(), "Karteikarte gespeichert", Toast.LENGTH_SHORT).show();
                    }
                    catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

              }
               else {
                    Toast.makeText(getApplicationContext(), "Kein Text", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //Abbrechen Button
        final Button abbrechenButton = (Button) findViewById(R.id.abbrechenButton);
        abbrechenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abbrechenIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(abbrechenIntent);
            }
        });


    }
}
