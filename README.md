# ecommerce

collaborative repository to build scalable ecommerce app

## How to Run Development
1. Minimum `Java 11` installed, check in your cmd/terminal.
```
java --version
```
2. Get maven installed, check in your cmd/terminal.
```
mvn --version
```
3. Install latest version of PostgreSQL.
4. Clone this repo 
```
git clone git@github.com:unsri-hackers/ecommerce.git
```
5. Go to root project, and make sure your `application.properties` looks like this
```
spring.datasource.url=jdbc:postgresql://localhost:5432/deuvox
spring.datasource.username=postgres
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQL81Dialect
```
* This is default minimal configuration in order to run the app.
6. You can run directly the app from Intellij IDEA or Vscode. If you prefer to run using cmd, run this on terminal/cmd
```
mvn spring-boot:run
```

## Maintainers

- [@ajailani4](https://github.com/ajailani4)
- [@onirutlA](https://github.com/onirutlA)
- [@aditiyars](https://github.com/aditiyars)
- [@lloistborn](https://github.com/lloistborn)
