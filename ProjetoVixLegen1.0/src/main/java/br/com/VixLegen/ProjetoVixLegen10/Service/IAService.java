package br.com.VixLegen.ProjetoVixLegen10.Service;

import br.com.VixLegen.ProjetoVixLegen10.Model.IA;
import br.com.VixLegen.ProjetoVixLegen10.Repository.IARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IAService {

    @Autowired
    private IARepository repository;

    public IA cadastrar(
            IA analise) {

        return repository.save(analise);
    }

    public List<IA> listar() {
        return repository.findAll();
    }

    public IA buscarPorId(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Análise de IA não encontrada"));
    }

    public IA atualizar(
            Long id,
            IA dados) {

        IA analise = buscarPorId(id);

        analise.setPrevisaoTempoJulgamento(
                dados.getPrevisaoTempoJulgamento());

        analise.setProbabilidadeSucesso(
                dados.getProbabilidadeSucesso());

        analise.setNivelConfianca(
                dados.getNivelConfianca());

        analise.setSugestoes(
                dados.getSugestoes());

        analise.setPadroesEncontrados(
                dados.getPadroesEncontrados());

        return repository.save(analise);
    }

    public void excluir(Long id) {

        IA analise = buscarPorId(id);

        repository.delete(analise);
    }
}
