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
        showDefaultValues = true
)
public class App implements Callable<Integer> {

    @Parameters(index = "0", description = "path to first file")
    private String filepath1;

    @Parameters(index = "1", description = "path to second file")
    private String filepath2;

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
        // Вызываем наш Differ. Если файлы не найдутся или будут битые,
        // исключение пробросится наверх, как и просили в задании.
        String result = Differ.generate(filepath1, filepath2);

        // На данном шаге result - это просто заглушка, выводим ее
        System.out.println(result);

        return 0;
    }
}