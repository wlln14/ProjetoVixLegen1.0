package br.com.VixLegen.ProjetoVixLegen10.Service;

import br.com.VixLegen.ProjetoVixLegen10.Model.Usuario;
import br.com.VixLegen.ProjetoVixLegen10.Repository.UsuarioRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario cadastrar(Usuario usuario) {
    return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public Usuario atualizar(Long id, Usuario usuario) {

        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuarioExistente.setPrimeiroNome(usuario.getPrimeiroNome());
        usuarioExistente.setUltimoNome(usuario.getUltimoNome());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setSenhaHash(usuario.getSenhaHash());
        usuarioExistente.setTelefone(usuario.getTelefone());
        usuarioExistente.setCpf(usuario.getCpf());
        usuarioExistente.setRg(usuario.getRg());
        usuarioExistente.setEmpresa(usuario.getEmpresa());
        usuarioExistente.setNumeroOAB(usuario.getNumeroOAB());
        usuarioExistente.setDataNascimento(usuario.getDataNascimento());
        usuarioExistente.setEstado(usuario.getEstado());
        usuarioExistente.setCidade(usuario.getCidade());
        usuarioExistente.setCep(usuario.getCep());
        usuarioExistente.setAtivo(usuario.isAtivo());

        return usuarioRepository.save(usuarioExistente);
    }

    public void excluir(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuarioRepository.delete(usuario);
    }
}
