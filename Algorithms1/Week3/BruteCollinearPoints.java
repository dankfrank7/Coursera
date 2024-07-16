package Week3;

import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public class BruteCollinearPoints {
    private final ArrayList<LineSegment> segmentsList = new ArrayList<>();

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        for (Point point : points) {
            if (point == null) throw new IllegalArgumentException("Point in array is null");
        }
        checkDuplicates(points);

        int N = points.length;
        
        // Brute force loop through every combination of 4 points
        for (int i = 0; i < N - 3; i++) {
            for (int j = i + 1; j < N - 2; j++) {
                for (int k = j + 1; k < N - 1; k++) {
                    for (int l = k + 1; l < N; l++) {
                        Point p = points[i];
                        Point q = points[j];
                        Point r = points[k];
                        Point s = points[l];

                        if (p.slopeOrder().compare(q, r) == 0 && p.slopeOrder().compare(q, s) == 0) {
                            Point[] collinearPoints = {p, q, r, s};
                            Arrays.sort(collinearPoints);
                            segmentsList.add(new LineSegment(collinearPoints[0], collinearPoints[3]));
                        }
                    }
                }
            }
        }
    }

    private void checkDuplicates(Point[] points) {
        Arrays.sort(points);
        for (int i = 1; i < points.length; i++) {
            if (points[i].compareTo(points[i - 1]) == 0) {
                throw new IllegalArgumentException("Duplicate points detected");
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return segmentsList.size();
    }       
    
    // the line segments
    public LineSegment[] segments() {
        return segmentsList.toArray(new LineSegment[0]);
    }
            
    // testing client
    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
    
        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.setPenRadius(0.02); // Set the pen radius to make the points larger
        for (Point p : points) {
            p.draw();
        }
    
        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        StdDraw.setPenRadius(0.005); // Set the pen radius to make the line smaller
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }                
}
