package br.com.VixLegen.ProjetoVixLegen10.Service;

import br.com.VixLegen.ProjetoVixLegen10.Model.ClassificacaoProcesso;
import br.com.VixLegen.ProjetoVixLegen10.Repository.ClassificacaoProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassificacaoProcessoService {
    @Autowired
    private ClassificacaoProcessoRepository repository;

    public ClassificacaoProcesso cadastrar(ClassificacaoProcesso classificacao) {
        return repository.save(classificacao);
    }

    public List<ClassificacaoProcesso> listar() {
        return repository.findAll();
    }

    public ClassificacaoProcesso buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Classificação não encontrada"));
    }

    public ClassificacaoProcesso atualizar(
            Long id,
            ClassificacaoProcesso dados) {

        ClassificacaoProcesso classificacao = buscarPorId(id);

        classificacao.setStatus(dados.getStatus());
        classificacao.setAreaDireito(dados.getAreaDireito());
        classificacao.setTipoAcao(dados.getTipoAcao());
        classificacao.setFaseProcessual(dados.getFaseProcessual());
        classificacao.setDescricaoObjeto(dados.getDescricaoObjeto());

        return repository.save(classificacao);
    }

    public void excluir(Long id) {
        ClassificacaoProcesso classificacao = buscarPorId(id);
        repository.delete(classificacao);
    }
}
