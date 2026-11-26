package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

class DifferTest {


    @Test
    void testGenerateDiffForFlatJson() throws Exception {
        String file1 = "src/test/resources/file1.json";
        String file2 = "src/test/resources/file2.json";

        String actual = Differ.generate(file1, file2);

        String expected =
                """
                        {
                          - follow: false
                            host: hexlet.io
                          - proxy: 123.234.53.22
                          - timeout: 50
                          + timeout: 20
                          + verbose: true
                        }""";

        assertEquals(expected, actual);
    }


    @Test
    void testGenerateDiffForFlatYaml() throws Exception {
        String file1 = "src/test/resources/file1.yml";
        String file2 = "src/test/resources/file2.yml";

        String actual = Differ.generate(file1, file2);

        String expected =
                """
                        {
                          - follow: false
                            host: hexlet.io
                          - proxy: 123.234.53.22
                          - timeout: 50
                          + timeout: 20
                          + verbose: true
                        }""";

        assertEquals(expected, actual);
    }


    @Test
    void testGenerateDiffForNestedJson() throws Exception {
        String file1 = "src/test/resources/file1_nested.json";
        String file2 = "src/test/resources/file2_nested.json";
        String expectedFile = "src/test/resources/expected_nested.txt";

        String actual = Differ.generate(file1, file2);
        String expected = Files.readString(Path.of(expectedFile)).trim().replace("\r\n", "\n");

        assertEquals(expected, actual);
    }


    @Test
    void testGenerateDiffForNestedYaml() throws Exception {
        String file1 = "src/test/resources/file1_nested.yml";
        String file2 = "src/test/resources/file2_nested.yml";
        String expectedFile = "src/test/resources/expected_nested.txt";

        String actual = Differ.generate(file1, file2);
        String expected = Files.readString(Path.of(expectedFile)).trim().replace("\r\n", "\n");

        assertEquals(expected, actual);
    }


    @Test
    void testGenerateDiffForNestedJsonPlain() throws Exception {
        String file1 = "src/test/resources/file1_nested.json";
        String file2 = "src/test/resources/file2_nested.json";
        String expectedFile = "src/test/resources/expected_plain.txt";

        String actual = Differ.generate(file1, file2, "plain");
        String expected = Files.readString(Path.of(expectedFile)).trim().replace("\r\n", "\n");

        assertEquals(expected, actual);
    }


    @Test
    void testGenerateDiffForNestedYamlPlain() throws Exception {
        String file1 = "src/test/resources/file1_nested.yml";
        String file2 = "src/test/resources/file2_nested.yml";
        String expectedFile = "src/test/resources/expected_plain.txt";

        String actual = Differ.generate(file1, file2, "plain");
        String expected = Files.readString(Path.of(expectedFile)).trim().replace("\r\n", "\n");

        assertEquals(expected, actual);
    }


    @Test
    void testUnknownFormatThrowsException() {
        String file1 = "src/test/resources/file1_nested.json";
        String file2 = "src/test/resources/file2_nested.json";

        Exception exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> {
                            Differ.generate(file1, file2, "unknown");
                        });

        assertTrue(exception.getMessage().contains("Unknown format: unknown"));
    }

    @Test
    void testGenerateDiffForNestedJsonJson() throws Exception {
        String file1 = "src/test/resources/file1_nested.json";
        String file2 = "src/test/resources/file2_nested.json";
        String expectedFile = "src/test/resources/expected_json.json";


        String actual = Differ.generate(file1, file2, "json").replace("\r\n", "\n").trim();

        String expected = Files.readString(Path.of(expectedFile)).replace("\r\n", "\n").trim();

        assertEquals(expected, actual);
    }


    @Test
    void testGenerateDiffForNestedYamlJson() throws Exception {
        String file1 = "src/test/resources/file1_nested.yml";
        String file2 = "src/test/resources/file2_nested.yml";
        String expectedFile = "src/test/resources/expected_json.json";


        String actual = Differ.generate(file1, file2, "json").replace("\r\n", "\n").trim();

        String expected = Files.readString(Path.of(expectedFile)).replace("\r\n", "\n").trim();

        assertEquals(expected, actual);
    }
}
