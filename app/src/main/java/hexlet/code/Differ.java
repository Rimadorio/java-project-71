package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class Differ {

  public static String generate(String filePath1, String filePath2) throws Exception {
    return generate(filePath1, filePath2, "stylish");
  }

  public static String generate(String filePath1, String filePath2, String format)
      throws Exception {
    Map<String, Object> data1 = getData(filePath1);
    Map<String, Object> data2 = getData(filePath2);

    List<DiffEntry> diff = DiffBuilder.build(data1, data2);

    // Делегируем форматирование классу Formatter
    return Formatter.format(diff, format);
  }

  private static Map<String, Object> getData(String filePath) throws Exception {
    Path path = Path.of(filePath);
    String content = Files.readString(path);
    String extension = filePath.substring(filePath.lastIndexOf(".") + 1);
    return Parser.parse(content, extension);
  }
}
