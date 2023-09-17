# Sber-BeautifulCode

# Инструкция по запуску

1. Соберите проект
```shell
mvn clean install
```
2. Соберите Docker контейнер
```shell
docker build --tag=sber-beautiful-code:latest .
```

3. Запустите Docker контейнер
```shell
docker run -p 8080:8080 sber-beautiful-code:latest
```

# Запуск всех тестов
Выполните команду
```shell
mvn test
```

# Документация к API
Документация сервиса написана с использованием Open API и доступна по [адресу](http://localhost:8080/swagger-ui/index.html)