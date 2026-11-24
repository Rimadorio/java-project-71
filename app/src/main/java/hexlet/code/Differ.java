package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

public class Differ {

  public static String generate(String filePath1, String filePath2) throws Exception {
    Map<String, Object> data1 = getData(filePath1);
    Map<String, Object> data2 = getData(filePath2);

    // TreeSet автоматически сортирует ключи в алфавитном порядке и убирает дубликаты
    TreeSet<String> allKeys = new TreeSet<>(data1.keySet());
    allKeys.addAll(data2.keySet());

    StringBuilder diff = new StringBuilder("{\n");

    for (String key : allKeys) {
      boolean inFirst = data1.containsKey(key);
      boolean inSecond = data2.containsKey(key);
      Object val1 = data1.get(key);
      Object val2 = data2.get(key);

      if (inFirst && inSecond) {
        if (Objects.equals(val1, val2)) {
          // Ключ есть в обоих файлах, значения совпадают
          diff.append("    ").append(key).append(": ").append(val1).append("\n");
        } else {
          // Ключ есть в обоих файлах, но значения разные
          diff.append("  - ").append(key).append(": ").append(val1).append("\n");
          diff.append("  + ").append(key).append(": ").append(val2).append("\n");
        }
      } else if (inFirst) {
        // Ключ удален (есть только в первом)
        diff.append("  - ").append(key).append(": ").append(val1).append("\n");
      } else {
        // Ключ добавлен (есть только во втором)
        diff.append("  + ").append(key).append(": ").append(val2).append("\n");
      }
    }

    diff.append("}"); // Закрывающая скобка без \n на конце
    return diff.toString();
  }

  public static Map<String, Object> getData(String filePath) throws Exception {
    Path path = Path.of(filePath);
    String content = Files.readString(path);
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(content, new TypeReference<Map<String, Object>>() {});
  }
}
