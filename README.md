# Дипломный проект по курсу «Тестировщик ПО»

### Тестирование сервиса покупки тура

## Документация
- [План автоматизации тестирования](https://github.com/usoltsevjr/qa-diplom/blob/master/Plan.md)
- [Отчет по итогам тестирования]
- [Отчет по итогам автоматизации]

## Перед началом работы
*Необходимо, чтобы были уставновлены:*
- IntelliJ IDEA
- Docker desktop / docker toolbox
1. Используя команду `git clone https://github.com/usoltsevjr/qa-diplom.git` скачать себе данный репозиторий

2. **Запускаем docker-контейнер с MySQL , PostgreSQL и Node.js.**
- ввести в терминале(консоле) команду `docker-compose up`

3.**Для запуска SUT открываем новую вкладку в Терминале IDEA и вводим следующую команду:**
- для **MySQL**:
`java -Dspring.datasource.url=jdbc:mysql://192.168.99.100:3306/app -jar artifacts/aqa-shop.jar`
- для **PostgreSQL**:
`java -Dspring.datasource-postgresql.url=jdbc:postgresql://192.168.99.100:5432/app -jar artifacts/aqa-shop.jar`
 
 4.**Для запуска авто-тестов необходимо открыть новую вкладку в теминале и ввести команду:**
 `gradlew clean test`
 
 5.**Для создания отчета Allure ввести в терминале команду:**
 `gradlew allureServe`
 