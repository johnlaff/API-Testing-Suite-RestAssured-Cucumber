Feature: Testar a API JSONPlaceholder

  Scenario: Obter um único usuário
    Given Eu configuro o endpoint do serviço de GET users
    When Eu envio uma requisição HTTP GET
    Then Eu recebo o código de resposta HTTP 200
    And Eu recebo o usuário com id 1
