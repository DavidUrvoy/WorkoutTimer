package model;

import java.util.List;

/**
 * Created by David on 16/04/2016.
 */
public class Seance {

    private List<Exercice> exercices;

    public Seance(List<Exercice> exercices) {
        this.exercices = exercices;
    }

    public List<Exercice> getExercices() {
        return exercices;
    }

}
