import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FastaReader {
    File file;
    List<String> sequences = new ArrayList<>();

    public List<String> getSequences(File file) {
        this.file = file;
        return setSeqquence();
    }

    private List<String> setSeqquence() {
        String tmp = "";
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String seq = sc.nextLine();
                if (seq.contains(">")) {
                    if (!tmp.isEmpty()) {
                        sequences.add(tmp);
                        tmp = "";
                    }
                } else {
                    tmp += seq.strip().toUpperCase();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        if (!tmp.isEmpty()) {
            sequences.add(tmp);
        }
        return sequences;
    }
}
