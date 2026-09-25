package br.com.VixLegen.ProjetoVixLegen10.Service;

import br.com.VixLegen.ProjetoVixLegen10.Exception.RecursoNaoEncontradoException;
import br.com.VixLegen.ProjetoVixLegen10.Exception.RegraNegocioException;
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
        validarDocumentoDuplicado(cliente, null);
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Cliente não encontrado"));
    }

    public List<ProcessoJuridico> listarProcessos(Long idCliente) {
        buscarPorId(idCliente);
        return processoRepository.findByClienteIdCliente(idCliente);
    }

    public Cliente atualizar(Long id, Cliente cliente) {

        Cliente clienteExistente = buscarPorId(id);
        validarDocumentoDuplicado(cliente, id);

        clienteExistente.setNomeCompleto(cliente.getNomeCompleto());
        clienteExistente.setEmail(cliente.getEmail());
        clienteExistente.setTelefone(cliente.getTelefone());
        clienteExistente.setCpf(cliente.getCpf());
        clienteExistente.setCnpj(cliente.getCnpj());

        return clienteRepository.save(clienteExistente);
    }

    public void excluir(Long id) {

        Cliente cliente = buscarPorId(id);

        if (!processoRepository.findByClienteIdCliente(id).isEmpty()) {
            throw new RegraNegocioException(
                    "Não é possível excluir um cliente com processos vinculados");
        }

        clienteRepository.delete(cliente);
    }

    public List<ProcessoJuridico> consultarHistorico(Long idCliente) {
        buscarPorId(idCliente);
        return processoRepository.findByClienteIdCliente(idCliente);
    }

    private void validarDocumentoDuplicado(Cliente cliente, Long idClienteAtual) {

        if (cliente.getCpf() != null && !cliente.getCpf().isBlank()) {
            boolean cpfDuplicado = idClienteAtual == null
                    ? clienteRepository.existsByCpf(cliente.getCpf())
                    : clienteRepository.existsByCpfAndIdClienteNot(
                            cliente.getCpf(), idClienteAtual);

            if (cpfDuplicado) {
                throw new RegraNegocioException("CPF já cadastrado");
            }
        }

        if (cliente.getCnpj() != null && !cliente.getCnpj().isBlank()) {
            boolean cnpjDuplicado = idClienteAtual == null
                    ? clienteRepository.existsByCnpj(cliente.getCnpj())
                    : clienteRepository.existsByCnpjAndIdClienteNot(
                            cliente.getCnpj(), idClienteAtual);

            if (cnpjDuplicado) {
                throw new RegraNegocioException("CNPJ já cadastrado");
            }
        }
    }
}
