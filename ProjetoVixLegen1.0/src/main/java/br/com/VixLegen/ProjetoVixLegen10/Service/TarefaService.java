package br.com.VixLegen.ProjetoVixLegen10.Service;

import br.com.VixLegen.ProjetoVixLegen10.Model.Tarefa;
import br.com.VixLegen.ProjetoVixLegen10.Repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public Tarefa cadastrar(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> listarTodos() {
        return tarefaRepository.findAll();
    }

    public Tarefa buscarPorId(Long id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Tarefa não encontrada"));
    }

    public Tarefa atualizar(Long id, Tarefa tarefa) {

        Tarefa existente = tarefaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Tarefa não encontrada"));

        existente.setDataAtribuicao(tarefa.getDataAtribuicao());
        existente.setPrazo(tarefa.getPrazo());
        existente.setTipoTarefa(tarefa.getTipoTarefa());
        existente.setStatus(tarefa.getStatus());

        return tarefaRepository.save(existente);
    }

    public void excluir(Long id) {

        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Tarefa não encontrada"));

        tarefaRepository.delete(tarefa);
    }
}