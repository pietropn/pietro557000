# Cidade em Dia de Chuva - Global Solution

- **Nome:** Pietro de Paula Nascimento
- **RM:** 557000
- **P1 (prefixo das tabelas):** GS000 (557000 % 1000 = 0)
- **P2 (limiar de alerta L):** 10 cm (10 + 2 * (557000 % 4) = 10)
- **Versão:** com HTML (Thymeleaf)

## Como executar

1. Coloque o arquivo `credenciais.txt` na pasta raiz do projeto, com o conteúdo:

   ```
   spring.datasource.username=rm557000
   spring.datasource.password=SENHA
   ```
   
   *ou se preferir, coloque as credenciais direto em `application.properties`

2. Execute `mvn spring-boot:run` na pasta raiz do projeto.
3. Acesse http://localhost:8080/

As tabelas `GS000_BAIRRO`, `GS000_TRECHO` e `GS000_LEITURA` são criadas automaticamente pelo JPA no Oracle.
