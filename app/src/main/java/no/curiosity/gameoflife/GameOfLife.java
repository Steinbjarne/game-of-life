package no.curiosity.gameoflife;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import no.curiosity.gameoflife.views.GridView;

public class GameOfLife extends ActionBarActivity {
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game_of_life);

        gridView = (GridView) findViewById(R.id.viewGrids);
    }

    public void clearView(View view) {
        gridView.clearGrid();
    }
}
