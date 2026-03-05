package com.example.ejb;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class BeneficioEjbServiceTest {

    @Mock
    private EntityManager em;

    @InjectMocks
    private BeneficioEjbService service;

    private Beneficio origem;
    private Beneficio destino;

    @BeforeEach
    void setUp() {
        origem = new Beneficio();
        origem.setId(1L);
        origem.setValor(BigDecimal.valueOf(100.0));

        destino = new Beneficio();
        destino.setId(2L);
        destino.setValor(BigDecimal.valueOf(50.0));
    }

    @Test
    void deveTransferirComSucessoQuandoSaldoForSuficiente() {
        when(em.find(Beneficio.class, 1L, LockModeType.PESSIMISTIC_WRITE)).thenReturn(origem);
        when(em.find(Beneficio.class, 2L, LockModeType.PESSIMISTIC_WRITE)).thenReturn(destino);

        service.transfer(1L, 2L, BigDecimal.valueOf(40.0));

        assertEquals(BigDecimal.valueOf(60.0), origem.getValor());
        assertEquals(BigDecimal.valueOf(90.0), destino.getValor());
    }

    @Test
    void deveLancarExcecaoQuandoSaldoForInsuficiente() {
        when(em.find(Beneficio.class, 1L, LockModeType.PESSIMISTIC_WRITE)).thenReturn(origem);
        when(em.find(Beneficio.class, 2L, LockModeType.PESSIMISTIC_WRITE)).thenReturn(destino);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.transfer(1L, 2L, BigDecimal.valueOf(150.0));
        });

        assertTrue(exception.getMessage().contains("Saldo insuficiente"));
    }
}