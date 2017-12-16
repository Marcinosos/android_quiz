package test.smartcards;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class karteikarteBearbeiten extends AppCompatActivity {

    String karteikartenName = "";
    //.txt abschneiden

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karteikarte_bearbeiten);

        //Prüft ob es einen hasExtra gibt und speichert ihn in die Variable Stapelname.
        // Stapelname wurde von der vorherigen Activity hierher übertragen.
        if (getIntent().hasExtra("test.smartcards.STAPELNAME")){
            TextView tv = (TextView) findViewById(R.id.karteikartenName);
            //Die übergebene Information wird in der Variablen title gespeichert
            karteikartenName = getIntent().getExtras().getString("test.smartcards.STAPELNAME");
            tv.setText(""+karteikartenName);
        }
    }
    //Holt String aus der .txt Datei und Speichert sie in eine Variable



}
