package hexlet.code;

public record DiffEntry(String key, String status, Object value1, Object value2) {
  // status может быть: "added", "removed", "unchanged", "changed"
}
