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
}