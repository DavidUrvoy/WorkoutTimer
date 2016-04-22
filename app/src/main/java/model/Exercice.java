package model;

import android.view.View;
import android.widget.TextView;

import java.util.List;

/**
 * Created by David on 16/04/2016.
 */
public abstract class Exercice implements IExercice {

    private TextView textView;

    private List<Serie> series;

    private int tpsRepos = 25;
    private int tpsReposFinal = 25;

    public Exercice(TextView textView, List<Serie> series) {
        this.textView = textView;
        this.series = series;
    }

    @Override
    public int execute(List<Exercice> exercices){
        getTextView().setVisibility(View.INVISIBLE);
        exercices.remove(this);
        return tpsReposFinal;
    }

    public List<Serie> getSeries() {
        return series;
    }

    public void setSeries(List<Serie> series) {
        this.series = series;
    }

    public TextView getTextView() {
        return textView;
    }

    public int getTpsRepos() {
        return tpsRepos;
    }

    public void setTpsRepos(int tpsRepos) {
        this.tpsRepos = tpsRepos;
    }

    public int getTpsReposFinal() {
        return tpsReposFinal;
    }

    public void setTpsReposFinal(int tpsReposFinal) {
        this.tpsReposFinal = tpsReposFinal;
    }
}
