package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
