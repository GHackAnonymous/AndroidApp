package com.example.ghackanonymous.gato;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Vibrator;


// refe vibra: http://www.forosdelweb.com/f165/aporte-android-vibraciones-nuestras-app-s-963194/
//refe log: http://www.androidcurso.com/index.php/tutoriales-android-fundamentos/31-unidad-1-vision-general-y-entorno-de-desarrollo/470-depurar-con-mensajes-log

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button boton;
    MediaPlayer mp;
    NotificationManager mgr;
    Notification note;
    Vibrator vibra;
    long [] patron = {0, 500, 300, 1000, 500};
    int notificationID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton = (Button) this.findViewById(R.id.button);
        mp = MediaPlayer.create(this, R.raw.maullido);


        mgr = (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
        note =new Notification(R.drawable.gato,"Gsto maullando",
                System.currentTimeMillis());


        vibra = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        boton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        mp.start();
        Log.i("Gato","Reproduzco sonido");
        this.vibra.vibrate(patron,3);
        Log.i("Gato","Vibro con patron");

        mgr.notify(notificationID, note);
        Log.i("Gsto","Lanzo notificacion");
    }
}
