package bearmaps;

import java.util.List;

public class NaivePointSet implements PointSet {
    private List<Point> pointSets;

    public NaivePointSet(List<Point> points) {
        pointSets = points;
    }

    @Override
    public Point nearest(double x, double y) {
        Point target = new Point(x, y);
        double bestDist = Point.distance(target, pointSets.get(0));
        Point bestPoint = pointSets.get(0);
        for(int i=1; i<pointSets.size(); i++) {
            double dist = Point.distance(target, pointSets.get(i));
            if(dist < bestDist) {
                bestDist = dist;
                bestPoint = pointSets.get(i);
            }
        }

        return bestPoint;
    }

}
