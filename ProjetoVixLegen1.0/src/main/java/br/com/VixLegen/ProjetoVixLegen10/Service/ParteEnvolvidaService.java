package br.com.VixLegen.ProjetoVixLegen10.Service;

import br.com.VixLegen.ProjetoVixLegen10.Model.ParteEnvolvida;
import br.com.VixLegen.ProjetoVixLegen10.Repository.ParteEnvolvidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParteEnvolvidaService {

    @Autowired
    private ParteEnvolvidaRepository repository;

    public ParteEnvolvida cadastrar(ParteEnvolvida parte){
        return repository.save(parte);
    }

    public List<ParteEnvolvida> listar(){
        return repository.findAll();
    }

    public ParteEnvolvida buscarPorId(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Parte Não Encontrada"));
    }

    public ParteEnvolvida atualizar(Long id, ParteEnvolvida dados){
        ParteEnvolvida parte = buscarPorId(id);

        parte.setPosicaoCliente(dados.getPosicaoCliente());
        parte.setParteContraria(dados.getParteContraria());
        parte.setAdvogadoContrario(dados.getAdvogadoContrario());
        parte.setJuizResponsavel(dados.getJuizResponsavel());

        return repository.save(parte);
    }

    public void excluir(Long id){
        ParteEnvolvida parte = buscarPorId(id);
        repository.delete(parte);
    }
}
