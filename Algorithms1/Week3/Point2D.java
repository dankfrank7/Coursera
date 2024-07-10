package Week3;

import java.util.Comparator;

public class Point2D {
    private final Comparator<Point2D> POLAR_ORDER = new PolarOrder();
    private final double x, y;
    
    public Point2D(double x, double y) {
        this.x = x; 
        this.y = y;
    }

    public static int ccw(Point2D a, Point2D b, Point2D c) {
        double area2 = (b.x - a.x)*(c.y-a.y) - (b.y-a.y)*(c.x-a.x);
        
        if (area2 < 0) {
            return -1; // clockwise
        } else if (area2 > 0) {
            return 1; //counter-clockwise
        } else {
            return 0; //collinear
        }
    }

    private class PolarOrder implements Comparator<Point2D> {
        public int compare(Point2D q1, Point2D q2) {
            double dy1 = q1.y - y;
            double dy2 = q2.y - y;

            if      (dy1 == 0 && dy2 == 0) return 0; // is this correct?
            else if (dy1 >= 0 && dy2 < 0) return -1;
            else if (dy2 >= 0 && dy1 < 0) return +1;
            else return -ccw(Point2D.this, q1, q2); 
        }
    }


    public static void main(String[] args) { 

        Point2D a = new Point2D(0, 0);
        Point2D b = new Point2D(1, 1);
        Point2D c = new Point2D(2, 0);
        System.out.println("Test 1 (Counter-clockwise): " + (ccw(a, b, c) == 1));
    }
}