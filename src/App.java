import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        List<List<String>> collection = new Sorter().sort(new Reader("lng.csv").read());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("sorted_list.csv"))) {
            long total = collection.stream().filter(list -> list.size() > 1).count();

            writer.write("Total numbers of group is longer than 1 element: " + total + "\n");

            collection.stream()
                    .filter(list -> list.size() > 1)
                    .sorted(new CollectionSizeComparator().reversed())
                    .forEach(list -> {
                        StringBuilder sb = new StringBuilder();
                        for (String line : list) {
                            sb.append(line).append("\n");
                        }
                        sb.append("\n");
                        try {
                            writer.write(String.valueOf(sb));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.printf("Execution time of all methods of the program: %s\n", System.currentTimeMillis() - start);

    }
}
