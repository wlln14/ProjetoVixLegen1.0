package br.com.VixLegen.ProjetoVixLegen10.Service;

import br.com.VixLegen.ProjetoVixLegen10.Exception.RecursoNaoEncontradoException;
import br.com.VixLegen.ProjetoVixLegen10.Enums.StatusTarefa;
import br.com.VixLegen.ProjetoVixLegen10.Exception.RegraNegocioException;
import br.com.VixLegen.ProjetoVixLegen10.Model.ProcessoJuridico;
import br.com.VixLegen.ProjetoVixLegen10.Model.Tarefa;
import br.com.VixLegen.ProjetoVixLegen10.Model.Usuario;
import br.com.VixLegen.ProjetoVixLegen10.Repository.ProcessoJuridicoRepository;
import br.com.VixLegen.ProjetoVixLegen10.Repository.TarefaRepository;
import br.com.VixLegen.ProjetoVixLegen10.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final ProcessoJuridicoRepository processoRepository;
    private final UsuarioRepository usuarioRepository;

    public TarefaService(
            TarefaRepository tarefaRepository,
            ProcessoJuridicoRepository processoRepository,
            UsuarioRepository usuarioRepository) {

        this.tarefaRepository = tarefaRepository;
        this.processoRepository = processoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // CREATE
    public Tarefa cadastrar(Tarefa tarefa) {

        ProcessoJuridico processo = processoRepository.findById(
                tarefa.getProcesso().getIdProcesso()
        ).orElseThrow(() ->
                new RecursoNaoEncontradoException("Processo jurídico não encontrado"));

        Usuario usuario = usuarioRepository.findById(
                tarefa.getUsuarioResponsavel().getIdUsuario()
        ).orElseThrow(() ->
                new RecursoNaoEncontradoException("Usuário responsável não encontrado"));

        tarefa.setProcesso(processo);
        tarefa.setUsuarioResponsavel(usuario);

        return tarefaRepository.save(tarefa);
    }

    // READ
    public List<Tarefa> listarTodos() {
        return tarefaRepository.findAll();
    }

    // READ por ID
    public Tarefa buscarPorId(Long id) {

        return tarefaRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Tarefa não encontrada"));
    }

    // UPDATE
    public Tarefa atualizar(Long id, Tarefa tarefa) {

        Tarefa existente = tarefaRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Tarefa não encontrada"));

        ProcessoJuridico processo = processoRepository.findById(
                tarefa.getProcesso().getIdProcesso()
        ).orElseThrow(() ->
                new RecursoNaoEncontradoException("Processo jurídico não encontrado"));

        Usuario usuario = usuarioRepository.findById(
                tarefa.getUsuarioResponsavel().getIdUsuario()
        ).orElseThrow(() ->
                new RecursoNaoEncontradoException("Usuário responsável não encontrado"));

        existente.setDataAtribuicao(tarefa.getDataAtribuicao());
        existente.setPrazo(tarefa.getPrazo());
        existente.setTipoTarefa(tarefa.getTipoTarefa());
        existente.setStatus(tarefa.getStatus());
        existente.setProcesso(processo);
        existente.setUsuarioResponsavel(usuario);

        return tarefaRepository.save(existente);
    }

    // DELETE
    public void excluir(Long id) {

        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Tarefa não encontrada"));

        tarefaRepository.delete(tarefa);
    }

    public Tarefa concluir(Long id) {

        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Tarefa não encontrada"));

        tarefa.setStatus(StatusTarefa.CONCLUIDA);

        return tarefaRepository.save(tarefa);
    }

    public Tarefa atribuir(
            Long idTarefa,
            Long idUsuario,
            Long idProcesso) {

        Tarefa tarefa = tarefaRepository.findById(idTarefa)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Tarefa não encontrada"));

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Usuário não encontrado"));

        ProcessoJuridico processo = processoRepository.findById(idProcesso)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Processo jurídico não encontrado"));

        tarefa.setUsuarioResponsavel(usuario);
        tarefa.setProcesso(processo);
        tarefa.setStatus(StatusTarefa.PENDENTE);

        return tarefaRepository.save(tarefa);
    }

    public Tarefa alterarPrazo(Long id, LocalDate novoPrazo) {

        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Tarefa não encontrada"));

        if (tarefa.getStatus() == StatusTarefa.CONCLUIDA) {
            throw new RegraNegocioException(
                    "Não é possível alterar o prazo de uma tarefa concluída");
        }

        if (novoPrazo.isBefore(tarefa.getDataAtribuicao())) {
            throw new RegraNegocioException(
                    "O prazo não pode ser anterior à data de atribuição");
        }

        tarefa.setPrazo(novoPrazo);

        return tarefaRepository.save(tarefa);
    }
}