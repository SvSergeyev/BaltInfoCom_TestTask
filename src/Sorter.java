import java.util.*;

public class Sorter {
    public List<List<String>> sort(List<String> unsortedList) {
        long startTime = System.currentTimeMillis();
        List<List<String>> result = new ArrayList<>();
        List<Map<String, Integer>> listOfPartsGroupedByPositionInLine = new ArrayList<>();

        for (String line : unsortedList) {
            String[] parts = line.split(";");
            TreeSet<Integer> intersectionWithOtherGroups = new TreeSet<>();
            List<Element> elements = new ArrayList<>();

            for (int i = 0; i < parts.length; i++) {
                String part = parts[i];
                if (!"".equals(part)) {
                    if (listOfPartsGroupedByPositionInLine.size() == i) {
                        listOfPartsGroupedByPositionInLine.add(new HashMap<>());
                    }
                    Map<String, Integer> partsGroupedByPositionInLine = listOfPartsGroupedByPositionInLine.get(i);
                    Integer partLineNumber = partsGroupedByPositionInLine.get(part);
                    if (partLineNumber == null) {
                        elements.add(new Element(part, i));
                    } else {
                        intersectionWithOtherGroups.add(partLineNumber);
                    }
                }
            }
            int groupNumber;
            if (intersectionWithOtherGroups.isEmpty()) {
                groupNumber = result.size();
                result.add(new ArrayList<>());
            } else {
                groupNumber = intersectionWithOtherGroups.first();
            }
            for (Element element : elements) {
                listOfPartsGroupedByPositionInLine.get(element.getPositionInLine()).put(element.getValue(), groupNumber);
            }

            for (Integer intersectionNumber : intersectionWithOtherGroups) {
                if (!intersectionNumber.equals(groupNumber)) {
                    result.get(groupNumber).addAll(result.get(intersectionNumber));
                    result.set(intersectionNumber, null);
                }
            }

            List<String> tempCollection = result.get(groupNumber);
            boolean hasDuplicate = false;
            for (String elem : tempCollection) {
                if (line.equals(elem)) {
                    hasDuplicate = true;
                    break;
                }
            }
            if (!hasDuplicate)
                result.get(groupNumber).add(line);
        }
        result.removeIf(Objects::isNull);
        System.out.printf("Found %s groups\n", (int) result.stream().filter(list -> list.size() > 1).count());
        System.out.printf("Sorting execution time: %d ms\n", System.currentTimeMillis() - startTime);
        return result;
    }
}
