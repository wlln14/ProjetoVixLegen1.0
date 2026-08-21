package br.com.VixLegen.ProjetoVixLegen10.Controller;

import br.com.VixLegen.ProjetoVixLegen10.Model.Cliente;
import br.com.VixLegen.ProjetoVixLegen10.Service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Cliente> cadastrar(
            @Valid @RequestBody Cliente cliente) {

        return ResponseEntity.ok(
                clienteService.cadastrar(cliente)
        );
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos() {

        return ResponseEntity.ok(
                clienteService.listarTodos()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                clienteService.buscarPorId(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(
            @Valid @PathVariable Long id,
            @RequestBody Cliente cliente) {

        return ResponseEntity.ok(
                clienteService.atualizar(id, cliente)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {

        clienteService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}