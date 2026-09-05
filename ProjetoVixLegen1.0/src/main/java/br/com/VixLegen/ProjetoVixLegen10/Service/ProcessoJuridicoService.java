package br.com.VixLegen.ProjetoVixLegen10.Service;

import br.com.VixLegen.ProjetoVixLegen10.Enums.StatusProcesso;
import br.com.VixLegen.ProjetoVixLegen10.Model.Cliente;
import br.com.VixLegen.ProjetoVixLegen10.Model.ProcessoJuridico;
import br.com.VixLegen.ProjetoVixLegen10.Repository.ClienteRepository;
import br.com.VixLegen.ProjetoVixLegen10.Repository.ProcessoJuridicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessoJuridicoService {

    private final ProcessoJuridicoRepository processoRepository;
    private final ClienteRepository clienteRepository;

    public ProcessoJuridicoService(
            ProcessoJuridicoRepository processoRepository,
            ClienteRepository clienteRepository) {

        this.processoRepository = processoRepository;
        this.clienteRepository = clienteRepository;
    }

    // CREATE
    public ProcessoJuridico cadastrar(ProcessoJuridico processo) {

        Cliente cliente = clienteRepository.findById(
                processo.getCliente().getIdCliente()
        ).orElseThrow(() ->
                new RuntimeException("Cliente não encontrado"));

        processo.setCliente(cliente);

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

        if (processoExistente.getStatus() == StatusProcesso.ENCERRADO) {
            throw new RuntimeException("Processo encerrado não pode ser alterado");
        }

        Cliente cliente = clienteRepository.findById(
                processo.getCliente().getIdCliente()
        ).orElseThrow(() ->
                new RuntimeException("Cliente não encontrado"));

        processoExistente.setNumeroProcesso(processo.getNumeroProcesso());
        processoExistente.setVara(processo.getVara());
        processoExistente.setComarca(processo.getComarca());
        processoExistente.setTribunal(processo.getTribunal());
        processoExistente.setInstancia(processo.getInstancia());
        processoExistente.setSegredoJustica(processo.isSegredoJustica());
        processoExistente.setCliente(cliente);

        return processoRepository.save(processoExistente);
    }

    // DELETE
    public void excluir(Long id) {

        ProcessoJuridico processo = processoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Processo jurídico não encontrado"));

        processoRepository.delete(processo);
    }

    public List<ProcessoJuridico> listarPorStatus(StatusProcesso status) {
        return processoRepository.findByStatus(status);
    }

    public StatusProcesso consultarSituacao(Long id) {

        ProcessoJuridico processo = processoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Processo jurídico não encontrado"));

        return processo.getStatus();
    }
}