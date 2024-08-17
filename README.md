# Sprint_7

### Запуск проекта:
```shell
mvn clean test
```

## Объект тестирования:
API cервиса Яндекс.Самокат
(https://qa-scooter.praktikum-services.ru/)

## Содержание проекта:
* Тесты для ручки создания курьера (_POST /api/v1/courier_)
* Тесты для ручки логина курьера (_POST /api/v1/courier/login_)
* Тесты для ручки создания заказа (_POST /api/v1/orders_)
* Тест на получение списка заказов (_GET /api/v1/orders_)

## Используемые технологии:

| **Название**          | **Версия** |
|-----------------------|------------|
| AspectJ               | 1.9.7      |
| JUnit 4               | 4.13.2     |
| REST Assured          | 5.5.0      |
| Gson                  | 2.8.9      |
| Allure                | 2.15.0     |
| Maven Surefire plugin | 3.3.1      |
| Maven Allure plugin   | 2.10.0     |

