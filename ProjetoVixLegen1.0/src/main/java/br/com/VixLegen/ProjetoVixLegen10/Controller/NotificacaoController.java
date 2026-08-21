package br.com.VixLegen.ProjetoVixLegen10.Controller;

import br.com.VixLegen.ProjetoVixLegen10.Model.Notificacao;
import br.com.VixLegen.ProjetoVixLegen10.Service.NotificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificacoes")
public class NotificacaoController {

    @Autowired
    private NotificacaoService service;

    @PostMapping
    public Notificacao cadastrar(
            @RequestBody Notificacao notificacao) {

        return service.cadastrar(notificacao);
    }

    @GetMapping
    public List<Notificacao> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Notificacao buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Notificacao atualizar(
            @PathVariable Long id,
            @RequestBody Notificacao notificacao) {

        return service.atualizar(id, notificacao);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}