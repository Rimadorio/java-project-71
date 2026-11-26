package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.util.Map;

public class Parser {
  public static Map<String, Object> parse(String content, String format) throws Exception {
    // В зависимости от расширения выбираем нужный маппер
    ObjectMapper mapper =
        switch (format.toLowerCase()) {
          case "yaml", "yml" -> new YAMLMapper();
          default -> new ObjectMapper(); // По умолчанию JSON
        };

    return mapper.readValue(content, new TypeReference<Map<String, Object>>() {});
  }
}
