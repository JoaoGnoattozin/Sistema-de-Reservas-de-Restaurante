# Sistema de Reservas de Restaurante

Este projeto é um sistema de reservas para um restaurante, desenvolvido em **Java puro com JPA** (sem Spring). Ele permite que clientes façam e cancelem reservas de mesas, além de consultar as reservas existentes. O sistema utiliza **persistência com PostgreSQL** e segue princípios da Programação Orientada a Objetos (POO).

---

## ⚙️ Tecnologias Utilizadas

- Java 11+
- JPA (Jakarta Persistence API)
- PostgreSQL
- Hibernate
- Maven
- Eclipse IDE (recomendado)

---

## ✨ Funcionalidades

- **Fazer Reserva**
  - Cliente informa nome, telefone, se é VIP e o horário da reserva.
  - Escolhe uma das mesas disponíveis.
  - A reserva é registrada no banco de dados.

- **Cancelar Reserva**
  - Reserva pode ser cancelada por ID.
  - A mesa é liberada após o cancelamento.

- **Listar Reservas**
  - Exibe reservas existentes, com cliente, mesa e horário.

- **Consultar por Período**
  - Permite filtrar reservas entre duas datas/horários.

---

## 🗂️ Estrutura do Projeto

- `Cliente` e `ClienteVIP`: representam o cliente comum e o VIP (herança).
- `Mesa` e `MesaVIP`: representam mesas comuns e VIPs.
- `Reserva`: relaciona cliente, mesa e horário.
- `StatusReserva`: enum com o status da reserva (ATIVA, CANCELADA).
- `ReservaService`, `ClienteService`, `MesaService`: controlam as operações com JPA.
- `RestauranteController`: lida com entrada de dados via console.
- `MainApp`: classe principal que inicia o sistema.
- `persistence.xml`: configura o acesso ao banco PostgreSQL.

---

## 🧪 Como Executar o Projeto

1. **Pré-requisitos**:
   - PostgreSQL rodando localmente (`localhost:5432`)
   - Banco de dados chamado `restaurante` criado
   - Usuário e senha no `persistence.xml` devidamente configurados

2. **Passos**:
   - Clone o projeto:
     ```bash
     git clone https://github.com/JoaoGnoattozin/Sistema-de-Reservas-de-Restaurante.git
     ```
   - Importe no Eclipse como projeto Maven.
   - Execute a classe `MainApp.java`.

> O sistema cria as tabelas automaticamente no banco (graças ao `hibernate.hbm2ddl.auto = create`).

---

## 💾 Exemplo de Uso

```shell
=== SISTEMA DE RESERVAS ===
1. Fazer reserva
2. Cancelar reserva
3. Listar reservas
4. Listar mesas disponíveis
5. Consultar reservas por período
6. Sair
