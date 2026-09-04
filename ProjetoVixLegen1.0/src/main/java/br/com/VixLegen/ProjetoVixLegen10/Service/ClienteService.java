package br.com.VixLegen.ProjetoVixLegen10.Service;

import br.com.VixLegen.ProjetoVixLegen10.Model.Cliente;
import br.com.VixLegen.ProjetoVixLegen10.Model.ProcessoJuridico;
import br.com.VixLegen.ProjetoVixLegen10.Repository.ClienteRepository;
import br.com.VixLegen.ProjetoVixLegen10.Repository.ProcessoJuridicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ProcessoJuridicoRepository processoRepository;

    public ClienteService(
            ClienteRepository clienteRepository,
            ProcessoJuridicoRepository processoRepository) {

        this.clienteRepository = clienteRepository;
        this.processoRepository = processoRepository;
    }

    public Cliente cadastrar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Cliente não encontrado"));
    }

    public List<ProcessoJuridico> listarProcessos(Long idCliente) {

        if (!clienteRepository.existsById(idCliente)) {
            throw new RuntimeException("Cliente não encontrado");
        }

        return processoRepository.findByClienteIdCliente(idCliente);
    }

    public Cliente atualizar(Long id, Cliente cliente) {

        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Cliente não encontrado"));

        clienteExistente.setNomeCompleto(cliente.getNomeCompleto());
        clienteExistente.setEmail(cliente.getEmail());
        clienteExistente.setTelefone(cliente.getTelefone());
        clienteExistente.setCpf(cliente.getCpf());

        return clienteRepository.save(clienteExistente);
    }

    public void excluir(Long id) {

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Cliente não encontrado"));

        clienteRepository.delete(cliente);
    }
}