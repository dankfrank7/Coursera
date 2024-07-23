package Week4;

public class Sandbox { 
    public static void main(String[] args) {
        int dim = 5;
        int[][] matrix = new int[dim][dim];
        System.out.println(dimension(matrix));
        System.out.println(toString(matrix));

    }
    
    public static String toString(int[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                sb.append(matrix[i][j]);
                if (j < matrix[i].length - 1) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static int dimension(int[][] matrix) { 
        return matrix.length;
    } 
}