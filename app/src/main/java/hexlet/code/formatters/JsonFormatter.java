package hexlet.code.formatters;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DiffEntry;
import java.util.List;

public class JsonFormatter {
  public static String format(List<DiffEntry> diff) throws Exception {
    ObjectMapper mapper = new ObjectMapper();

    DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
    // Принудительно используем \n для отступов объектов
    DefaultIndenter indenter = new DefaultIndenter("  ", "\n");
    printer.indentObjectsWith(indenter);

    // Оставляем массивы в формате [ { ... }, { ... } ] (без переносов строк)
    printer.indentArraysWith(DefaultPrettyPrinter.FixedSpaceIndenter.instance);

    return mapper.writer(printer).writeValueAsString(diff);
  }
}
