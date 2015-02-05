package no.curiosity.gameoflife.vews;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import no.curiosity.gameoflife.R;
import no.curiosity.gameoflife.painters.Grid;

public class GridView extends ImageView {
    float xFrame = 0;
    float yFrame = 0;
    boolean doDraw = false;
    int animation = 0x00;

    public GridView(Context context) {
        super(context);
    }

    public GridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Grid.paintGrid(canvas, getHeight(), getWidth());

        if (!isClickable()) {
            setClickable(true);
        }

        if (doDraw) {
            Grid.updateRectangulars(canvas, xFrame, yFrame, animation);
            doDraw = false;
        }

        if (animation == Grid.ANIM_SINGLE_STEP) {
            animation = Grid.ANIM_NONE;
        }

        if (animation == Grid.ANIM_MULTI_STEP) {
            stepGameOfLife();
            try {
                Thread.sleep(250);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        MotionEvent.PointerCoords pointClicked = new MotionEvent.PointerCoords();

        if (event.getAction() == MotionEvent.ACTION_UP) {
            event.getPointerCoords(0, pointClicked);

            xFrame = Math.round((pointClicked.x + (Grid.marginX * 4)) / Grid.X_PIXEL_STEP);
            yFrame = Math.round((pointClicked.y + Grid.BOTTOM_MARGIN + Grid.TOP_MARGIN) / Grid.Y_PIXEL_STEP);

            // Only update and re-draw if valid point is clicked.
            if (shouldUpdateGrid()) {
                doDraw = true;
                invalidate();
            }
        }
        return true;
    }

    public void clearGrid() {
        Grid.clearRectangulars();
        invalidate();
    }

    public void runGameOfLife() {
        if (animation == Grid.ANIM_NONE) {
            animation = Grid.ANIM_MULTI_STEP;
        }
        else {
            animation = Grid.ANIM_NONE;
        }

        invalidate();
    }

    public void stepGameOfLife() {
        doDraw = true;
        if (animation == Grid.ANIM_NONE) {
            animation = Grid.ANIM_SINGLE_STEP;
        }
        Grid.doOneStep();
        invalidate();
    }

    private boolean shouldUpdateGrid() {
        return xFrame > -1 && yFrame > -1
                && xFrame <= Grid.calcNumberOfXGrids(getWidth())
                && yFrame <= Grid.calcNumberofYGrids(getHeight());
    }
}
