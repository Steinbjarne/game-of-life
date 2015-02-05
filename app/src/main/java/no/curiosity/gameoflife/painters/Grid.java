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
    public static final int GRID_COLOR = Color.rgb(20, 20, 200);
    public static final int SQUARE_BORDER = 5;

    public static float marginX = 0;
    private static List<GridPoint> points = new ArrayList<>();

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

    public static void updateRectangulars(Canvas canvas, float xFrame, float yFrame) {
        Paint paint = new Paint();
        paint.setColor(Color.rgb(200, 20, 200));

        addOrRemovePoint(xFrame, yFrame);

        prepareRepaint(canvas, paint);
    }

    public static void clearRectangulars() {
        points = new ArrayList<>();
    }

    private static void prepareRepaint(Canvas canvas, Paint paint) {
        for (GridPoint point : points) {
            float pointX = point.getXPoint() * X_PIXEL_STEP - X_PIXEL_STEP;
            float pointY = point.getYPoint() * Y_PIXEL_STEP - Y_PIXEL_STEP;

            canvas.drawRect(
                    pointX + marginX + SQUARE_BORDER,
                    pointY + SQUARE_BORDER + TOP_MARGIN,
                    pointX + marginX + X_PIXEL_STEP - SQUARE_BORDER,
                    pointY + Y_PIXEL_STEP + TOP_MARGIN - SQUARE_BORDER, paint);
        }
    }

    private static void addOrRemovePoint(float xFrame, float yFrame) {
        GridPoint removePoint = null;
        for (GridPoint point : points) {
            if (point.getXPoint() == xFrame && point.getYPoint() == yFrame) {
                removePoint = point;
            }
        }
        if (removePoint == null) {
            points.add(new GridPoint(xFrame, yFrame));
        } else {
            points.remove(removePoint);
        }
    }

    private static Paint createGridPainter() {
        Paint paint = new Paint();
        paint.setColor(GRID_COLOR);
        paint.setStrokeWidth(STROKE_WIDTH);

        return paint;
    }

    public static float calcNumberOfXGrids(float xSize) {
        return (int) xSize / X_PIXEL_STEP;
    }

    public static float calcNumberofYGrids(float ySize) {
        return ((int) (ySize - BOTTOM_MARGIN) / Y_PIXEL_STEP);
    }
}