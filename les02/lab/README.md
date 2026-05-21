# Отчет о лаботаротоной работе 1. Gradle. Базовое приложение Spring
Выполнил: Уваров Владислав Александрович, группа 12002453

## Цель работы
Создать каркас Java-приложения с использованием Gradle, подключить библиотеку Spring Context и реализовать консольное приложение для чтения данных о товарах из CSV-файла и вывода их в виде таблицы.
## Используемые инструменты
- Java 17
- Axiom JDK 17
- Gradle 8.12
- IntelliJ IDEA
- Spring Context 6.2.2
- Git / GitHub
## Выполнение работы

Были созданы следующие компоненты:

Product — класс, описывающий товар магазина;

Reader и ResourceFileReader — интерфейс и класс для чтения CSV-файла;

Parser и CSVParser — интерфейс и класс для разбора CSV-данных;

ProductProvider и ConcreteProductProvider — интерфейс и класс для получения списка товаров;

Renderer и ConsoleTableRenderer — интерфейс и класс для вывода товаров в консоль;

AppConfig — класс Java-конфигурации Spring;

App — основной класс запуска приложения.

Основной код приложения расположен в директории: app/src/main/java/ru/bsuedu/cad/lab

Структура пакетов:
```
ru.bsuedu.cad.lab
├── App.java
├── config
│   └── AppConfig.java
├── model
│   └── Product.java
├── reader
│   ├── Reader.java
│   └── ResourceFileReader.java
├── parser
│   ├── Parser.java
│   └── CSVParser.java
├── provider
│   ├── ProductProvider.java
│   └── ConcreteProductProvider.java
└── renderer
    ├── Renderer.java
    └── ConsoleTableRenderer.java
```
Файл с товарами расположен по пути:
les02/lab/app/src/main/resources/product.csv

### 1. Настройка проекта

les02/lab/app/build.gradle.kts
- Установлен JDK 17 и Gradle 8.12 согласно инструкциям.
- В папке `les02/lab` выполнена команда:
  gradle init --type java-application --package ru.bsuedu.cad.lab --project-name product-table --java-version 17 --dsl kotlin --test-framework junit-jupiter
- В app/build.gradle.kts добавлена зависимость spring-context:6.2.2 и настройка кодировки компиляции UTF-8.

### 2. Разработка классов

В приложении были созданы классы и интерфейсы согласно диаграмме из задания.

Класс `Product` описывает товар магазина. В нём хранятся данные о товаре: идентификатор, название, описание, категория, цена, количество, ссылка на изображение и даты создания/обновления.

Интерфейс `Reader` и класс `ResourceFileReader` отвечают за чтение CSV-файла `product.csv` из папки `resources`.

Интерфейс `Parser` и класс `CSVParser` отвечают за разбор CSV-файла. `CSVParser` пропускает строку с заголовками и преобразует остальные строки в объекты `Product`.

Интерфейс `ProductProvider` и класс `ConcreteProductProvider` используются для получения списка товаров. `ConcreteProductProvider` объединяет работу `Reader` и `Parser`.

Интерфейс `Renderer` и класс `ConsoleTableRenderer` отвечают за вывод списка товаров в консоль в виде таблицы.

Класс `App` является точкой входа в программу. Он запускает Spring-контейнер, получает из него `Renderer` и вызывает метод `render()`.

## Выводы
В ходе выполнения лабораторной работы был создан Gradle-проект Java Application, подключена библиотека Spring Context и реализовано консольное Spring-приложение.

Приложение читает данные о товарах для магазина товаров для животных из CSV-файла и выводит их в консоль в виде таблицы.

В работе были использованы Java-конфигурация Spring, Spring Beans, интерфейсы и внедрение зависимостей через конструктор.