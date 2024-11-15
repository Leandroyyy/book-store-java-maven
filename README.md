# Criando um Projeto Java Maven do Zero via Linha de Comando

## 1. Verifique a instalação do Maven

Antes de começar, certifique-se de que o Maven está instalado e configurado corretamente. Para verificar, execute:

```bash
mvn -v
```

Se o Maven estiver instalado corretamente, esse comando exibirá a versão do Maven e detalhes da configuração.

## 2. Crie um projeto Maven

Use o comando `mvn archetype:generate` para criar um novo projeto. Este comando irá gerar a estrutura inicial do projeto com base em um arquétipo (template).

### Comando:

```bash
mvn archetype:generate -DgroupId=com.exemplo.projeto -DartifactId=meu-projeto -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

### Explicação dos parâmetros:

- **`-DgroupId`**: O identificador do grupo, geralmente no formato de domínio invertido (`com.exemplo.projeto`).
- **`-DartifactId`**: O nome do artefato/projeto (`meu-projeto`).
- **`-DarchetypeArtifactId`**: O tipo de template/archetype para criar o projeto. O `maven-archetype-quickstart` é um template padrão para projetos simples.
- **`-DinteractiveMode=false`**: Cria o projeto de forma não interativa (sem perguntar nada no terminal).

## 3. Estrutura do projeto gerado

O comando acima irá criar uma estrutura de projeto similar a esta:

```
meu-projeto
├── pom.xml
└── src
    ├── main
    │   └── java
    │       └── com
    │           └── exemplo
    │               └── projeto
    │                   └── App.java
    └── test
        └── java
            └── com
                └── exemplo
                    └── projeto
                        └── AppTest.java
```

## 4. Navegue até o diretório do projeto e compile

```bash
cd meu-projeto
mvn compile
```

## 5. Execute o projeto

Para executar a classe principal (por exemplo, `App.java`), você pode usar o plugin Maven Exec:

```bash
mvn exec:java -Dexec.mainClass="com.exemplo.projeto.App"
```

### Instalação do Plugin Maven Exec (caso não esteja configurado)

Adicione ao `pom.xml` se necessário:

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>exec-maven-plugin</artifactId>
            <version>3.0.0</version>
        </plugin>
    </plugins>
</build>
```

## 6. Criando um projeto com dependências via Spring Initializr

### Passos para usar o Spring Initializr:

1. Acesse [Spring Initializr](https://start.spring.io/).
2. Selecione as dependências necessárias:
   - **Oracle Driver**
   - **Spring Boot DevTools**
   - **Spring Web**
   - **Spring Data JPA**
3. Clique em "Generate" para baixar o projeto.

### Copie o conteúdo relevante do `pom.xml` gerado:

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.3.5</version>
    <relativePath/>
</parent>

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>com.oracle.database.jdbc</groupId>
        <artifactId>ojdbc8</artifactId>
        <scope>runtime</scope>
    </dependency>
     <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <optional>true</optional>
    </dependency>
</dependencies>

<build>
    <plugins>
        <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <excludes>
                    <exclude>
                        <groupId>org.projectlombok</groupId>
                        <artifactId>lombok</artifactId>
                    </exclude>
                </excludes>
            </configuration>
        </plugin>
    </plugins>
</build>
```

## 7. Configurando a classe principal do projeto

Crie a classe principal para iniciar o servidor Spring Boot:

Crie um arquivo `BookStoreApplication.java` em `src/main/java/br/com/fiap` com o seguinte conteúdo:

```java
package br.com.fiap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookStoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class, args);
    }
}
```

Agora, seu projeto está pronto para rodar o servidor Spring Boot.

## 8. Rodando o projeto Spring Boot

Para iniciar o servidor, navegue até o diretório do projeto e execute:

```bash
mvn spring-boot:run
```

## 9. Configurando a conexão com o Oracle no `application.properties`

Crie o arquivo `application.properties` em `src/main/resources` e adicione as seguintes configurações para conectar ao banco de dados Oracle:

```properties
spring.datasource.url=jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl
spring.datasource.username=seu_user
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

#### Explicação:

- **`spring.datasource.url`**: A URL de conexão com o banco de dados Oracle (ajuste `localhost`, `1521` e `xe` conforme sua configuração).
- **`spring.datasource.username`**: O nome do usuário do banco de dados.
- **`spring.datasource.password`**: A senha do usuário do banco de dados.
- **`spring.datasource.driver-class-name`**: O driver JDBC necessário para a conexão com o Oracle (`oracle.jdbc.OracleDriver`).
- **`spring.jpa.hibernate.ddl-auto`**: Define o comportamento do Hibernate em relação à criação do banco de dados (neste caso, `update` atualiza a estrutura do banco de dados).
- **`spring.jpa.show-sql`**: Exibe as consultas SQL no console para depuração.

## 10. Rodando o projeto Spring Boot

Para iniciar o servidor, navegue até o diretório do projeto e execute:

```bash
mvn spring-boot:run
```

## 11. Criando o Controller `BookStoreController`

No diretório `src/main/java/br/com/fiap/controller`, crie o arquivo `BookStoreController.java`:

```java
package br.com.fiap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookStoreController {

    @GetMapping
    public String getBooks() {
        return "Lista de livros disponível";
    }
}
```

## 12. Criando a pasta `entities` e adicionando a classe `Book`

No diretório `src/main/java/br/com/fiap/entities`, crie o arquivo `Book.java`:

```java
package br.com.fiap.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "role", sequenceName = "SQ_TB_ROLE", allocationSize = 1)
public class Book {

  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role")
  private Long id;

  private String title;

  private String author;

  private String publisher;

  private LocalDate releaseDate;

  private Double price;

}
```

## 13. Criando o Repository e Service

### Criando a interface `BookRepository`

No diretório `src/main/java/br/com/fiap/repositories`, crie o arquivo `BookRepository.java`:

```java
package br.com.fiap.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiap.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
```

### Criando a classe de serviço `BookStoreService`

No diretório `src/main/java/br/com/fiap/service`, crie o arquivo `BookStoreService.java`:

```java
package br.com.fiap.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.fiap.entities.Book;
import br.com.fiap.repositories.BookRepository;

@Service
public class BookStoreService {

  @Autowired
  private BookRepository bookRepository;

  public List<Book> list() {
    return this.bookRepository.findAll();
  }

}
```

## 14. Atualizando o Controller `BookStoreController`

Agora, vamos modificar o `BookStoreController` para usar o `BookStoreService`:

```java
package br.com.fiap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.entities.Book;
import br.com.fiap.service.BookStoreService;

@RestController
@RequestMapping("/books")
public class BookStoreController {

  @Autowired
  private BookStoreService bookStoreService;

  @GetMapping
  public ResponseEntity<List<Book>> find() {
    List<Book> books = this.bookStoreService.list();

    return ResponseEntity.status(HttpStatus.OK).body(books);
  }

}
```
