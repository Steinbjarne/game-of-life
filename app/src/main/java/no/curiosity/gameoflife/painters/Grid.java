package no.curiosity.gameoflife.painters;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    public static final int Y_PIXEL_STEP = 80;
    public static final int X_PIXEL_STEP = 80;
    public static final int BOTTOM_MARGIN = 10;
    public static final int TOP_MARGIN = 20;
    private static final int STROKE_WIDTH = 2;
    public static final int SQUARE_COLOR = Color.rgb(20, 20, 200);

    public static float marginX = 0;

    public static void paintGrid(final Canvas canvas, final float height, final float width) {
        Paint paint = createGridPainter();

        float maxXGrids = calcNumberOfXGrids(width);
        float maxYGrids = calcNumberofYGrids(height);

        marginX = (width - (maxXGrids * X_PIXEL_STEP)) / 2;
        float xPos = 0;
        float yPos = TOP_MARGIN;

        for (int i = 0; i <= calcNumberofYGrids(height); i++) {
            canvas.drawLine(marginX, yPos, width - marginX, yPos, paint);
            yPos += Y_PIXEL_STEP;
        }

        float maxY = yPos - Y_PIXEL_STEP;
        for (int i = 0; i <= maxYGrids; i++) {
            canvas.drawLine(xPos + marginX, TOP_MARGIN, xPos + marginX, maxY, paint);
            xPos += X_PIXEL_STEP;
        }
    }

    private static Paint createGridPainter() {
        Paint paint = new Paint();
        paint.setColor(SQUARE_COLOR);
        paint.setStrokeWidth(STROKE_WIDTH);

        return paint;
    }

    private static float calcNumberOfXGrids(float xSize) {
        return (int) xSize / X_PIXEL_STEP;
    }

    private static float calcNumberofYGrids(float ySize) {
        return ((int) (ySize - BOTTOM_MARGIN) / Y_PIXEL_STEP);
    }
}