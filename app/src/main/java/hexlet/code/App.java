package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.concurrent.Callable;

@Command(
        name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.",
        showDefaultValues = true // Включает отображение [default: stylish] в справке
)
public class App implements Callable<Integer> {

    // Два обязательных позиционных параметра
    @Parameters(index = "0", description = "path to first file")
    private String filepath1;

    @Parameters(index = "1", description = "path to second file")
    private String filepath2;

    // Опция формата вывода
    @Option(
            names = {"-f", "--format"},
            description = "output format",
            defaultValue = "stylish"
    )
    private String format;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        // На этом шаге логики сравнения пока нет.
        // Для проверки того, что параметры считываются, можно вывести их в консоль.
        // Позже здесь будет вызов Differ.generate(filepath1, filepath2, format).

        System.out.println("File 1: " + filepath1);
        System.out.println("File 2: " + filepath2);
        System.out.println("Format: " + format);

        return 0;
    }
}