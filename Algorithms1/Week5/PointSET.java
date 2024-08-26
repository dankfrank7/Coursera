package Week5; 

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class PointSET {
    private SET<Point2D> points;

    // construct an empty set of points
    public PointSET() {
        this.points = new SET<>();
    }

    // is the set empty? 
    public boolean isEmpty() {
        return this.points.size() == 0;
    }

    // number of points in the set
    public int size() {
        return this.points.size();
    }

    // add a point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException("PointSET.insert: Point2D p is null");
        this.points.add(p);
    }

    // does the set contain p?
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException("PointSET.contains: Point2D p is null");
        return this.points.contains(p);
    }

    //  draw all points to standard draw
    public void draw() {
        if (this.points.size() == 0) return;
        for (Point2D point : points) {
            point.draw();
        }        
     }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("PointSET.range: RectHV rect is null");
        List<Point2D> inside = new ArrayList<>();
        for (Point2D point : this.points) {
            if (rect.contains(point)) {
                inside.add(point);
            }
        }
        return inside;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (this.points.isEmpty()) return null;
        if (p == null) throw new IllegalArgumentException("PointSET.nearest: Point2D p is null");

        Point2D nearest = null;
        double nearestDist = Double.POSITIVE_INFINITY;

        for (Point2D point : points) {
            double dist = p.distanceSquaredTo(point);
            if (dist < nearestDist) {
                nearestDist = dist;
                nearest = point;
            }
        }
        return nearest;
    }

    public static void main(String[] args) {

    }
}