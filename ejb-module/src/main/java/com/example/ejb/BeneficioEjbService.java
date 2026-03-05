package com.example.ejb;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import jakarta.persistence.LockModeType;

import java.math.BigDecimal;

@Stateless
@Service
public class BeneficioEjbService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void transfer(Long fromId, Long toId, BigDecimal amount) {

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor da transferência deve ser maior que zero.");
        }

        if (fromId.equals(toId)) {
            throw new IllegalArgumentException("As contas de origem e destino não podem ser as mesmas.");
        }

        Beneficio from = em.find(Beneficio.class, fromId, LockModeType.PESSIMISTIC_WRITE);
        Beneficio to   = em.find(Beneficio.class, toId, LockModeType.PESSIMISTIC_WRITE);

        if (from == null) {
            throw new IllegalArgumentException("Conta de origem não encontrada.");
        }

        if (to == null) {
            throw new IllegalArgumentException("Conta de destino não encontrada.");
        }

        if (from.getValor().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar a transferência.");
        }

        // BUG: sem validações, sem locking, pode gerar saldo negativo e lost update
        from.setValor(from.getValor().subtract(amount));
        to.setValor(to.getValor().add(amount));

        em.merge(from);
        em.merge(to);
    }
}
