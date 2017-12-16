package test.smartcards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Topbar entfernen
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 2 Parameter übergeben (int flags, int mask) --> Beide FullScreen zuweisen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // View nach Fullscreen einspielen, sonst Logikproblem
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        // call Logolauncher Class on create
        LogoLauncher logoLauncher = new LogoLauncher();
        logoLauncher.start();  // thread wird gestartet




    }


    private class LogoLauncher extends Thread{
        public void run(){
            try{
                sleep(3000);

            }catch(InterruptedException e){
                e.printStackTrace();
            }

            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
            startActivity(intent);
            SplashScreen.this.finish();
        }

    }
}
