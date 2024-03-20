# Documentação do Projeto de Teste de API com Rest Assured, Cucumber e JUnit

Este documento descreve o processo de criação de um projeto de teste de API utilizando Rest Assured, Cucumber e JUnit. O objetivo é fornecer um guia passo a passo para replicar e entender tudo o que foi feito, incluindo explicações detalhadas de cada função usada e exemplos de como e quando elas seriam utilizadas.

## Configuração do Projeto

O projeto foi configurado utilizando Java como linguagem de programação, Maven como ferramenta de gerenciamento de projeto e IntelliJ IDEA como IDE. As dependências do projeto foram definidas no arquivo `pom.xml`.

```xml
<dependencies>
    <!-- Cucumber -->
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-java</artifactId>
        <version>7.15.0</version>
    </dependency>
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-junit</artifactId>
        <version>7.15.0</version>
        <scope>test</scope>
    </dependency>
    <!-- Rest Assured -->
    <dependency>
        <groupId>io.rest-assured</groupId>
        <artifactId>rest-assured</artifactId>
        <version>5.4.0</version>
        <scope>test</scope>
    </dependency>
    <!-- JUnit -->
    <dependency>
        <groupId>junit</groupId>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13.2</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

## Estrutura do Projeto

O projeto segue a estrutura padrão de um projeto Maven, com código fonte em `src/main/java` e testes em `src/test/java`. Os arquivos de feature do Cucumber estão localizados em `src/test/resources/features`.

## Testes de API com Rest Assured

Rest Assured é uma biblioteca Java para testar e validar serviços REST. Ela fornece uma DSL (Domain Specific Language) que permite escrever testes de forma muito expressiva.

No nosso projeto, utilizamos Rest Assured para enviar uma requisição HTTP GET e validar a resposta. Aqui está um exemplo de como isso é feito:

```java
RestAssured.baseURI = "https://jsonplaceholder.typicode.com/users/1";
Response response = RestAssured.given().get();
response.then().statusCode(200);
response.then().body("id", equalTo(1));
```

Neste exemplo, `RestAssured.baseURI` é usado para definir a URL base para as requisições. O método `given()` é usado para especificar as condições do teste. O método `get()` envia uma requisição GET para a URL especificada. O método `then()` é usado para validar a resposta.

Rest Assured também suporta outros tipos de requisições HTTP, como POST, PUT, DELETE, etc.

## Exemplos Adicionais

Aqui estão alguns exemplos adicionais de testes que você pode realizar com Rest Assured e Cucumber.

### Teste de Requisição POST

```java
String requestBody = "{ \"name\": \"John\", \"age\": 30 }";
Response response = RestAssured.given()
        .contentType(ContentType.JSON)
        .body(requestBody)
        .post("https://jsonplaceholder.typicode.com/users");
response.then().statusCode(201);
response.then().body("name", equalTo("John"));
```

Neste exemplo, estamos enviando uma requisição HTTP POST para a URL especificada. O corpo da requisição é definido com o método `body(requestBody)`. Estamos verificando se o código de status da resposta é 201, que indica que um recurso foi criado com sucesso, e se o corpo da resposta contém um campo `name` que é igual a "John".

### Teste de Requisição PUT

```java
String requestBody = "{ \"name\": \"John\", \"age\": 30 }";
Response response = RestAssured.given()
        .contentType(ContentType.JSON)
        .body(requestBody)
        .put("https://jsonplaceholder.typicode.com/users/1");
response.then().statusCode(200);
response.then().body("name", equalTo("John"));
```

Neste exemplo, estamos enviando uma requisição HTTP PUT para a URL especificada. O corpo da requisição é definido com o método `body(requestBody)`. Estamos verificando se o código de status da resposta é 200, que indica uma resposta bem-sucedida, e se o corpo da resposta contém um campo `name` que é igual a "John".

### Teste de Requisição DELETE

```java
Response response = RestAssured.given()
        .delete("https://jsonplaceholder.typicode.com/users/1");
