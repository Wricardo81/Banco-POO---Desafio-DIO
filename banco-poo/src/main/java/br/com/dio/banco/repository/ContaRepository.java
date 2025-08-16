package br.com.dio.banco.repository;

import br.com.dio.banco.models.Conta;

import java.util.*;

public class ContaRepository {
    private final Map<String, Conta> porNumero = new HashMap<>();
    private final Map<String, Conta> porPix = new HashMap<>();

    public Conta save(Conta c) {
        porNumero.put(c.getNumero(), c);
        if (c.getPixChave() != null && !c.getPixChave().isBlank()) {
            porPix.put(c.getPixChave(), c);
        }
        return c;
    }

    public Optional<Conta> findByNumero(String numero) {
        return Optional.ofNullable(porNumero.get(numero));
    }

    public Optional<Conta> findByPix(String pix) {
        return Optional.ofNullable(porPix.get(pix));
    }

    public List<Conta> findAll() {
        return new ArrayList<>(porNumero.values());
    }
}
