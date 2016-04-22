package model;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by David on 17/04/2016.
 */
public class ExerciceDouble extends Exercice {

    private boolean doublon = true;
    List<Serie> doublonSerie = new ArrayList<>();
    private int tpsReposIntermediaire = 120;

    public ExerciceDouble(TextView textView, List<Serie> series) {
        super(textView, series);
        doublonSerie.addAll(series);
    }

    public ExerciceDouble(TextView textView, List<Serie> series, int tpsReposIntermediaire) {
        super(textView, series);
        this.tpsReposIntermediaire = tpsReposIntermediaire;
        doublonSerie.addAll(series);
    }

    @Override
    public int execute(List<Exercice> exercices) {
        int tps = getTpsReposFinal();
        if (doublon) {
            doublon = false;
            this.setSeries(doublonSerie);
            tps = tpsReposIntermediaire;
        } else
            super.execute(exercices);
        return tps;
    }

}