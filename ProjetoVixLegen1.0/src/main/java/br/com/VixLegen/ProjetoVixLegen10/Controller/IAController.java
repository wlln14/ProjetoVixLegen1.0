package br.com.VixLegen.ProjetoVixLegen10.Controller;

import br.com.VixLegen.ProjetoVixLegen10.Model.IA;
import br.com.VixLegen.ProjetoVixLegen10.Service.IAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inteligencia-artificial")
public class IAController {

    @Autowired
    private IAService service;

    @PostMapping
    public IA cadastrar(
            @RequestBody IA analise) {

        return service.cadastrar(analise);
    }

    @GetMapping
    public List<IA> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public IA buscarPorId(
            @PathVariable Long id) {

        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public IA atualizar(
            @PathVariable Long id,
            @RequestBody IA analise) {

        return service.atualizar(id, analise);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {

        service.excluir(id);
    }
}
