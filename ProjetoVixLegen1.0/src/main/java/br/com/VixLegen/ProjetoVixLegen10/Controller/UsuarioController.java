package br.com.VixLegen.ProjetoVixLegen10.Controller;

import br.com.VixLegen.ProjetoVixLegen10.Model.Usuario;
import br.com.VixLegen.ProjetoVixLegen10.Service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.VixLegen.ProjetoVixLegen10.DTOs.Request.UsuarioRequest;
import br.com.VixLegen.ProjetoVixLegen10.DTOs.Response.UsuarioResponse;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> cadastrar(
            @Valid @RequestBody UsuarioRequest request) {

        return ResponseEntity.ok(
                usuarioService.cadastrar(request)
        );
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(
             @PathVariable Long id,
             @Valid
            @RequestBody Usuario usuario) {

        Usuario usuarioAtualizado = usuarioService.atualizar(id, usuario);

        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        usuarioService.excluir(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> buscarPorEmail(
            @PathVariable String email) {

        return ResponseEntity.ok(
                usuarioService.buscarPorEmail(email)
        );
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Usuario> buscarPorCpf(
            @PathVariable String cpf) {

        return ResponseEntity.ok(
                usuarioService.buscarPorCpf(cpf)
        );
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<Usuario>> listarAtivos() {

        return ResponseEntity.ok(
                usuarioService.listarAtivos()
        );
    }
}