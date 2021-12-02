#XBrain - CRUD Teste para Dev Java Jr.

Este repositório contém o código da API do desafio de seleção da XBrain para dev Java.

O propósito do desafio é entregar uma API de Vendas usando SPRING BOOT, e retornar a lista de vendedores com os campos:
#####nome, total de vendas do vendedor e média de vendas diária, conforme o período informado por parâmetro. 

## Exemplos de Requests

Abaixo, alguns exemplos pontuais de consumo da API desenvolvida.

### Cadastrar Vendedor

```
POST / vendedores
Host: localhost:8080

{
    "nome": "Nome do Vendedor" - CAMPO OBRIGATORIO
}

```

### Listar Individualmente vendedores com todas as suas vendas

```
GET / vendedores/{id}
Host: localhost:8080
```

### Retornar a lista de todos os vendedores com todas suas vendas, trazendo o calculo da média filtrando por período.

```
GET  /vendedores/filtro?dataInicio=dd/MM/YYYY&dataFim=dd/MM/yyyy
host: localhost:8080
```

### Cadastrar Cliente

```
POST / vendedores
Host: localhost:8080

{
    "nome": "Nome do Cliente" - CAMPO OBRIGATORIO
}

```

### Listar o cliente especifico

```
GET / clientes/{id}
Host: localhost:8080
```
### Listar todos os clientes

```
GET / clientes
Host: localhost:8080
```

###  Cadastrar uma venda

```
POST / vendas
Host: localhost:8080

{
    "vendedor": id, - Verifica se o vendedor existe na base de dados
    "cliente": id, - Verifica se o cliente existe na base de dados
    "dataVenda": "xx/xx/xxxx", - CAMPO OBRIGATORIO
    "valorVenda": xxx.xx - CAMPO OBRIGATORIO

}
```
### Listar uma venda em especifico

```
GET / vendas/{id}
Host: localhost:8080
```

### Listar uma todas as vendas

```
GET / vendas
Host: localhost:8080
```
### Atualizar uma venda

```
PUT / vendas/{id}
Host: localhost:8080

{
    "vendedor": id,
    "cliente": id,
    "valorVenda": xx.xx,
    "dataVenda": "xx/xx/xxxx"
}
```
###Deletar uma venda

```
DELETE /vendas/{id}
Host: localhost:8080
```

