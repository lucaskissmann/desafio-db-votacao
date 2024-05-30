# Geral
- URL de acesso ao Swagger da versão: `/swagger-ui/index.html` ou `/votacao_swagger.html`.
- Na raíz do projeto há uma collection do postman com as requisições configuradas.

# Estrutura do projeto

A aplicação é estruturada em diferentes versões separadas por pastas. No mesmo nível de cada pasta de versionamento exite a pasta de context onde está localizado o ApplicationContext, que contém a informação da versão atual do projeto para que, desta forma, ao alterar a versão do projeto, apenas um local seja modificado. Dentro de cada versão, existe uma pasta de configuração geral, uma pasta helper onde há diversas classes uteis para a versão como um todo e uma pasta com os modulos da versão, sendo cada um deles separado por controllers, data, e services.

### Controllers
    - Responsáveis por processar as requisições HTTP.
    - Deve estender a classe `Controller.java`, que centraliza os métodos de retorno para as requisições.
    - Deve implementar uma interface `Swagger.java`, que possui a documentação do swagger para cada classe.

### Data
    - Centraliza tudo que é relevante e diretamente relacionado com as entidades da versão, bem como **ENUMS**, **DTO's**, **REPOSITORIES**, **SWAGGER** e os **MODELS**

### Services
    - Implementação das regras de negócio.
    - Podem haver chamadas para outras services, mas a interação deve ser limitada ao Repository da Entidade em questão.

# Tratamento de erros e exceções
    - O tratamento de exceções e erros disparados pela aplicação é realizada por ExceptionHandlers, onde cada Exception deve possuir o respectivo Handler.
    - A nomenclatura do Handler deve seguir o nome da exception + `Handler.java`.
    - Cada Handler deve implementar a interface `Handler<T>` e realizar a implementação do método `handle( T e )` utilizando como retorno o método `ResponseEntity<Error> response( String message, HttpStatus status )`.
    - A interface Handler, é utilizada para facilitar a implementação de um novo ExceptionHandler, centralizando a validação e Response retornada para as exceptions e erros.