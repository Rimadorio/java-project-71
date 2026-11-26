package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DiffEntry;
import java.util.List;

public class JsonFormatter {
  public static String format(List<DiffEntry> diff) throws Exception {
    ObjectMapper mapper = new ObjectMapper();

    return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(diff);
  }
}
