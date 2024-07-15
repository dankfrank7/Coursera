//package Week3;

import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public class FastCollinearPoints {
    private final ArrayList<LineSegment> segmentsList = new ArrayList<>();


    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points){
        if (points == null) throw new IllegalArgumentException("Argument is null");
        for (Point point : points) {
            if (point == null) throw new IllegalArgumentException("Point in array is null");
        }
        checkDuplicates(points);

        Point[] sortedPoints = points.clone();

        for (Point p : points) {
            Arrays.sort(sortedPoints, p.slopeOrder());

            int count = 1; 
            Double prevSlope = p.slopeTo(sortedPoints[0]);

            for (int j = 1; j < sortedPoints.length; j++) {
                double currentSlope = p.slopeTo(sortedPoints[j]);

                if (Double.compare(currentSlope, prevSlope) == 0) {
                    count++;
                } else {
                    if (count >= 3) {
                        addSegment(p, sortedPoints, j - count, j -1);
                    }
                    count = 1;
                    prevSlope = currentSlope;
                }
            }

            // check the last group of collinear points
            if (count >= 3) {
                addSegment(p, sortedPoints, sortedPoints.length - count, sortedPoints.length -1);
            }
        }
    }        

    // make sure every line segment is only added once
    private void addSegment(Point p, Point[] points, int start, int end) {
        Point[] collinearPoints = new Point[end - start + 2]; 
        collinearPoints[0] = p;

        for (int i = start; i <= end; i++) {
            collinearPoints[i - start + 1] = points[i]; 
        }
        Arrays.sort(collinearPoints); 
        if (p.compareTo(collinearPoints[0]) == 0) {
            segmentsList.add(new LineSegment(collinearPoints[0], collinearPoints[collinearPoints.length - 1]));
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
    
    public int numberOfSegments() {
        return segmentsList.size(); 
    }

    public LineSegment[] segments() {
        return segmentsList.toArray(new LineSegment[0]);
    }
    
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        StdDraw.setPenRadius(0.005); // Set the pen radius to make the line smaller
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }      
}