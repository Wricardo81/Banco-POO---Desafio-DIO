# Banco POO (DIO) - Java 17

Sistema bancário básico para praticar POO (abstração, encapsulamento, herança, polimorfismo) + PIX, investimentos e extrato.  
**Stack:** Java 17, Maven, Lombok.

## Rodar
```bash
mvn -q compile exec:java
```
> Se a IDE pedir, habilite *annotation processing* para o Lombok.

## Estrutura
- `App.java`: menu de console
- `service/BancoService.java`: regras de negócio
- `repository/ContaRepository.java`: persistência em memória
- `models/*`: entidades, enums e record `Transacao`
