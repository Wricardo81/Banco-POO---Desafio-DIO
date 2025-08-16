package br.com.dio.banco.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Investimento {
    private String tipo;               // Ex: CDB, LCI, Tesouro
    private BigDecimal valorAplicado;
    private LocalDate dataAplicacao;
}
