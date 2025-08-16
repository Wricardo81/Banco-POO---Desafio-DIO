# 💰 Banco POO - Desafio DIO

Este projeto foi desenvolvido como parte do **Desafio da DIO** para consolidar os conceitos de **Programação Orientada a Objetos (POO)** em Java.  
A aplicação simula um **sistema bancário básico**, incluindo operações como **depósitos, saques, transferências via PIX, investimentos e histórico de transações**.

---

## 🚀 Tecnologias Utilizadas
- **Java 17**
- **Maven**
- **Lombok**
- **JUnit 5** (para testes futuros)
- **Exec Maven Plugin** (para rodar a aplicação pelo terminal)

---

## ⚙️ Funcionalidades
✔️ Criar conta (Corrente ou Poupança)  
✔️ Depositar valores  
✔️ Sacar valores  
✔️ Transferir via PIX  
✔️ Criar investimentos (ex: CDB, LCI, Tesouro)  
✔️ Consultar extrato detalhado  
✔️ Listar contas cadastradas  

---

## 📂 Estrutura do Projeto
banco-poo/
├── src/
│ └── main/java/br/com/dio/banco/
│ ├── App.java # Menu interativo
│ ├── models/ # Entidades, enums e record Transacao
│ ├── repository/ # Repositório em memória
│ └── service/ # Regras de negócio
├── pom.xml # Configuração do Maven
└── README.md # Documentação do projeto
---


---

## ▶️ Como Rodar o Projeto

### Pré-requisitos
- Java 17 instalado → [Download](https://adoptium.net/)  
- Maven instalado → [Download](https://maven.apache.org/)  
- IDE (VS Code, IntelliJ ou Eclipse)  

### Rodando pelo terminal
```bash
# Clone o repositório
git clone https://github.com/Wricardo81/DesafioBancoPOO.git

# Acesse a pasta
cd DesafioBancoPOO

# Compile e execute
mvn -q compile exec:java

---

🖼️ Exemplo do Menu
=== Banco POO - DIO ===

1 - Criar conta
2 - Depositar
3 - Sacar
4 - Transferir PIX
5 - Criar Investimento
6 - Ver Extrato
7 - Listar Contas
0 - Sair

---
📘 Aprendizados

Durante o desenvolvimento deste projeto foi possível praticar:
Uso dos pilares da POO: abstração, encapsulamento, herança e polimorfismo.
Implementação de repositórios em memória simulando persistência.
Utilização de Lombok para reduzir código repetitivo.
Criação de enums e records no Java 17.
Estruturação de menus interativos no console.
Documentação clara no GitHub.

---
👨‍💻 Autor

Projeto desenvolvido por Ricardo Santos
GitHub: Wricardo81
