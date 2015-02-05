package no.curiosity.gameoflife;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import no.curiosity.gameoflife.vews.GridView;

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

    public void runGameOfLife(View view) {
        GridView gridView = (GridView) findViewById(R.id.viewGrids);
        Button run = (Button) findViewById(R.id.btnRun);
        Button clear = (Button) findViewById(R.id.btnClear);
        Button step = (Button) findViewById(R.id.btnStep);
        if (run.getText().equals("Run")) {
            run.setText("Stop");
            clear.setEnabled(false);
            step.setEnabled(false);
        }
        else {
            run.setText("Run");
            clear.setEnabled(true);
            step.setEnabled(true);
        }
        gridView.runGameOfLife();
    }

    public void stepGameOfLife(View view) {
        GridView gridView = (GridView) findViewById(R.id.viewGrids);
        gridView.stepGameOfLife();
    }
}
