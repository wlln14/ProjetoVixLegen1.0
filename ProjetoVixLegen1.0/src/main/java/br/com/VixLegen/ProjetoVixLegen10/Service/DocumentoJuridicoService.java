package br.com.VixLegen.ProjetoVixLegen10.Service;

import br.com.VixLegen.ProjetoVixLegen10.Model.DocumentoJuridico;
import br.com.VixLegen.ProjetoVixLegen10.Repository.DocumentoJuridicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentoJuridicoService {

    private final DocumentoJuridicoRepository repository;

    public DocumentoJuridicoService(
            DocumentoJuridicoRepository repository) {
        this.repository = repository;
    }

    public DocumentoJuridico cadastrar(DocumentoJuridico documento) {
        return repository.save(documento);
    }

    public List<DocumentoJuridico> listarTodos() {
        return repository.findAll();
    }

    public DocumentoJuridico buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Documento não encontrado"));
    }

    public DocumentoJuridico atualizar(
            Long id,
            DocumentoJuridico documento) {

        DocumentoJuridico existente = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Documento não encontrado"));

        existente.setNome(documento.getNome());
        existente.setDataCadastro(documento.getDataCadastro());
        existente.setArquivo(documento.getArquivo());

        return repository.save(existente);
    }

    public void excluir(Long id) {

        DocumentoJuridico documento = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Documento não encontrado"));

        repository.delete(documento);
    }
}
