//package Week4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import java.util.LinkedList;

public class Solver {
    private MinPQ<SearchNode> pq, pqTwin;
    private boolean solvable;
    private SearchNode goalNode;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initialBoard) {
        if (initialBoard == null) throw new IllegalArgumentException("Initial board is null.");

        pq = new MinPQ<>();
        pqTwin = new MinPQ<>();
        initialiseQueues(initialBoard);

        while(!isResolved()) {
            expandNode(pq, true);
            expandNode(pqTwin, false);
        }
    }

    private void initialiseQueues(Board initialBoard) {
        pq.insert(new SearchNode(initialBoard, 0, null));
        pqTwin.insert(new SearchNode(initialBoard.twin(), 0, null));
    }

    private boolean isResolved() {
        SearchNode current = pq.min();
        SearchNode currentTwin = pqTwin.min();

        if (current.isGoal()) {
            solvable = true;
            goalNode = current;
            return true;
        } else if (currentTwin.isGoal()) {
            solvable = false;
            return true;
        }
        return false;
    }

    private void expandNode(MinPQ<SearchNode> queue, boolean isPrimary) {
        SearchNode current = queue.delMin();

        for (Board neighbor : current.board.neighbors()) {
            if (current.prev == null || !neighbor.equals(current.prev.board)) {
                queue.insert(new SearchNode(neighbor, current.moves+1, current));
            }
        }
    }
    
    private class SearchNode implements Comparable<SearchNode> {
        private int hamming, moves, manhattan, priority;
        private Board board;
        private SearchNode prev;
        
        public SearchNode(Board board, int moves, SearchNode prev) {
            this.board = board;
            this.moves = moves;
            this.hamming = board.hamming();
            this.manhattan = board.manhattan();
            this.priority = this.manhattan + this.moves; // priority function
            this.prev = prev;
        }

        // if this is lower priority than that, return -1
        public int compareTo(SearchNode that) {
            return Integer.compare(this.priority, that.priority);
        }

        public boolean isGoal() {
            return this.board.isGoal();
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return solvable;
    }
    
    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return solvable ? goalNode.moves : -1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!solvable) return null;
        LinkedList<Board> solution = new LinkedList<>();
        for (SearchNode node = goalNode; node != null; node = node.prev) {
            solution.addFirst(node.board);
        }
        return solution;
    }    

    // test client (see below) 
    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);
    
        // solve the puzzle
        Solver solver = new Solver(initial);
    
        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}