package hexlet.code;

import java.util.concurrent.Callable;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(
    name = "gendiff",
    mixinStandardHelpOptions = true,
    version = "gendiff 1.0",
    description = "Compares two configuration files and shows a difference.",
    showDefaultValues = true)
public class App implements Callable<Integer> {

  @Parameters(index = "0", description = "path to first file")
  private String filepath1;

  @Parameters(index = "1", description = "path to second file")
  private String filepath2;

  @Option(
      names = {"-f", "--format"},
      description = "output format",
      defaultValue = "stylish")
  private String format;

  public static void main(String[] args) {
    int exitCode = new CommandLine(new App()).execute(args);
    System.exit(exitCode);
  }

  @Override
  public Integer call() throws Exception {

    String result = Differ.generate(filepath1, filepath2, format);

    System.out.println(result);

    return 0;
  }
}
