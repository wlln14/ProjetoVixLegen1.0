package br.com.VixLegen.ProjetoVixLegen10.Service;

import br.com.VixLegen.ProjetoVixLegen10.Model.Notificacao;
import br.com.VixLegen.ProjetoVixLegen10.Model.Usuario;
import br.com.VixLegen.ProjetoVixLegen10.Repository.NotificacaoRepository;
import br.com.VixLegen.ProjetoVixLegen10.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacaoService {

    private final NotificacaoRepository notificacaoRepository;
    private final UsuarioRepository usuarioRepository;

    public NotificacaoService(
            NotificacaoRepository notificacaoRepository,
            UsuarioRepository usuarioRepository) {

        this.notificacaoRepository = notificacaoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // CREATE
    public Notificacao cadastrar(Notificacao notificacao) {

        Usuario usuario = usuarioRepository.findById(
                notificacao.getUsuario().getIdUsuario()
        ).orElseThrow(() ->
                new RuntimeException("Usuário não encontrado"));

        notificacao.setUsuario(usuario);

        return notificacaoRepository.save(notificacao);
    }

    // READ
    public List<Notificacao> listarTodos() {
        return notificacaoRepository.findAll();
    }

    // READ por ID
    public Notificacao buscarPorId(Long id) {

        return notificacaoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Notificação não encontrada"));
    }

    // UPDATE
    public Notificacao atualizar(
            Long id,
            Notificacao notificacao) {

        Notificacao existente = notificacaoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Notificação não encontrada"));

        Usuario usuario = usuarioRepository.findById(
                notificacao.getUsuario().getIdUsuario()
        ).orElseThrow(() ->
                new RuntimeException("Usuário não encontrado"));

        existente.setMensagem(notificacao.getMensagem());
        existente.setDataEnvio(notificacao.getDataEnvio());
        existente.setCanal(notificacao.getCanal());
        existente.setStatus(notificacao.getStatus());
        existente.setUsuario(usuario);

        return notificacaoRepository.save(existente);
    }

    // DELETE
    public void excluir(Long id) {

        Notificacao notificacao = notificacaoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Notificação não encontrada"));

        notificacaoRepository.delete(notificacao);
    }
}