response.then().statusCode(200);
```

Neste exemplo, estamos enviando uma requisição HTTP DELETE para a URL especificada. Estamos verificando se o código de status da resposta é 200, que indica uma resposta bem-sucedida.

## Testes de Comportamento com Cucumber e Gherkin

Cucumber é uma ferramenta para executar testes de comportamento automatizados escritos em uma linguagem chamada Gherkin. Gherkin é uma linguagem que permite escrever cenários de teste de forma muito expressiva, em um formato quase natural.

No nosso projeto, utilizamos Cucumber para definir e executar os cenários de teste. Os cenários são escritos em arquivos de feature, que são arquivos de texto com a extensão `.feature`. Cada arquivo de feature contém um ou mais cenários de teste.

Aqui está um exemplo de um arquivo de feature:

```gherkin
Feature: Testar a API JSONPlaceholder

  Scenario: Obter um único usuário
    Given Eu configuro o endpoint do serviço de GET users
    When Eu envio uma requisição HTTP GET
    Then Eu recebo o código de resposta HTTP 200
    And Eu recebo o usuário com id 1
```

Neste exemplo, `Feature` é usado para descrever a funcionalidade que está sendo testada. `Scenario` é usado para descrever um cenário de teste. `Given`, `When`, `Then` e `And` são usados para descrever os passos do cenário.

Os passos do cenário são implementados em classes de steps, que são classes Java que contêm métodos correspondentes a cada passo. Aqui está um exemplo de uma classe de steps:

```java
public class TestAPISteps {
    private Response response;

