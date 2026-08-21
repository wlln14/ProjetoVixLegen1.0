package br.com.VixLegen.ProjetoVixLegen10.Service;

import br.com.VixLegen.ProjetoVixLegen10.Model.Notificacao;
import br.com.VixLegen.ProjetoVixLegen10.Repository.NotificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacaoService {

    @Autowired
    private NotificacaoRepository repository;

    public Notificacao cadastrar(Notificacao notificacao) {
        return repository.save(notificacao);
    }

    public List<Notificacao> listar() {
        return repository.findAll();
    }

    public Notificacao buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Notificação não encontrada"));
    }

    public Notificacao atualizar(Long id, Notificacao dados) {

        Notificacao notificacao = buscarPorId(id);

        notificacao.setMensagem(dados.getMensagem());
        notificacao.setDataEnvio(dados.getDataEnvio());
        notificacao.setCanal(dados.getCanal());
        notificacao.setStatus(dados.getStatus());

        return repository.save(notificacao);
    }

    public void excluir(Long id) {

        Notificacao notificacao = buscarPorId(id);

        repository.delete(notificacao);
    }
}
