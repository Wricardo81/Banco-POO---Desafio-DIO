package br.com.dio.banco.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Transacao(LocalDateTime dataHora,
                        TipoTransacao tipo,
                        BigDecimal valor,
                        String descricao) { }