    @Given("Eu configuro o endpoint do serviço de GET users")
    public void eu_configuro_o_endpoint_do_servico_de_get_users() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/users/1";
    }

    @When("Eu envio uma requisição HTTP GET")
    public void euEnvioUmaRequisicaoHTTPGET() {
        response = RestAssured.given().get();
    }

    @Then("Eu recebo o código de resposta HTTP {int}")
    public void euReceboOCodigoDeRespostaHTTP(Integer int1) {
        response.then().statusCode(int1);
    }

    @And("Eu recebo o usuário com id {int}")
    public void euReceboOUsuarioComId(Integer int1) {
        response.then().body("id", equalTo(int1));
    }
}
```

Neste exemplo, `@Given`, `@When`, `@Then` e `@And` são anotações usadas para associar os métodos aos passos do cenário. O método `eu_configuro_o_endpoint_do_servico_de_get_users()` é associado ao passo "Eu configuro o endpoint do serviço de GET users", e assim por diante.

## Testes Unitários com JUnit

JUnit é uma framework para escrever e executar testes unitários em Java. No nosso projeto, utilizamos JUnit em conjunto com Cucumber para executar os cenários de teste.

Aqui está um exemplo de uma classe de teste JUnit:

```java
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "Steps",
        plugin = {"pretty", "html:target/cucumber-reports"},
        monochrome = true
)
public class TestRunner {
}
```

Neste exemplo, `@RunWith(Cucumber.class)` é uma anotação que diz ao JUnit para executar a classe de teste com o runner do Cucumber. `@CucumberOptions` é uma anotação que permite configurar várias opções do Cucumber, como o caminho para os arquivos de feature (`features`), o pacote das classes de steps (`glue`), os plugins a serem usados (`plugin`), etc.

## Explicação de Código Rest Assured e Cucumber

Vamos analisar um exemplo de código que utiliza Rest Assured e Cucumber para testar uma API.

```java
RestAssured.baseURI = "https://jsonplaceholder.typicode.com/users/1";
Response response = RestAssured.given().get();
response.then().statusCode(200);
response.then().body("id", equalTo(1));
```

- `RestAssured.baseURI = "https://jsonplaceholder.typicode.com/users/1";` - Aqui, estamos definindo a URL base para as requisições HTTP que serão feitas. Neste caso, estamos apontando para a API JSONPlaceholder, que é uma API de teste gratuita e de uso livre.

- `Response response = RestAssured.given().get();` - Aqui, estamos enviando uma requisição HTTP GET para a URL base que definimos anteriormente. O método `given()` é usado para definir as condições do teste. O método `get()` envia a requisição GET. A resposta da requisição é armazenada na variável `response`.

- `response.then().statusCode(200);` - Aqui, estamos verificando se o código de status da resposta HTTP é 200, que indica uma resposta bem-sucedida.

- `response.then().body("id", equalTo(1));` - Aqui, estamos verificando se o corpo da resposta contém um campo `id` que é igual a 1.

## Melhores Práticas

Aqui estão algumas melhores práticas para escrever testes de API eficazes:

- Mantenha os testes independentes: Cada teste deve ser capaz de ser executado de forma independente, sem depender de outros testes. Isso significa que um teste não deve alterar nenhum estado que possa afetar a execução de outros testes. Por exemplo, se um teste cria um usuário em um sistema, ele deve se certificar de excluir esse usuário no final, para que outros testes não sejam afetados por esse usuário extra.

- Mantenha os testes idempotentes: Cada teste deve produzir o mesmo resultado, não importa quantas vezes seja executado. Isso é especialmente importante para testes de API, onde você pode estar interagindo com um sistema externo. Por exemplo, se um teste verifica se a criação de um usuário retorna um código de status 201, ele deve garantir que o usuário seja excluído antes de cada execução do teste, para que o mesmo usuário possa ser criado repetidamente.

- Organize os arquivos de feature do Cucumber: Mantenha os arquivos de feature do Cucumber bem organizados, agrupando cenários relacionados no mesmo arquivo. Isso torna mais fácil para os leitores entenderem o que cada arquivo de feature está testando e encontrar cenários específicos que possam estar procurando.


## Resolução de Problemas

Aqui estão algumas soluções comuns para problemas que você pode encontrar:

- Se você está recebendo um erro de compilação, verifique se todas as suas dependências estão corretas e atualizadas em seu arquivo `pom.xml`. As versões incorretas ou ausentes de dependências podem causar erros de compilação.

- Se um teste está falhando, verifique se a API que você está testando está funcionando corretamente. Você pode fazer isso enviando requisições manualmente com uma ferramenta como Postman. Se a API não estiver funcionando como esperado, isso pode ser a causa do teste falhando.

- Se você está tendo problemas para entender um erro, tente usar o método `log().all()` do Rest Assured para registrar todas as informações da requisição e da resposta. Isso pode ajudá-lo a ver exatamente o que está sendo enviado e recebido, o que pode ajudar a identificar a causa do problema.

## Recursos Adicionais

Aqui estão alguns links para documentações oficiais, tutoriais adicionais e outras fontes de aprendizado:

- [Rest Assured User Guide](https://rest-assured.io/): Este é o guia oficial do usuário para Rest Assured. Ele contém uma visão geral detalhada de como usar a biblioteca, incluindo exemplos de código.

- [Cucumber Documentation](https://cucumber.io/docs/guides/): Esta é a documentação oficial do Cucumber. Ela contém guias sobre como usar o Cucumber para testes de comportamento, incluindo como escrever arquivos de feature e classes de steps.

- [JUnit User Guide](https://junit.org/junit5/docs/current/user-guide/): Este é o guia oficial do usuário para JUnit. Ele contém uma visão geral detalhada de como usar o JUnit para testes unitários, incluindo como escrever e executar testes.

- [Maven Getting Started Guide](https://maven.apache.org/guides/getting-started/index.html): Este é um guia para começar com Maven. Ele contém informações sobre como configurar um projeto Maven e como usar Maven para gerenciar dependências e construir seu projeto.

- [Java Tutorials](https://docs.oracle.com/javase/tutorial/): Estes são tutoriais oficiais para a linguagem de programação Java. Eles contêm informações sobre os fundamentos da linguagem, bem como tópicos mais avançados.

## Conclusão

Este documento forneceu um guia passo a passo para criar um projeto de teste de API utilizando Rest Assured, Cucumber e JUnit. Esperamos que ele seja útil para você aprender sobre essas tecnologias e como usá-las para testar APIs REST de forma eficaz.