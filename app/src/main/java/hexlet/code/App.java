package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.concurrent.Callable;

@Command(
        name = "app",
        mixinStandardHelpOptions = true,
        version = "app 1.0",
        description = "Пример консольного приложения на Picocli."
)
public class App implements Callable<Integer> {

    // Пример опционального параметра (опции)
    @Option(names = {"-v", "--verbose"}, description = "Включить подробный вывод")
    private boolean verbose;

    // Пример обязательного позиционного параметра
    @Parameters(index = "0", description = "Имя пользователя для приветствия")
    private String name;

    public static void main(String[] args) {
        // Создаем экземпляр команды и запускаем его
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        // Здесь размещается основная логика приложения
        if (verbose) {
            System.out.println("Режим подробного вывода включен.");
        }
        System.out.printf("Привет, %s!%n", name);
        return 0; // Код успешного завершения
    }
}