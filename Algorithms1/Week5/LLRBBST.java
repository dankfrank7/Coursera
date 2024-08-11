package Week5;

// Left Leaning Red Black Binary Search Tree 
public class LLRBBST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    private class Node { 
        Key key;
        Value val;
        Node left, right;
        boolean colour; // colour of parent link

        public Node(Key key, Value val, boolean colour) {
            this.key = key;
            this.val = val;
            this.left = null;
            this.right = null;
            this.colour = colour;
        }
    }

    public LLRBBST() {
        this.root = null;
    }

    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if      (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else if (cmp == 0) return x.val;
        }
        return null;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
        root.colour = BLACK;
    }

    private Node put(Node h, Key key, Value val) {
        if (h == null) return new Node(key, val, RED);
        int cmp = key.compareTo(h.key);
        if      (cmp < 0) h.left = put(h.left, key, val);
        else if (cmp > 0) h.right = put(h.right, key, val);
        else if (cmp == 0) h.val = val;

        if (isRed(h.right) && !isRed(h.left))       h = rotateLeft(h);
        if (isRed(h.left)  && isRed(h.left.left))   h = rotateRight(h);
        if (isRed(h.left)  && isRed(h.right))       flipColours(h);
        return h;
    }


    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.colour == RED;
    }
    
    private Node rotateLeft(Node h) {
        assert isRed(h.right);
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.colour = h.colour;
        h.colour = RED;
        return x;
    }

    private Node rotateRight(Node h) {
        assert isRed(h.left);
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.colour = h.colour;
        h.colour = RED;
        return x;
    }

    private void flipColours(Node h) {
        assert !isRed(h);
        assert isRed(h.left);
        assert isRed(h.right);
        h.colour = RED;
        h.left.colour = BLACK;
        h.right.colour = BLACK;
    }

    public void printTree() {
        printTree(root, "", true);
    }
    
    private void printTree(Node node, String indent, boolean last) {
        if (node != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "     ";
            } else {
                System.out.print("L----");
                indent += "|    ";
            }
            System.out.println(node.key + "(" + (node.colour ? "RED" : "BLACK") + ")");
            printTree(node.left, indent, false);
            printTree(node.right, indent, true);
        }
    }
    
    public static void main(String[] args) {
        // Test insertions
        LLRBBST<String, Integer> st = new LLRBBST<>();
        st.put("S", 1);
        st.put("E", 2);
        st.put("A", 3);
        st.put("R", 4);
        st.put("C", 5);
        st.put("H", 6);
        st.put("X", 7);
        st.put("M", 8);
        st.put("P", 9);
        st.put("L", 10);

        // Test searching
        assert st.get("E").equals(2) : "Test failed, value should be 2";
        assert st.get("M").equals(8) : "Test failed, value should be 8";
        assert st.get("X").equals(7) : "Test failed, value should be 7";
        assert st.get("Z") == null : "Test failed, value should be null";

        System.out.println("All tests passed.");
        st.printTree();
    }
}
