import java.util.ArrayList;
import java.util.List;

public class MatrixMenagment {
    int gap;
    int match;
    int missmatch;
    String seq1 = "";
    String seq2 = "";
    int[][] matrix;

    MatrixMenagment(int gap, int match, int missmatch, List<String> sequences) {
        this.gap = gap;
        this.match = match;
        this.missmatch = missmatch;
        this.seq1 = sequences.get(0);
        this.seq2 = sequences.get(1);
    }

    public List<Integer> answear() {
        return pathFider();
    }

    private List<Integer> pathFider() {
        List<Integer> path = new ArrayList<>();
        int i = matrix.length - 1;
        int j = matrix[0].length - 1;
        path.add(matrix[i][j]);
        while (i != 0 && j != 0) {
            int curr = matrix[i][j];
            int up = matrix[i - 1][j];
            int left = matrix[i][j - 1];
            int diagonal = matrix[i - 1][j - 1];
            String cell = findDestination(curr, up, left, diagonal);
            if(cell == "Up") {
                if(i>=1){
                    i-=1;
                }
            } else if (cell == "Left") {
                if(j>=1){
                    j-=1;
                }
            } else if (cell == "Diagonal") {
                if(j>=1 && i >=1){
                    i-=1;
                    j-=1;
                }
            }
            path.add(matrix[i][j]);
        }
        return path.reversed();
    }

    private String findDestination(int curr, int up, int left, int diagonal) {
        if (up + gap == curr) {
            return "Up";
        } else if (left + gap == curr) {
            return "Left";
        }
        return "Diagonal";
    }

    public int[][] matrixCrafter() {
        return MatrixLoader();
    }

    private int[][] MatrixLoader() {
        int seq1len = seq1.length();
        int seq2len = seq2.length();
        matrix = new int[seq1len + 1][seq2len + 1];
        matrix[0][0] = 0;
        for (int row = 1; row < seq1len + 1; row++) {
            matrix[row][0] = matrix[row - 1][0] + gap;
        }
        for (int column = 1; column < seq2len + 1; column++) {
            matrix[0][column] = matrix[0][column - 1] + gap;
        }

        for (int row = 1; row < seq1len + 1; row++) {
            for (int column = 1; column < seq2len + 1; column++) {
                int up = matrix[row - 1][column] + gap;
                int left = matrix[row][column - 1] + gap;
                int diagonal = matrix[row - 1][column - 1];
                if (seq1.charAt(row - 1) == seq2.charAt(column - 1)) {
                    diagonal += match;
                    matrix[row][column] = threeMax(up, diagonal, left);
                } else {
                    diagonal += missmatch;
                    matrix[row][column] = threeMax(up, diagonal, left);
                }
            }
        }
        return matrix;
    }

    private int threeMax(int up, int diagonal, int left) {
        if (up >= left && up >= diagonal) {
            return up;
        } else if (left >= up && left >= diagonal) {
            return left;
        }
        return diagonal;
    }

    public void printMatrix(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                System.out.print(matrix[row][column] + " ");
            }
            System.out.println("\n");
        }
    }
}
