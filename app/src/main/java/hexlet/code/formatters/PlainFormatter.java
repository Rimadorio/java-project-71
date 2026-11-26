package hexlet.code.formatters;

import hexlet.code.DiffEntry;

import java.util.List;
import java.util.Map;

public class PlainFormatter {
    public static String format(List<DiffEntry> diff) {
        StringBuilder sb = new StringBuilder();

        for (DiffEntry entry : diff) {
            String key = entry.key();
            switch (entry.status()) {
                case "added":
                    sb.append("Property '")
                            .append(key)
                            .append("' was added with value: ")
                            .append(formatValue(entry.value2()))
                            .append("\n");
                    break;
                case "removed":
                    sb.append("Property '").append(key).append("' was removed\n");
                    break;
                case "changed":
                    sb.append("Property '")
                            .append(key)
                            .append("' was updated. From ")
                            .append(formatValue(entry.value1()))
                            .append(" to ")
                            .append(formatValue(entry.value2()))
                            .append("\n");
                    break;
                case "unchanged":

                    break;
                default:
                    throw new IllegalArgumentException("Unknown status: " + entry.status());
            }
        }


        return sb.toString().trim();
    }

    private static String formatValue(Object value) {
        if (isComplex(value)) {
            return "[complex value]";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        return String.valueOf(value);
    }

    private static boolean isComplex(Object value) {

        return value instanceof Map || value instanceof List || value instanceof Object[];
    }
}
