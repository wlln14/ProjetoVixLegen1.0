package br.com.VixLegen.ProjetoVixLegen10.Service;

import br.com.VixLegen.ProjetoVixLegen10.Model.CategoriaDocumento;
import br.com.VixLegen.ProjetoVixLegen10.Repository.CategoriaDocumentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaDocumentoService {

    private final CategoriaDocumentoRepository repository;

    public CategoriaDocumentoService(
            CategoriaDocumentoRepository repository) {
        this.repository = repository;
    }

    public CategoriaDocumento cadastrar(CategoriaDocumento categoria) {
        return repository.save(categoria);
    }

    public List<CategoriaDocumento> listarTodos() {
        return repository.findAll();
    }

    public CategoriaDocumento buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Categoria de documento não encontrada"));
    }

    public CategoriaDocumento atualizar(
            Long id,
            CategoriaDocumento categoria) {

        CategoriaDocumento existente = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Categoria de documento não encontrada"));

        existente.setDescricao(categoria.getDescricao());
        existente.setPrazoMaximoUtilizacao(
                categoria.getPrazoMaximoUtilizacao());
        existente.setValorTaxaDiariaMulta(
                categoria.getValorTaxaDiariaMulta());

        return repository.save(existente);
    }

    public void excluir(Long id) {

        CategoriaDocumento categoria = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Categoria de documento não encontrada"));

        repository.delete(categoria);
    }
}
