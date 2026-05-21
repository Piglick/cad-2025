# Отчет о лаботаротоной работе 1. Gradle. Базовое приложение Spring
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

les02/lab/app/src/main/java/ru/bsuedu/cad/lab/App.java
les02/lab/app/src/main/java/ru/bsuedu/cad/lab/config/AppConfig.java
les02/lab/app/src/main/java/ru/bsuedu/cad/lab/model/Product.java
les02/lab/app/src/main/java/ru/bsuedu/cad/lab/reader/Reader.java
les02/lab/app/src/main/java/ru/bsuedu/cad/lab/reader/ResourceFileReader.java
les02/lab/app/src/main/java/ru/bsuedu/cad/lab/parser/Parser.java
les02/lab/app/src/main/java/ru/bsuedu/cad/lab/parser/CSVParser.java
les02/lab/app/src/main/java/ru/bsuedu/cad/lab/provider/ProductProvider.java
les02/lab/app/src/main/java/ru/bsuedu/cad/lab/provider/ConcreteProductProvider.java
les02/lab/app/src/main/java/ru/bsuedu/cad/lab/renderer/Renderer.java
les02/lab/app/src/main/java/ru/bsuedu/cad/lab/renderer/ConsoleTableRenderer.java

Файл с товарами расположен по пути:
les02/lab/app/src/main/resources/product.csv

Файл настройки зависимостей Gradle:
les02/lab/app/build.gradle.kts
## Выводы
В ходе выполнения лабораторной работы был создан Gradle-проект Java Application, подключена библиотека Spring Context и реализовано консольное Spring-приложение.

Приложение читает данные о товарах для магазина товаров для животных из CSV-файла и выводит их в консоль в виде таблицы.

В работе были использованы Java-конфигурация Spring, Spring Beans, интерфейсы и внедрение зависимостей через конструктор.