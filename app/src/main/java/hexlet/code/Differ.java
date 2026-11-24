package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        // Читаем и парсим оба файла
        Map<String, Object> data1 = getData(filePath1);
        Map<String, Object> data2 = getData(filePath2);

        // Пока логики сравнения нет, просто выведем содержимое в консоль для проверки
        System.out.println("File 1 content: " + data1);
        System.out.println("File 2 content: " + data2);

        // Заглушка, на следующем шаге здесь будет логика сравнения
        return "Diff logic will be implemented soon";
    }

    // Метод для чтения и парсинга файла
    public static Map<String, Object> getData(String filePath) throws Exception {
        Path path = Path.of(filePath);
        // Читаем весь файл в строку
        String content = Files.readString(path);

        // Используем Jackson для преобразования строки в Map
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, new TypeReference<Map<String, Object>>() {});
    }
}