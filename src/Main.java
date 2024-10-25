import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int gap = -2;
        int match = 1;
        int missmatch = -1;
        File file = new File("D:\\Java\\JavaProjects\\Needleman-Wunsch_algorithm\\src\\seq.fasta");
        FastaReader fastaReader = new FastaReader();
        List<String> sequences = fastaReader.getSequences(file);
        System.out.println(sequences);
        MatrixMenagment matrixMenagment = new MatrixMenagment(gap,match,missmatch,sequences);
        int [][]matrix = matrixMenagment.matrixCrafter();
        matrixMenagment.printMatrix(matrix);
        List<Integer>ans = matrixMenagment.answear();
        System.out.println(ans);
    }
}