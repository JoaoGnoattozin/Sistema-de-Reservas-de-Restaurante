# Sistema-de-Reservas-de-Restaurante
Implementação Completa do Sistema de Reservas de Restaurante com JPA/Hibernate e PostgreSQL
# Sistema de Reservas de Restaurante com JPA/Hibernate

## Tecnologias Utilizadas
- Java 11
- JPA/Hibernate
- PostgreSQL
- Maven

## Funcionalidades
- Cadastro de clientes (regular e VIP)
- Reserva de mesas
- Cancelamento de reservas
- Consultas avançadas:
  - Por período de datas
  - Por nome do cliente
  - Por número da mesa
- Relatórios de ocupação

## Configuração
1. Criar banco de dados PostgreSQL chamado `restaurante`
2. Configurar usuário/senha no `persistence.xml`
3. Executar `mvn clean package`
4. Rodar com `java -jar target/restaurante-jpa-1.0-jar-with-dependencies.jar`

## Consultas JPA Implementadas
1. Consulta com JOIN entre Reserva e Mesa
2. Consulta com intervalo de datas (BETWEEN)
3. Consulta com LIKE para busca parcial de nomes
4. Consulta agregada com COUNT e AVG
5. Consulta com herança (Cliente/ClienteVIP)

## Diagrama de Classes
![Diagrama de Classes](diagrama-classes.png)
