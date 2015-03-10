package no.curiosity.gameoflife;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import no.curiosity.gameoflife.vews.GridView;

public class GameOfLife extends ActionBarActivity {
    GridView gridView;
    Button btnRun;
    Button btnClear;
    Button btnStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game_of_life);

        gridView = (GridView) findViewById(R.id.viewGrids);
        btnRun = (Button) findViewById(R.id.btnRun);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnStep = (Button) findViewById(R.id.btnStep);
    }

    public void clearView(View view) {
        gridView.clearGrid();
    }

    public void runGameOfLife(View view) {
        if (btnRun.getText().equals("Run")) {
            btnRun.setText("Stop");
            btnClear.setEnabled(false);
            btnStep.setEnabled(false);
        }
        else {
            btnRun.setText("Run");
            btnClear.setEnabled(true);
            btnStep.setEnabled(true);
        }
        gridView.runGameOfLife();
    }

    public void stepGameOfLife(View view) {
        gridView.stepGameOfLife();
    }
}
