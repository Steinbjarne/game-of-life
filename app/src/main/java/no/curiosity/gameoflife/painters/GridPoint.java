package no.curiosity.gameoflife.painters;

public class GridPoint {
    private Float xPoint;
    private Float yPoint;

    public GridPoint(Float xPoint, Float yPoint) {
        this.xPoint = xPoint;
        this.yPoint = yPoint;
    }

    public Float getXPoint() {
        return xPoint;
    }

    public Float getYPoint() {
        return yPoint;
    }

    public boolean isNeighbour(GridPoint point) {
        float checkX = point.getXPoint();
        float checkY = point.getYPoint();
        if (checkX - 1 == xPoint) {
            if (checkY - 1 == yPoint || checkY == yPoint || checkY + 1 == yPoint) {
                return true;
            }
        }
        if (checkX == xPoint) {
            if (checkY - 1 == yPoint || checkY + 1 == yPoint) {
                return true;
            }
        }
        if (checkX + 1 == xPoint) {
            if (checkY - 1 == yPoint || checkY == yPoint || checkY + 1 == yPoint) {
                return true;
            }
        }
        return false;
    }

    public boolean isSamePoint(GridPoint checkPoint) {
        return this.xPoint == checkPoint.getXPoint().floatValue()
                && this.yPoint == checkPoint.getYPoint().floatValue();
    }
}