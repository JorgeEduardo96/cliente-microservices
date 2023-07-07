
# Cliente Microservices

Projeto criado com fim didático para prática de conceitos de microsserviços.


## Componentes

- api-gateway: Serviço de gateway da aplicação.
- cliente-cadastro: Serviço responsável pelo cadastro de clientes, utilizando do banco de dados em memória H2;
- cliente-emprestimo: Serviço responsável pelos cálculos referentes ao emprestimos dos clientes já cadastrados;
- naming-server: Serviço de service discovery da aplicação;
- spring-cloud-config-server: Serviço de configuração da aplicação, contendo as taxas por cada ambiente


## Startup

A aplicação está conteinerizada, então é necessário ter o Docker instalado para executa-la.
Para iniciar a aplicação, basta ir na pasta raiz, e digitar o comando:

```
docker compose up -d
```