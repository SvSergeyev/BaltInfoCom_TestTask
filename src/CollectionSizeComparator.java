import java.util.Comparator;
import java.util.List;

public class CollectionSizeComparator implements Comparator<List<String>> {

    @Override
    public int compare(List<String> o1, List<String> o2) {
        return (Integer.compare(o1.size(), o2.size()));
    }
}
