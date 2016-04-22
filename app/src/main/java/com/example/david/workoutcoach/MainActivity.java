package com.example.david.workoutcoach;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import model.Exercice;
import model.ExerciceDouble;
import model.ExerciceSimple;
import model.Seance;
import model.Serie;
import utils.ExerciceUtils;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mp;
    private MediaPlayer mp2;
    private MediaPlayer mp3;
    private Seance seance;
    private boolean isTimerRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        seance = initialiserSeance();
        mp = MediaPlayer.create(this, R.raw.bip);
        mp2 = MediaPlayer.create(this, R.raw.bip);
        mp3 = MediaPlayer.create(this, R.raw.bip);
        Button chrono = (Button) findViewById(R.id.buttonTimer);
        chrono.setText(String.valueOf(seance.getExercices().get(0).getTpsRepos()));
        chrono.setOnClickListener(new OnClickTimer());
    }

    class OnClickTimer implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if(!isTimerRunning) {
                isTimerRunning = true;
                final Button b = (Button) v;
                List<Exercice> exercices = seance.getExercices();
                Exercice e = exercices.get(0);

                int tpsRepos = ExerciceUtils.executerSerie(exercices, e);

                new CountDownTimer(tpsRepos * 1000, 500) {

                    @Override
                    public void onTick(long millisUntilFinished) {
                        b.setText(String.valueOf(millisUntilFinished / 1000));
                    }

                    @Override
                    public void onFinish() {
                        isTimerRunning = false;
                        finalBip();
                    }
                }.start();
            }
        }
    }

    public void finalBip() {
        mp.start();
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mp2.start();
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mp3.start();
    }

    public Seance initialiserSeance() {

        Serie s1 = new Serie((TextView) findViewById(R.id.serie1));
        Serie s2 = new Serie((TextView) findViewById(R.id.serie2));
        Serie s3 = new Serie((TextView) findViewById(R.id.serie3));
        Serie s4 = new Serie((TextView) findViewById(R.id.serie4));
        Serie s5 = new Serie((TextView) findViewById(R.id.serie5));
        Serie s6 = new Serie((TextView) findViewById(R.id.serie6));

        Exercice e1 = new ExerciceSimple((TextView) findViewById(R.id.exercice1), new LinkedList<>(Arrays.asList(s6, s5, s4, s3, s2, s1)));
        Exercice e2 = new ExerciceSimple((TextView) findViewById(R.id.exercice2), new LinkedList<>(Arrays.asList(s6, s5, s4, s3, s2, s1)));
        Exercice e3 = new ExerciceSimple((TextView) findViewById(R.id.exercice3), new LinkedList<>(Arrays.asList(s6, s5, s4, s3, s2, s1)));
        e3.setTpsReposFinal(180);
        Exercice e4 = new ExerciceDouble((TextView) findViewById(R.id.exercice4), new LinkedList<>(Arrays.asList(s6, s5, s4, s3, s2, s1)), 25);
        e4.setTpsReposFinal(180);
        Exercice e5 = new ExerciceDouble((TextView) findViewById(R.id.exercice5), new LinkedList<>(Arrays.asList(s6, s5, s4, s3, s2, s1)));
        e5.setTpsReposFinal(180);
        Exercice e6 = new ExerciceSimple((TextView) findViewById(R.id.exercice6), new LinkedList<>(Arrays.asList(s6, s5, s4, s3, s2, s1)));
        e6.setTpsReposFinal(90);
        Exercice e7 = new ExerciceSimple((TextView) findViewById(R.id.exercice7), new LinkedList<>(Arrays.asList(s6, s5, s4, s3, s2, s1)));
        e7.setTpsReposFinal(60);
        Exercice e8 = new ExerciceSimple((TextView) findViewById(R.id.exercice8), new LinkedList<>(Arrays.asList(s3, s2, s1)));
        e8.setTpsRepos(60);

        List<Exercice> exercices = new LinkedList<>(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8));

        Seance seance = new Seance(exercices);

        return seance;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
