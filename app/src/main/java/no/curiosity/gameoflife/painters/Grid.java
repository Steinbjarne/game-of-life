package no.curiosity.gameoflife.painters;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    public static int ANIM_NONE = 0x00;
    public static int ANIM_SINGLE_STEP = 0x01;
    public static int ANIM_MULTI_STEP = 0x02;

    public static final int NEIGHBOURS_TO_SURVIVE = 2;
    public static final int NEIGHBOURS_TO_LIGHT_UP = 3;

    public static final int Y_PIXEL_STEP = 80;
    public static final int X_PIXEL_STEP = 80;
    public static final int BOTTOM_MARGIN = 10;
    public static final int TOP_MARGIN = 20;
    public static final int GRID_COLOR = Color.rgb(20, 20, 200);
    public static final int SQUARE_BORDER = 5;

    private static final int STROKE_WIDTH = 2;

    public static float marginX = 0;
    private static float maxXGrids = 0;
    private static float maxYGrids = 0;

    private static List<GridPoint> points = new ArrayList<>();

    public static void paintGrid(final Canvas canvas, final float height, final float width) {
        Paint paint = createGridPainter();

        maxXGrids = calcNumberOfXGrids(width);
        maxYGrids = calcNumberofYGrids(height);

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

    public static void updateRectangulars(Canvas canvas, float xFrame, float yFrame, int animation) {
        Paint paint = new Paint();
        paint.setColor(Color.rgb(200, 20, 200));

        if (animation == ANIM_NONE) {
            addOrRemovePoint(xFrame, yFrame);
        }

        prepareRepaint(canvas, paint);
    }

    public static void clearRectangulars() {
        points = new ArrayList<>();
    }

    public static void doOneStep() {
        List<GridPoint> newPoints = new ArrayList<>();

        // Check if existing points are to survive or die.
        for (GridPoint point : points) {
            if (shouldAddPoint(point, false)) {
                newPoints.add(point);
            }
        }

        // Check if empty points surrounding existing points are to be lit.
        for (GridPoint point : points) {
            GridPoint pointToCheck;

            pointToCheck = new GridPoint(point.getXPoint() - 1, point.getYPoint() - 1);
            if (shouldAddPoint(pointToCheck, true)) {
                if (!isAlreadyAdded(newPoints, pointToCheck)) {
                    newPoints.add(pointToCheck);
                }
            }
            pointToCheck = new GridPoint(point.getXPoint() - 1, point.getYPoint());
            if (shouldAddPoint(pointToCheck, true)) {
                if (!isAlreadyAdded(newPoints, pointToCheck)) {
                    newPoints.add(pointToCheck);
                }
            }
            pointToCheck = new GridPoint(point.getXPoint() - 1, point.getYPoint() + 1);
            if (shouldAddPoint(pointToCheck, true)) {
                if (!isAlreadyAdded(newPoints, pointToCheck)) {
                    newPoints.add(pointToCheck);
                }
            }
            pointToCheck = new GridPoint(point.getXPoint(), point.getYPoint() - 1);
            if (shouldAddPoint(pointToCheck, true)) {
                if (!isAlreadyAdded(newPoints, pointToCheck)) {
                    newPoints.add(pointToCheck);
                }
            }
            pointToCheck = new GridPoint(point.getXPoint(), point.getYPoint() + 1);
            if (shouldAddPoint(pointToCheck, true)) {
                if (!isAlreadyAdded(newPoints, pointToCheck)) {
                    newPoints.add(pointToCheck);
                }
            }
            pointToCheck = new GridPoint(point.getXPoint() + 1, point.getYPoint() - 1);
            if (shouldAddPoint(pointToCheck, true)) {
                if (!isAlreadyAdded(newPoints, pointToCheck)) {
                    newPoints.add(pointToCheck);
                }
            }
            pointToCheck = new GridPoint(point.getXPoint() + 1, point.getYPoint());
            if (shouldAddPoint(pointToCheck, true)) {
                if (!isAlreadyAdded(newPoints, pointToCheck)) {
                    newPoints.add(pointToCheck);
                }
            }
            pointToCheck = new GridPoint(point.getXPoint() + 1, point.getYPoint() + 1);
            if (shouldAddPoint(pointToCheck, true)) {
                if (!isAlreadyAdded(newPoints, pointToCheck)) {
                    newPoints.add(pointToCheck);
                }
            }
        }

        clearRectangulars();
        points.addAll(newPoints);
    }

    private static boolean isAlreadyAdded(List<GridPoint> newPoints, GridPoint reusedTempPointForVar) {
        boolean isSamePoint = false;
        for (GridPoint pointToCheck : newPoints) {
            if (pointToCheck.isSamePoint(reusedTempPointForVar)) {
                isSamePoint = true;
                break;
            }
        }
        return isSamePoint;
    }

    private static boolean shouldAddPoint(GridPoint pointToCheck, boolean isEmptyCell) {
        if (isInsideGrid(pointToCheck)) {
            int numNeighbours = isNeighbouringPoint(pointToCheck);
            if (!isEmptyCell) {
                if (numNeighbours == NEIGHBOURS_TO_SURVIVE || numNeighbours == NEIGHBOURS_TO_LIGHT_UP) {
                    return true;
                }
            } else {
                if (numNeighbours == NEIGHBOURS_TO_LIGHT_UP) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isInsideGrid(GridPoint pointToCheck) {
        return !(pointToCheck.getXPoint() < 1 || pointToCheck.getYPoint() < 1) &&
                !(pointToCheck.getXPoint() >= maxXGrids+1 || pointToCheck.getYPoint() >= maxYGrids+1);
    }

    private static int isNeighbouringPoint(GridPoint point) {
        int numNeighbours = 0;
        for (GridPoint neighbouringPoint : points) {
            if (point.isNeighbour(neighbouringPoint)) {
                numNeighbours++;
            }
        }
        return numNeighbours;
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