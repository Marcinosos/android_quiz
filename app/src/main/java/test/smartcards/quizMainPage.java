package test.smartcards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import static test.smartcards.R.id.antwortAnsehenButton;
import static test.smartcards.R.id.frage;
import static test.smartcards.R.id.karteiKartenNummer;
import static test.smartcards.R.id.stapelName;

public class quizMainPage extends AppCompatActivity {

    private static ArrayList<String> smartcards;
    private static int count = 0;
    private static String ganzerPfad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_main_page);




        //hasExtra() prüft ob der quizMainpageIntent extra Informationen unter dem Namen "path" hat, die wir auslesen kˆnnen.

        TextView tv_Stapelname = (TextView) findViewById(stapelName); //StapelName ist die ID des Textviews (in XML)
        //Die ¸bergebene Information wird in der Variablen stapelTitle gespeichert
        ganzerPfad = getIntent().getExtras().getString("rueberZuQuizMain");
        String kurzerPfad = ganzerPfad.substring(ganzerPfad.lastIndexOf('/')+1);
        tv_Stapelname.setText("Stapel: " + kurzerPfad);
        //stapelname0 -> Textview Wo der Stapelname steht


        File f = new File(ganzerPfad);
        String[] ar = f.list();
        smartcards = new ArrayList<String>();

        for(int i = 0; i < ar.length; i++){
            File f2 = new File(ganzerPfad + "/" + ar[i]);

            try {
                Scanner scanner = new Scanner(f2);
                scanner.useDelimiter("\\s*\\|\\s*");
                String card = scanner.next() + "|" + scanner.next();
                smartcards.add(card);
                scanner.close();
                count++;
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        // Zufallszahl generieren
        int number = (int)(Math.round((Math.random() * smartcards.size())));
        if(number != 0 && number == smartcards.size())
            number--;
        String input = smartcards.get(number);
        Scanner s = new Scanner(input).useDelimiter("\\s*\\|\\s*");
        String question = s.next(); //Frage
        String answer = s.next();  //Antwort
        s.close();

        TextView tv_Frage = (TextView) findViewById(frage);//frage ist die ID vom TextView Frage in XML
        tv_Frage.setText(question);

        TextView tv_anzahl = (TextView) findViewById(karteiKartenNummer);//frage ist die ID vom TextView Frage in XML
        tv_anzahl.setText("noch " + count + " cards");
        count = 0; //nach der Anzeige der Anzahl der cards muss count wieder auf 0 gesetzt werden

        final Button antwortAnsehen = (Button) findViewById(antwortAnsehenButton);
        antwortAnsehen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent zuFrageAntwort = new Intent(getApplicationContext(), quiz_frage_antwort.class);
                startActivity(zuFrageAntwort);
            }
        });


    }
}
