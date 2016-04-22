package utils;

import android.view.View;

import java.util.List;

import model.Exercice;
import model.Serie;

/**
 * Created by David on 17/04/2016.
 */
public class ExerciceUtils {

    public static int executerSerie(List<Exercice> exercices, Exercice exercice) {
    int tpsRepos = exercice.getTpsRepos();
        exercice.getSeries().get(0).getTextView().setVisibility(View.INVISIBLE);
        exercice.getSeries().remove(0);

        if (exercice.getSeries().isEmpty()) {
            tpsRepos = exercice.execute(exercices);
            for (Serie s : exercices.get(0).getSeries())
                s.getTextView().setVisibility(View.VISIBLE);
        }

        return tpsRepos;
    }
}
