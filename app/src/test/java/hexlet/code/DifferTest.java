package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    String actual = Differ.generate(file1, file2);

    String expected =
        """
            {
                chars1: [a, b, c]
              - chars2: [d, e, f]
              + chars2: false
              - checked: false
              + checked: true
              - default: null
              + default: [value1, value2]
              - id: 45
              + id: null
              - key1: value1
              + key2: value2
                numbers1: [1, 2, 3, 4]
              - numbers2: [2, 3, 4, 5]
              + numbers2: [22, 33, 44, 55]
              - numbers3: [3, 4, 5]
              + numbers4: [4, 5, 6]
              + obj1: {nestedKey=value, isNested=true}
              - setting1: Some value
              + setting1: Another value
              - setting2: 200
              + setting2: 300
              - setting3: true
              + setting3: none
            }""";

    assertEquals(expected, actual);
  }

  @Test
  void testGenerateDiffForNestedYaml() throws Exception {
    String file1 = "src/test/resources/file1_nested.yml";
    String file2 = "src/test/resources/file2_nested.yml";

    String actual = Differ.generate(file1, file2);

    String expected =
        """
            {
                chars1: [a, b, c]
              - chars2: [d, e, f]
              + chars2: false
              - checked: false
              + checked: true
              - default: null
              + default: [value1, value2]
              - id: 45
              + id: null
              - key1: value1
              + key2: value2
                numbers1: [1, 2, 3, 4]
              - numbers2: [2, 3, 4, 5]
              + numbers2: [22, 33, 44, 55]
              - numbers3: [3, 4, 5]
              + numbers4: [4, 5, 6]
              + obj1: {nestedKey=value, isNested=true}
              - setting1: Some value
              + setting1: Another value
              - setting2: 200
              + setting2: 300
              - setting3: true
              + setting3: none
            }""";

    assertEquals(expected, actual);
  }

  @Test
  void testGenerateDiffForNestedJsonPlain() throws Exception {
    String file1 = "src/test/resources/file1_nested.json";
    String file2 = "src/test/resources/file2_nested.json";

    String actual = Differ.generate(file1, file2, "plain");

    String expected =
        """
            Property 'chars2' was updated. From [complex value] to false
            Property 'checked' was updated. From false to true
            Property 'default' was updated. From null to [complex value]
            Property 'id' was updated. From 45 to null
            Property 'key1' was removed
            Property 'key2' was added with value: 'value2'
            Property 'numbers2' was updated. From [complex value] to [complex value]
            Property 'numbers3' was removed
            Property 'numbers4' was added with value: [complex value]
            Property 'obj1' was added with value: [complex value]
            Property 'setting1' was updated. From 'Some value' to 'Another value'
            Property 'setting2' was updated. From 200 to 300
            Property 'setting3' was updated. From true to 'none'""";

    assertEquals(expected, actual);
  }

  @Test
  void testGenerateDiffForNestedYamlPlain() throws Exception {
    String file1 = "src/test/resources/file1_nested.yml";
    String file2 = "src/test/resources/file2_nested.yml";

    String actual = Differ.generate(file1, file2, "plain");

    String expected =
        """
            Property 'chars2' was updated. From [complex value] to false
            Property 'checked' was updated. From false to true
            Property 'default' was updated. From null to [complex value]
            Property 'id' was updated. From 45 to null
            Property 'key1' was removed
            Property 'key2' was added with value: 'value2'
            Property 'numbers2' was updated. From [complex value] to [complex value]
            Property 'numbers3' was removed
            Property 'numbers4' was added with value: [complex value]
            Property 'obj1' was added with value: [complex value]
            Property 'setting1' was updated. From 'Some value' to 'Another value'
            Property 'setting2' was updated. From 200 to 300
            Property 'setting3' was updated. From true to 'none'""";

    assertEquals(expected, actual);
  }

  @Test
  void testGenerateDiffForNestedJsonJson() throws Exception {
    String file1 = "src/test/resources/file1_nested.json";
    String file2 = "src/test/resources/file2_nested.json";

    // Вызываем программу и получаем результат без модификаций
    String actual = Differ.generate(file1, file2, "json");

    String expected =
        """
            [ {
              "key" : "chars1",
              "status" : "unchanged",
              "value1" : [ "a", "b", "c" ],
              "value2" : [ "a", "b", "c" ]
            }, {
              "key" : "chars2",
              "status" : "changed",
              "value1" : [ "d", "e", "f" ],
              "value2" : false
            }, {
              "key" : "checked",
              "status" : "changed",
              "value1" : false,
              "value2" : true
            }, {
              "key" : "default",
              "status" : "changed",
              "value1" : null,
              "value2" : [ "value1", "value2" ]
            }, {
              "key" : "id",
              "status" : "changed",
              "value1" : 45,
              "value2" : null
            }, {
              "key" : "key1",
              "status" : "removed",
              "value1" : "value1",
              "value2" : null
            }, {
              "key" : "key2",
              "status" : "added",
              "value1" : null,
              "value2" : "value2"
            }, {
              "key" : "numbers1",
              "status" : "unchanged",
              "value1" : [ 1, 2, 3, 4 ],
              "value2" : [ 1, 2, 3, 4 ]
            }, {
              "key" : "numbers2",
              "status" : "changed",
              "value1" : [ 2, 3, 4, 5 ],
              "value2" : [ 22, 33, 44, 55 ]
            }, {
              "key" : "numbers3",
              "status" : "removed",
              "value1" : [ 3, 4, 5 ],
              "value2" : null
            }, {
              "key" : "numbers4",
              "status" : "added",
              "value1" : null,
              "value2" : [ 4, 5, 6 ]
            }, {
              "key" : "obj1",
              "status" : "added",
              "value1" : null,
              "value2" : {
                "nestedKey" : "value",
                "isNested" : true
              }
            }, {
              "key" : "setting1",
              "status" : "changed",
              "value1" : "Some value",
              "value2" : "Another value"
            }, {
              "key" : "setting2",
              "status" : "changed",
              "value1" : 200,
              "value2" : 300
            }, {
              "key" : "setting3",
              "status" : "changed",
              "value1" : true,
              "value2" : "none"
            } ]""";

    assertEquals(expected, actual);
  }

  @Test
  void testGenerateDiffForNestedYamlJson() throws Exception {
    String file1 = "src/test/resources/file1_nested.yml";
    String file2 = "src/test/resources/file2_nested.yml";

    String actual = Differ.generate(file1, file2, "json");

    String expected =
        """
            [ {
              "key" : "chars1",
              "status" : "unchanged",
              "value1" : [ "a", "b", "c" ],
              "value2" : [ "a", "b", "c" ]
            }, {
              "key" : "chars2",
              "status" : "changed",
              "value1" : [ "d", "e", "f" ],
              "value2" : false
            }, {
              "key" : "checked",
              "status" : "changed",
              "value1" : false,
              "value2" : true
            }, {
              "key" : "default",
              "status" : "changed",
              "value1" : null,
              "value2" : [ "value1", "value2" ]
            }, {
              "key" : "id",
              "status" : "changed",
              "value1" : 45,
              "value2" : null
            }, {
              "key" : "key1",
              "status" : "removed",
              "value1" : "value1",
              "value2" : null
            }, {
              "key" : "key2",
              "status" : "added",
              "value1" : null,
              "value2" : "value2"
            }, {
              "key" : "numbers1",
              "status" : "unchanged",
              "value1" : [ 1, 2, 3, 4 ],
              "value2" : [ 1, 2, 3, 4 ]
            }, {
              "key" : "numbers2",
              "status" : "changed",
              "value1" : [ 2, 3, 4, 5 ],
              "value2" : [ 22, 33, 44, 55 ]
            }, {
              "key" : "numbers3",
              "status" : "removed",
              "value1" : [ 3, 4, 5 ],
              "value2" : null
            }, {
              "key" : "numbers4",
              "status" : "added",
              "value1" : null,
              "value2" : [ 4, 5, 6 ]
            }, {
              "key" : "obj1",
              "status" : "added",
              "value1" : null,
              "value2" : {
                "nestedKey" : "value",
                "isNested" : true
              }
            }, {
              "key" : "setting1",
              "status" : "changed",
              "value1" : "Some value",
              "value2" : "Another value"
            }, {
              "key" : "setting2",
              "status" : "changed",
              "value1" : 200,
              "value2" : 300
            }, {
              "key" : "setting3",
              "status" : "changed",
              "value1" : true,
              "value2" : "none"
            } ]""";

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
}
