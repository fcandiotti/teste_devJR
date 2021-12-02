# XBrain - CRUD Teste para Dev Java Jr.

Este repositório contém o código da API de um CRUD de Vendas, criando e filtrando Vendedor e Cliente,
A Api também retorna a lista de vendedores com os campos:
##### nome, total de vendas do vendedor e média de vendas diária, conforme o período informado por parâmetro. 

### Instalação

É necessário o maven instalado (apt-get install maven)

   1. mvn install para instalar as dependências
   2. mvn spring-boot:run para rodar a aplicação
   3. Acesse a url: http://localhost:8080

### Tecnologias
Java11, Spring-Boot, Jpa/Hibernate, H2 Database

## Exemplos de Requests

Abaixo, alguns exemplos pontuais de consumo da API desenvolvida.

### Cadastrar Vendedor

```
curl --location --request POST 'localhost:8080/vendedores' \
--header 'Content-Type: application/json' \
--data-raw '{
   "nome": "Joao Teste"
}'

```

### Listar Individualmente vendedores com todas as suas vendas

```
curl --location --request GET 'http://localhost:8080/vendedores/1' \
--header 'Content-Type: application/json' \
--data-raw '
```

### Listar todos os vendedores com todas as suas vendas
```
curl --location --request GET 'http://localhost:8080/vendedores' \
--header 'Content-Type: application/json' \
--data-raw '
'
```

### Retornar a lista de todos os vendedores com todas suas vendas, trazendo o calculo da média filtrando por período.

```
curl --location --request GET 'localhost:8080/vendedores/filtro?dataInicio=10/02/2020&dataFim=10/02/2020'

### Cadastrar Cliente

```
curl --location --request POST 'localhost:8080/clientes' \
--header 'Content-Type: application/json' \
--data-raw '{
   "nome": "Fernando Cliente"
}'

```

### Listar o cliente especifico

```
curl --location --request GET 'http://localhost:8080/clientes/1' \
--header 'Content-Type: application/json' \
--data-raw '
''
```

### Listar todos os clientes

```
curl --location --request GET 'http://localhost:8080/clientes' \
--header 'Content-Type: application/json' \
--data-raw '
'
```

###  Cadastrar uma venda

```
curl --location --request POST 'http://localhost:8080/vendas' \
--header 'Content-Type: application/json' \
--data-raw '{
    "vendedor": 6,
    "cliente": 5,
    "dataVenda": "08/02/2020",  
    "valorVenda": 100.2
}
'
```
### Listar uma venda em especifico

```
curl --location --request GET 'http://localhost:8080/vendas/1' \
--data-raw ''
```

### Listar uma todas as vendas

```
curl --location --request GET 'http://localhost:8080/vendas' \
--data-raw ''
```
### Atualizar uma venda

```
curl --location --request PUT 'localhost:8080/vendas/4' \
--header 'Authorization: {{token}}' \
--header 'Content-Type: application/json' \
--data-raw '{
    "dataVenda": "09/10/2020",
    "valorVenda": 10.22,
    "vendedor": 6,
    "cliente": 5,
    "nomeVendedor": "Vendedor Mil Grau",
    "nomeCliente": "Lucas Yoshi"
}'
```
### Deletar uma venda

```
curl --location --request DELETE 'localhost:8080/vendas/4' \
--header 'Authorization: {{token}}' \
--data-raw 
```

