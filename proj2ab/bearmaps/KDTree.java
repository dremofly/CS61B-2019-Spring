package bearmaps;

import java.util.Collections;
import java.util.List;

public class KDTree implements PointSet{
    private static final boolean HORIZONTAL = false;
    private Node root;

    private List<Point> pointSets;
    public KDTree(List<Point> points) {
        //Collections.shuffle(points);
        for(Point point:points) {
            root = insert(point, root, HORIZONTAL);
        }

    }

    private Node insert(Point point, Node node, boolean splitDim) {
        if(node == null) {
            return new Node(point, splitDim);
        }

        if(point.equals(node.getPoint())) {
            return node;
        }

        int cmp = comparePoint(point, node.getPoint(), splitDim);
        if(cmp < 0) {
            node.left = insert(point, node.getLeft(), !splitDim);
        } else {
            node.right = insert(point, node.getRight(), !splitDim);
        }

        return node;
    }

    private int comparePoint(Point point, Point nodePoint, boolean splitDim) {
        if(splitDim == HORIZONTAL) {
            return Double.compare(point.getX(), nodePoint.getX());
        } else {
            return Double.compare(point.getY(), nodePoint.getY());
        }
    }

    @Override
    public Point nearest(double x, double y) {
        Point target = new Point(x,y);
        return nearest(root, target, root.getPoint());
    }

    private Point nearest(Node node, Point target, Point best){
        if (node == null) {
            return best;
        }

        double currDist = Point.distance(node.getPoint(), target);
        double bestDist = Point.distance(best, target);

        int cmp = Double.compare(currDist, bestDist);
        if(cmp < 0){
            // greater
            best = node.getPoint();
        }

        Node goodSide;
        Node badSide;
        cmp = comparePoint(target, node.getPoint(), node.getSplitDim());
        if(cmp < 0) {
            goodSide = node.getLeft();
            badSide = node.getRight();
        } else {
            goodSide = node.getRight();
            badSide = node.getLeft();
        }

        best = nearest(goodSide, target, best);
        if(isBadside(node, target, best)) {
            best = nearest(badSide, target, best);
        }

        return best;
    }

    // 判断badSide的距离会比会比best短
    private boolean isBadside(Node node, Point target, Point best) {
        double bestDist = Point.distance(target, best);
        double badDist;
        if(node.splitDim == HORIZONTAL) {
            badDist = Point.distance(new Point(node.point.getX(), target.getY()), target);
        } else {
            badDist = Point.distance(new Point(target.getX(), node.point.getY()), target);
        }
        return Double.compare(badDist, bestDist) < 0;
    }


    private class Node {
        private Point point;
        private boolean splitDim;
        private Node left;
        private Node right;

        Node(Point p, boolean s) {
            this.point = p;
            this.splitDim = s;
            this.left = null;
            this.right = null;
        }

        public Point getPoint() {
            return point;
        }

        public boolean getSplitDim() {
            return splitDim;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }
    }
}
