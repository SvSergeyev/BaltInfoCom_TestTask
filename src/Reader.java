import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    private final List<String> lines = new ArrayList<>();

    public List<String> read() {
        try (BufferedReader reader = new BufferedReader(new FileReader("lng.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("\"")) {
                    line = line.replace("\"", "");
                }
                if (lineValidityCheck(line)) {
                    lines.add(line);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.printf("Read %d lines\n", lines.size());
        return lines;
    }

    private boolean lineValidityCheck(String line) {
        return line.matches("\\d*;\\d*;\\d*");
    }
}
