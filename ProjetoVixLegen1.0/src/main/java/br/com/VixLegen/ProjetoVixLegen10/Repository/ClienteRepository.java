package br.com.VixLegen.ProjetoVixLegen10.Repository;

import br.com.VixLegen.ProjetoVixLegen10.Model.Cliente;
import br.com.VixLegen.ProjetoVixLegen10.Model.ProcessoJuridico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<ProcessoJuridico> findByClienteIdCliente(Long idCliente);

    boolean existsByCpf(String cpf);

    List<Cliente> findByNomeCompletoContainingIgnoreCase(String nome);
}
