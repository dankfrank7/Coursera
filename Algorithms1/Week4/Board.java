package Week4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Board {
    private int[][] tiles;
    private final int n;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.n = tiles.length;
        this.tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(tiles[i], 0, this.tiles[i], 0, n);
        }
    }
           
    public String toString() {
        StringBuilder s = new StringBuilder();
        int n = dimension();
        s.append(n).append("\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j != 0) s.append(" ");
                s.append(String.format("%2d", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    // board dimension n
    public int dimension() {
        return this.n;
    }

    // number of tiles out of place
    public int hamming() {
        int count = 0;
        int length = tiles.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (tiles[i][j] != 0 && tiles[i][j] != goalPosition(i, j)) {
                    count++;
                }
            }
        }
        return count;
    }
    
    private int goalPosition(int i, int j) {
        return i * tiles.length + j + 1;
    }

    private static int[][] getTargetMat(int n) {
        int[][] targetMatrix = new int[n][n];
        int val = 1;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (val < n*n) {
                    targetMatrix[row][col] = val;
                    val++;
                } else {
                    targetMatrix[row][col] = 0;
                }
            }
        }

        return targetMatrix;
    }

    // private static Board getTargetBoard(int n) {
    //     return new Board(Board.getTargetMat(n));
    // }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int manhattanDist = 0;
        for (int row = 0; row < this.n; row++) {
            for (int col = 0; col < this.n; col++) { 
                int val = this.tiles[row][col];
                if (val != 0) {
                    Index2 currentIndex =  new Index2(row, col); 
                    Index2 targetIndex = findTargetVal(this.tiles[row][col]);
                    manhattanDist += currentIndex.manhattanTo(targetIndex);                  
                }
            }
        }
        return manhattanDist;
    }

    // finds the 2d index of the int val for taget board
    private Index2 findTargetVal(int val) {
        int targetCol, targetRow;
        if (val == 0) {
            targetRow = this.n-1;
            targetCol = this.n-1;
        } else {
            targetRow = (val-1) / this.n;
            targetCol = val - 1 - targetRow*this.n;   
        }  
        return new Index2(targetRow, targetCol);
    }

    private class Index2 { 
        private final int row, col;
     
        private Index2(int row, int col) {
            this.row = row;
            this.col = col;
        }

        private int manhattanTo(Index2 that) {
            int distance = 0;
            distance += Math.abs(this.row - that.row);
            distance += Math.abs(this.col - that.col);
            return distance;
        }

        public String toString() {
            return this.row + ", " + this.col;
        }
    }

    // is this board the goal board?
    public boolean isGoal() {
        return Arrays.deepEquals(this.tiles, getTargetMat(this.n)); 
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null || getClass() != y.getClass() || ((Board) y).tiles.length != n) return false;
        Board other = (Board) y;
        return Arrays.deepEquals(this.tiles, other.tiles);
    }
    

    // all neighboring boards
    public Iterable<Board> neighbors() {
        List<Board> neighbors = new ArrayList<>();
        Index2 emptyTile = findEmptyTile();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
       
        for (int[] dir: directions) {
            int newRow = emptyTile.row + dir[0];
            int newCol = emptyTile.col + dir[1];

            if ((newRow >= 0) && (newCol >= 0) && (newRow < n) && (newCol < n)) {
                exch(emptyTile, new Index2(newRow, newCol));
                neighbors.add(new Board(tiles));
                exch(emptyTile, new Index2(newRow, newCol)); // swap back to original
            }
        }
        return neighbors;
    }

    private Index2 findEmptyTile() {
        for (int row = 0; row < this.n; row++) {
            for (int col = 0; col < this.n; col++) {
                if (this.tiles[row][col] == 0) {
                    return new Index2(row, col);
                }
            }
        }
        throw new Error("Empty tile not found");
    }
        
    // exchanges two values d
    private void exch(Index2 p, Index2 q) {
        int temp = this.tiles[q.row][q.col];
        this.tiles[q.row][q.col] = this.tiles[p.row][p.col];
        this.tiles[p.row][p.col] = temp; 
    }

    private int[][] copyTiles() {
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(tiles[i], 0, copy[i], 0, n);
        }
        return copy;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[][] twinTiles = copyTiles();

        // Find two non-zero adjacent tiles and swap them
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n - 1; col++) {
                if (twinTiles[row][col] != 0 && twinTiles[row][col + 1] != 0) {
                    int temp = twinTiles[row][col];
                    twinTiles[row][col] = twinTiles[row][col + 1];
                    twinTiles[row][col + 1] = temp;
                    return new Board(twinTiles);
                }
            }
        }

        // If no horizontal pair was found, check vertically
        for (int col = 0; col < n; col++) {
            for (int row = 0; row < n - 1; row++) {
                if (twinTiles[row][col] != 0 && twinTiles[row + 1][col] != 0) {
                    int temp = twinTiles[row][col];
                    twinTiles[row][col] = twinTiles[row + 1][col];
                    twinTiles[row + 1][col] = temp;
                    return new Board(twinTiles);
                }
            }
        }

        // Should never reach here if the board is valid
        return null;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        int n = 10;    
        // create matrix
        int[][] tiles = Board.getTargetMat(n);
        // create board instance
        Board board = new Board(tiles);
        System.out.println(board);
    }
}