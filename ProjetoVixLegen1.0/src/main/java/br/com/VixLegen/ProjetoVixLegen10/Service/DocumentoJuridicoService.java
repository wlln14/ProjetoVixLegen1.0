package br.com.VixLegen.ProjetoVixLegen10.Service;

import br.com.VixLegen.ProjetoVixLegen10.Model.CategoriaDocumento;
import br.com.VixLegen.ProjetoVixLegen10.Model.DocumentoJuridico;
import br.com.VixLegen.ProjetoVixLegen10.Model.ProcessoJuridico;
import br.com.VixLegen.ProjetoVixLegen10.Repository.CategoriaDocumentoRepository;
import br.com.VixLegen.ProjetoVixLegen10.Repository.DocumentoJuridicoRepository;
import br.com.VixLegen.ProjetoVixLegen10.Repository.ProcessoJuridicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentoJuridicoService {

    private final DocumentoJuridicoRepository documentoRepository;
    private final ProcessoJuridicoRepository processoRepository;
    private final CategoriaDocumentoRepository categoriaRepository;

    public DocumentoJuridicoService(
            DocumentoJuridicoRepository documentoRepository,
            ProcessoJuridicoRepository processoRepository,
            CategoriaDocumentoRepository categoriaRepository) {

        this.documentoRepository = documentoRepository;
        this.processoRepository = processoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    // CREATE
    public DocumentoJuridico cadastrar(DocumentoJuridico documento) {

        ProcessoJuridico processo = processoRepository.findById(
                documento.getProcesso().getIdProcesso()
        ).orElseThrow(() ->
                new RuntimeException("Processo jurídico não encontrado"));

        CategoriaDocumento categoria = categoriaRepository.findById(
                documento.getCategoriaDocumento()
                        .getCodigoCategoriaDocumento()
        ).orElseThrow(() ->
                new RuntimeException(
                        "Categoria de documento não encontrada"));

        documento.setProcesso(processo);
        documento.setCategoriaDocumento(categoria);

        return documentoRepository.save(documento);
    }

    // READ
    public List<DocumentoJuridico> listarTodos() {
        return documentoRepository.findAll();
    }

    // READ por ID
    public DocumentoJuridico buscarPorId(Long id) {

        return documentoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Documento não encontrado"));
    }

    // UPDATE
    public DocumentoJuridico atualizar(
            Long id,
            DocumentoJuridico documento) {

        DocumentoJuridico existente =
                documentoRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Documento não encontrado"));

        ProcessoJuridico processo = processoRepository.findById(
                documento.getProcesso().getIdProcesso()
        ).orElseThrow(() ->
                new RuntimeException("Processo jurídico não encontrado"));

        CategoriaDocumento categoria = categoriaRepository.findById(
                documento.getCategoriaDocumento()
                        .getCodigoCategoriaDocumento()
        ).orElseThrow(() ->
                new RuntimeException(
                        "Categoria de documento não encontrada"));

        existente.setNome(documento.getNome());
        existente.setDataCadastro(documento.getDataCadastro());
        existente.setArquivo(documento.getArquivo());
        existente.setProcesso(processo);
        existente.setCategoriaDocumento(categoria);

        return documentoRepository.save(existente);
    }

    // DELETE
    public void excluir(Long id) {

        DocumentoJuridico documento =
                documentoRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Documento não encontrado"));

        documentoRepository.delete(documento);
    }
}