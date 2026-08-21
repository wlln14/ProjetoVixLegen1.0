package br.com.VixLegen.ProjetoVixLegen10.Service;

import br.com.VixLegen.ProjetoVixLegen10.Model.MovimentacaoProcessual;
import br.com.VixLegen.ProjetoVixLegen10.Repository.MovimentacaoProcessualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimentacaoProcessualService {

    @Autowired
    private MovimentacaoProcessualRepository repository;

    public MovimentacaoProcessual cadastrar(
            MovimentacaoProcessual movimentacao) {

        return repository.save(movimentacao);
    }

    public List<MovimentacaoProcessual> listar() {
        return repository.findAll();
    }

    public MovimentacaoProcessual buscarPorId(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Movimentação não encontrada"));
    }

    public MovimentacaoProcessual atualizar(
            Long id,
            MovimentacaoProcessual dados) {

        MovimentacaoProcessual movimentacao = buscarPorId(id);

        movimentacao.setData(dados.getData());
        movimentacao.setDescricao(dados.getDescricao());

        return repository.save(movimentacao);
    }

    public void excluir(Long id) {

        MovimentacaoProcessual movimentacao = buscarPorId(id);

        repository.delete(movimentacao);
    }
}
