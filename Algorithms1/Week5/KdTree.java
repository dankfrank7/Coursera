// package Week5;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

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
            return new Node(p, VERTICAL); // start with vertical
        }
        int cmp;
        if (dim == VERTICAL) cmp = Point2D.X_ORDER.compare(p, node.p); // vertical
        else                 cmp = Point2D.Y_ORDER.compare(p, node.p); // horizontal
        
        if      (cmp < 0) node.left = insert(node.left, p, !node.dim);
        else if (cmp > 0) node.right = insert(node.right, p, !node.dim);
        else if (!node.p.equals(p)) node.right = insert(node.right, p, !node.dim); // handle duplicate points
    
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

    //  draw all points to standard draw
    public void draw() {
        draw(root);
    }

    private void draw(Node node) {
        if (node == null) return;
        node.p.draw();
        draw(node.left);
        draw(node.right);
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("KdTree.range: RectHV rect is null");
        List<Point2D> inside = new ArrayList<>();
        range(root, inside, rect);
        return inside;
    }

    private void range(Node node, List<Point2D> inside, RectHV rect) {
        if (node == null) return;
        if (rect.contains(node.p)) inside.add(node.p);
    
        RectHV leftRect, rightRect;
        if (node.dim == VERTICAL) {
            leftRect = new RectHV(0., 0., node.p.x(), 1.);
            rightRect = new RectHV(node.p.x(), 0., 1., 1.);
        } else {
            leftRect = new RectHV(0., 0., 1., node.p.y());
            rightRect = new RectHV(0., node.p.y(), 1. , 1.);
        }
    
        if (rect.intersects(leftRect)) {
            range(node.left, inside, rect);
        }
    
        if (rect.intersects(rightRect)) {
            range(node.right, inside, rect);
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
        if (second != null && 
            ((dim == VERTICAL && Math.abs(p.x() - node.p.x()) < nearest.distanceSquaredTo(p)) || 
             (dim == HORIZONTAL && Math.abs(p.y() - node.p.y()) < nearest.distanceSquaredTo(p)))) {
            nearest = nearest(second, p, nearest, !dim);
        }
        return nearest;
    }

    public static void main(String[] args) {

    }

}
