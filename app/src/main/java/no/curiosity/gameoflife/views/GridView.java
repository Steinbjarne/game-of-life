package no.curiosity.gameoflife.views;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import no.curiosity.gameoflife.painters.Grid;

public class GridView extends ImageView {
    private float xFrame = 0;
    private float yFrame = 0;
    private boolean doDraw = false;

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
    public void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        Grid.paintGrid(canvas, getHeight(), getWidth());

        if (!isClickable()) {
            setClickable(true);
        }

        if (doDraw) {
            Grid.updateRectangulars(canvas, xFrame, yFrame);
            doDraw = false;
        }
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
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

    private boolean shouldUpdateGrid() {
        return xFrame > -1 && yFrame > -1
                && xFrame <= Grid.calcNumberOfXGrids(getWidth())
                && yFrame <= Grid.calcNumberofYGrids(getHeight());
    }
}
