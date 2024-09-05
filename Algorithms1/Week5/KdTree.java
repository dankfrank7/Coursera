// package Week5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
    private static final boolean VERTICAL = true;       // x-coordinate
    private static final boolean HORIZONTAL = false;    // y-coordinate
    private Node root;
    private int size; 

    private class Node {
        Point2D p; 
        Node left, right;
        boolean dim; // current dimension (VERTICAL or HORIZONTAL)

        public Node(Point2D p, boolean dim) {
            this.p = p;
            this.left = null;
            this.right = null;
            this.dim = dim; // true if vertical, false if 
        }
    }

    // construct an empty set of points
    public KdTree() {
        this.root = null;
        this.size = 0;
    }

    // is the set empty? 
    public boolean isEmpty() {
        return this.root == null;
    }

    // number of points in the set
    public int size() {
        return size;
    }

    // add a point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException("KdTree.insert: argument Point2D p is null");
        this.root = insert(root, p, VERTICAL);  
    }

    private Node insert(Node node, Point2D p, boolean dim) {
        if (node == null) {
            this.size++;
            return new Node(p, dim); // start with vertical
        }
        int cmp;
        if (dim == VERTICAL) cmp = Point2D.X_ORDER.compare(p, node.p); // vertical
        else                 cmp = Point2D.Y_ORDER.compare(p, node.p); // horizontal
        
        if      (cmp < 0)           node.left = insert(node.left, p, !dim);
        else if (cmp > 0)           node.right = insert(node.right, p, !dim);
        else if (!node.p.equals(p)) node.right = insert(node.right, p, !dim); // if value is equal, add to right tree?
    
        return node;
    }

    // does the set contain p?
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException("KdTree.contains: Point2D p is null");
        return contains(root, p, VERTICAL);
    }

    private boolean contains(Node node, Point2D p, boolean dim) {
        if (node == null) return false;
        if (node.p.equals(p)) return true;
    
        int cmp;
        if (dim == VERTICAL) cmp = Point2D.X_ORDER.compare(p, node.p); // vertical
        else                 cmp = Point2D.Y_ORDER.compare(p, node.p); // horizontal
    
        if (cmp < 0) return contains(node.left, p, !dim);
        else return contains(node.right, p, !dim);
    }

    public void draw() {
        draw(root, new RectHV(0, 0, 1, 1));
    }

    private void draw(Node node, RectHV rect) {
        if (node == null) return;

        // Draw the point
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        node.p.draw();

        // Draw the splitting line
        if (node.dim == VERTICAL) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius();
            StdDraw.line(node.p.x(), rect.ymin(), node.p.x(), rect.ymax());
            draw(node.left, new RectHV(rect.xmin(), rect.ymin(), node.p.x(), rect.ymax()));
            draw(node.right, new RectHV(node.p.x(), rect.ymin(), rect.xmax(), rect.ymax()));
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.setPenRadius();
            StdDraw.line(rect.xmin(), node.p.y(), rect.xmax(), node.p.y());
            draw(node.left, new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.p.y()));
            draw(node.right, new RectHV(rect.xmin(), node.p.y(), rect.xmax(), rect.ymax()));
        }
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("KdTree.range: RectHV rect is null");
        List<Point2D> inside = new ArrayList<>();
        range(root, rect, inside);
        return inside;
    }

    private void range(Node node, RectHV rect, List<Point2D> inside) {
        if (node == null) {
            return;
        }
    
        if (rect.contains(node.p)) {
            inside.add(node.p);
        }
    
        // Check if the current node splits the space vertically (X-axis) or horizontally (Y-axis)
        if (node.dim == VERTICAL) {
            // Vertical segment
            if (rect.xmin() <= node.p.x() && node.p.x() <= rect.xmax()) {
                // The vertical line intersects with the query rectangle
                range(node.left, rect, inside);
                range(node.right, rect, inside);
            } else if (rect.xmin() > node.p.x()) {
                // Search right
                range(node.right, rect, inside);
            } else {
                // Search left
                range(node.left, rect, inside);
            }
        } else {
            // Horizontal segment
            if (rect.ymin() <= node.p.y() && node.p.y() <= rect.ymax()) {
                // The horizontal line intersects with the query rectangle
                range(node.left, rect, inside);
                range(node.right, rect, inside);
            } else if (rect.ymin() > node.p.y()) {
                // Search above
                range(node.right, rect, inside);
            } else {
                // Search below
                range(node.left, rect, inside);
            }
        }
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (isEmpty()) return null;
        if (p == null) throw new IllegalArgumentException("KdTree.nearest: Point2D p is null");
        return nearest(root, p, root.p, VERTICAL);
    }

    private Point2D nearest(Node node, Point2D p, Point2D nearest, boolean dim) {
        if (node == null) return nearest; 
        if (node.p.distanceSquaredTo(p) < nearest.distanceSquaredTo(p)) {
            nearest = node.p;
        }
        Node first, second;
        if ((dim == VERTICAL && p.x() < node.p.x()) || (dim == HORIZONTAL && p.y() < node.p.y())) {
            first = node.left;
            second = node.right;
        } else {
            first = node.right;
            second = node.left;
        }
    
        nearest = nearest(first, p, nearest, !dim);
    
        double distToSplitLine;
        if (dim == VERTICAL) {
            distToSplitLine = Math.pow(p.x() - node.p.x(), 2);
        } else {
            distToSplitLine = Math.pow(p.y() - node.p.y(), 2);
        }
    
        if (distToSplitLine < nearest.distanceSquaredTo(p)) {
            nearest = nearest(second, p, nearest, !dim);
        }
        return nearest;
    }


    public static void main(String[] args) {
        // Create a new KdTree
        KdTree kdTree = new KdTree();

        // Generate and insert 20 random points into the KdTree
        Random rand = new Random();
        for (int i = 0; i < 30; i++) {
            double x = rand.nextDouble(); // Random x-coordinate between 0 and 1
            double y = rand.nextDouble(); // Random y-coordinate between 0 and 1
            Point2D point = new Point2D(x, y);
            kdTree.insert(point);
        }

        // Define a rectangle for the range search
        RectHV rect = new RectHV(0.3, 0.3, 0.8, 0.7);

        // Perform range search to find all points inside the rectangle
        Iterable<Point2D> pointsInRange = kdTree.range(rect);

        // Set up the drawing canvas
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);

        // Draw the KdTree with subdivision lines
        kdTree.draw();

        // Draw the rectangle for the range search
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.setPenRadius();
        rect.draw();

        // Highlight the points inside the rectangle
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.setPenRadius(0.02);
        for (Point2D p : pointsInRange) {
            p.draw();
        }

        // Show the final drawing
        StdDraw.show();
    }
    
}
