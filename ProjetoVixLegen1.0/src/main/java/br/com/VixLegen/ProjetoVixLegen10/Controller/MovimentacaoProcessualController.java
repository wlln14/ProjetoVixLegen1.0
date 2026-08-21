package br.com.VixLegen.ProjetoVixLegen10.Controller;

import br.com.VixLegen.ProjetoVixLegen10.Model.MovimentacaoProcessual;
import br.com.VixLegen.ProjetoVixLegen10.Service.MovimentacaoProcessualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimentacoes-processuais")
public class MovimentacaoProcessualController {

    @Autowired
    private MovimentacaoProcessualService service;

    @PostMapping
    public MovimentacaoProcessual cadastrar(
            @RequestBody MovimentacaoProcessual movimentacao) {

        return service.cadastrar(movimentacao);
    }

    @GetMapping
    public List<MovimentacaoProcessual> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public MovimentacaoProcessual buscarPorId(
            @PathVariable Long id) {

        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public MovimentacaoProcessual atualizar(
            @PathVariable Long id,
            @RequestBody MovimentacaoProcessual movimentacao) {

        return service.atualizar(id, movimentacao);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {

        service.excluir(id);
    }
}
