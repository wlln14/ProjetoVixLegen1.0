package br.com.VixLegen.ProjetoVixLegen10.Service;

import br.com.VixLegen.ProjetoVixLegen10.Model.ProcessoJuridico;
import br.com.VixLegen.ProjetoVixLegen10.Repository.ProcessoJuridicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessoJuridicoService {

    private final ProcessoJuridicoRepository processoRepository;

    public ProcessoJuridicoService(ProcessoJuridicoRepository processoRepository) {
        this.processoRepository = processoRepository;
    }

    // CREATE
    public ProcessoJuridico cadastrar(ProcessoJuridico processo) {
        return processoRepository.save(processo);
    }

    // READ - todos
    public List<ProcessoJuridico> listarTodos() {
        return processoRepository.findAll();
    }

    // READ - por ID
    public ProcessoJuridico buscarPorId(Long id) {
        return processoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Processo jurídico não encontrado"));
    }

    // UPDATE
    public ProcessoJuridico atualizar(
            Long id,
            ProcessoJuridico processo) {

        ProcessoJuridico processoExistente = processoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Processo jurídico não encontrado"));

        processoExistente.setNumeroProcesso(processo.getNumeroProcesso());
        processoExistente.setVara(processo.getVara());
        processoExistente.setComarca(processo.getComarca());
        processoExistente.setTribunal(processo.getTribunal());
        processoExistente.setInstancia(processo.getInstancia());
        processoExistente.setSegredoJustica(processo.isSegredoJustica());

        return processoRepository.save(processoExistente);
    }

    // DELETE
    public void excluir(Long id) {

        ProcessoJuridico processo = processoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Processo jurídico não encontrado"));

        processoRepository.delete(processo);
    }
}