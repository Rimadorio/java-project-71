package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

public class DiffBuilder {
    public static List<DiffEntry> build(Map<String, Object> data1, Map<String, Object> data2) {
        TreeSet<String> allKeys = new TreeSet<>(data1.keySet());
        allKeys.addAll(data2.keySet());

        List<DiffEntry> diff = new ArrayList<>();

        for (String key : allKeys) {
            boolean inFirst = data1.containsKey(key);
            boolean inSecond = data2.containsKey(key);
            Object val1 = data1.get(key);
            Object val2 = data2.get(key);

            if (inFirst && inSecond) {
                if (Objects.equals(val1, val2)) {
                    diff.add(new DiffEntry(key, "unchanged", val1, val2));
                } else {
                    diff.add(new DiffEntry(key, "changed", val1, val2));
                }
            } else if (inFirst) {
                diff.add(new DiffEntry(key, "removed", val1, null));
            } else {
                diff.add(new DiffEntry(key, "added", null, val2));
            }
        }
        return diff;
    }
}
