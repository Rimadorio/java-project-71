package hexlet.code.formatters;

import hexlet.code.DiffEntry;
import java.util.List;

public class StylishFormatter {
  public static String format(List<DiffEntry> diff) {
    StringBuilder sb = new StringBuilder("{\n");

    for (DiffEntry entry : diff) {
      switch (entry.status()) {
        case "unchanged":
          sb.append("    ")
              .append(entry.key())
              .append(": ")
              .append(formatValue(entry.value1()))
              .append("\n");
          break;
        case "removed":
          sb.append("  - ")
              .append(entry.key())
              .append(": ")
              .append(formatValue(entry.value1()))
              .append("\n");
          break;
        case "added":
          sb.append("  + ")
              .append(entry.key())
              .append(": ")
              .append(formatValue(entry.value2()))
              .append("\n");
          break;
        case "changed":
          sb.append("  - ")
              .append(entry.key())
              .append(": ")
              .append(formatValue(entry.value1()))
              .append("\n");
          sb.append("  + ")
              .append(entry.key())
              .append(": ")
              .append(formatValue(entry.value2()))
              .append("\n");
          break;
        default:
          throw new IllegalArgumentException("Unknown status: " + entry.status());
      }
    }

    sb.append("}");
    return sb.toString();
  }

  private static String formatValue(Object value) {
    if (value == null) {
      return "null";
    }

    return value.toString();
  }
}
