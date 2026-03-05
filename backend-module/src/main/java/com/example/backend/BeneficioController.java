package com.example.backend;

import com.example.backend.dto.TransferRequest;
import com.example.backend.repository.BeneficioRepository;
import com.example.ejb.Beneficio;
import com.example.ejb.BeneficioEjbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/v1/beneficios")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Benefícios", description = "Endpoints para gestão e transferência de benefícios")
public class BeneficioController {

    @Autowired
    private BeneficioEjbService beneficioEjbService;

    @Autowired
    private BeneficioRepository repository;

    @Operation(summary = "Executa a transferência de saldo entre dois benefícios",
            description = "Verifica saldo e aplica locking para garantir a integridade")
    @PostMapping("/transferir")
    @Transactional
    public ResponseEntity<String> transferir(@RequestBody TransferRequest request) {
        try {
            beneficioEjbService.transfer(
                    request.getFromId(),
                    request.getToId(),
                    request.getAmount()
            );
            return ResponseEntity.ok("Transferência realizada com sucesso.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao processar transferência: " + e.getMessage());
        }
    }

    @Operation(summary = "Lista todos os benefícios cadastrados")
    @GetMapping
    public ResponseEntity<List<Beneficio>> listarTodos() {
        return ResponseEntity.ok(repository.findAll());
    }

    @Operation(summary = "Cadastra um novo benefício")
    @PostMapping
    public ResponseEntity<Beneficio> criar(@RequestBody Beneficio beneficio) {
        Beneficio salvo = repository.save(beneficio);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @Operation(summary = "Atualiza um benefício")
    @PutMapping("/{id}")
    public ResponseEntity<Beneficio> atualizar(@PathVariable Long id, @RequestBody Beneficio atualizado) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        atualizado.setId(id);
        return ResponseEntity.ok(repository.save(atualizado));
    }

    @Operation(summary = "Deleta um benefício")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}