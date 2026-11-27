# Вычислитель отличий (Java)

[![hexlet-check](https://github.com/Rimadorio/java-project-71/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/Rimadorio/java-project-71/actions)
[![Java CI](https://github.com/Rimadorio/java-project-71/actions/workflows/push.yml/badge.svg)](https://github.com/Rimadorio/java-project-71/actions/workflows/push.yml)

Консольная утилита, которая сравнивает два файла JSON или YAML по ключам первого уровня и выводит разницу в трёх форматах. Вы разбираете аргументы командной строки через picocli, читаете оба формата через Jackson и выносите каждый формат вывода в свой класс. Тесты на JUnit вы пишете сами, а сборка в GitHub Actions проверяет стиль и порог покрытия.

Учебный проект Хекслета: https://ru.hexlet.io/programs/java
Как это должно работать: https://asciinema.org/a/NFIQgLVMu1ymFsqg4ESeOQeXi

## Стек

- Java

## Установка

```bash
git clone https://github.com/Rimadorio/java-project-71.git
cd java-project-71
```

## Использование

* [Сравнение плоских JSON-файлов](https://asciinema.org/a/jYoSuAYppjp83NFF) - находит различия между двумя плоскими JSON-файлами и выводит их в формате со знаками + и -. Ключи отсортированы по алфавиту.
* [Сравнение плоских YAML-файлов](https://asciinema.org/a/KaqUoSwLTcv2TD3D) - поиск различий между двумя плоскими (только пары ключ-значение) yaml файлами
* [Сравнение вложенных структур (по умолчанию)](https://asciinema.org/a/ZAa14r4FkRr7dnH6) - gendiff сравнивает файлы с вложенными структурами в формате stylish по умолчанию
* [Плоский формат (флаг -f plain)](https://asciinema.org/a/U7O5zjjD2pjvv3gd) - gendiff -f plain выводит разницу в текстовом формате.
* [Формат JSON (флаг -f json)](https://asciinema.org/a/xh7ascRSQOoM28iu) - все три формата работают: stylish (по умолчанию), plain, json.
---

<details>
<summary>Автоматические тесты Хекслета</summary>

Тесты запускаются на каждый коммит. За запуск отвечает файл `.github/workflows/hexlet-check.yml` — не удаляйте и не переименовывайте ни его, ни репозиторий.

</details>

## О Хекслете

[Хекслет](https://ru.hexlet.io/) — школа программирования: авторские программы обучения с практикой, поддержкой наставников и реальными проектами, которые остаются в резюме. Этот репозиторий — один из таких проектов.
