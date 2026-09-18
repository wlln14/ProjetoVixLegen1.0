package br.com.VixLegen.ProjetoVixLegen10.Service;

import br.com.VixLegen.ProjetoVixLegen10.Exception.RecursoNaoEncontradoException;
import br.com.VixLegen.ProjetoVixLegen10.Exception.RegraNegocioException;
import br.com.VixLegen.ProjetoVixLegen10.Model.Categoria;
import br.com.VixLegen.ProjetoVixLegen10.Model.Usuario;
import br.com.VixLegen.ProjetoVixLegen10.Repository.CategoriaRepository;
import br.com.VixLegen.ProjetoVixLegen10.Repository.UsuarioRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import br.com.VixLegen.ProjetoVixLegen10.DTOs.Request.UsuarioRequest;
import br.com.VixLegen.ProjetoVixLegen10.DTOs.Response.UsuarioResponse;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            CategoriaRepository categoriaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public UsuarioResponse cadastrar(UsuarioRequest request) {

        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new RegraNegocioException("E-mail já cadastrado");
        }

        if (usuarioRepository.existsByCpf(request.getCpf())) {
            throw new RegraNegocioException("CPF já cadastrado");
        }

        Usuario usuario = converterParaEntidade(request);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return converterParaResponse(usuarioSalvo);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));
    }

    public Usuario atualizar(Long id, Usuario usuario) {

        Usuario usuarioExistente = buscarUsuarioAtivo(id);

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

        Categoria categoria = categoriaRepository.findById(
                usuario.getCategoria().getCodigoCategoria()
        ).orElseThrow(() ->
                new RecursoNaoEncontradoException("Categoria não encontrada"));

        usuarioExistente.setCategoria(categoria);

        return usuarioRepository.save(usuarioExistente);
    }

    public void excluir(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));

        usuarioRepository.delete(usuario);
    }

    public Usuario buscarUsuarioAtivo(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Usuário não encontrado"));

        if (!usuario.isAtivo()) {
            throw new RecursoNaoEncontradoException("Usuário está inativo");
        }

        return usuario;
    }

    public Usuario buscarPorEmail(String email) {

        return usuarioRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Usuário não encontrado"));
    }

    public Usuario buscarPorCpf(String cpf) {

        return usuarioRepository.findByCpf(cpf)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Usuário não encontrado"));
    }

    public List<Usuario> listarAtivos() {
        return usuarioRepository.findByAtivoTrue();
    }


    private Usuario converterParaEntidade(UsuarioRequest request) {

        Usuario usuario = new Usuario();

        usuario.setPrimeiroNome(request.getPrimeiroNome());
        usuario.setUltimoNome(request.getUltimoNome());
        usuario.setEmail(request.getEmail());
        usuario.setSenhaHash(request.getSenhaHash());
        usuario.setTelefone(request.getTelefone());
        usuario.setCpf(request.getCpf());
        usuario.setRg(request.getRg());
        usuario.setEmpresa(request.getEmpresa());
        usuario.setNumeroOAB(request.getNumeroOAB());
        usuario.setDataNascimento(request.getDataNascimento());
        usuario.setEstado(request.getEstado());
        usuario.setCidade(request.getCidade());
        usuario.setCep(request.getCep());

        Categoria categoria = categoriaRepository.findById(
                request.getCodigoCategoria()
        ).orElseThrow(() ->
                new RecursoNaoEncontradoException(
                        "Categoria não encontrada"));

        usuario.setCategoria(categoria);

        return usuario;
    }

    private UsuarioResponse converterParaResponse(Usuario usuario) {

        UsuarioResponse response = new UsuarioResponse();

        response.setIdUsuario(usuario.getIdUsuario());
        response.setPrimeiroNome(usuario.getPrimeiroNome());
        response.setUltimoNome(usuario.getUltimoNome());
        response.setEmail(usuario.getEmail());
        response.setTelefone(usuario.getTelefone());
        response.setCpf(usuario.getCpf());
        response.setRg(usuario.getRg());
        response.setEmpresa(usuario.getEmpresa());
        response.setNumeroOAB(usuario.getNumeroOAB());
        response.setDataNascimento(usuario.getDataNascimento());
        response.setEstado(usuario.getEstado());
        response.setCidade(usuario.getCidade());
        response.setCep(usuario.getCep());
        response.setAtivo(usuario.isAtivo());

        if (usuario.getCategoria() != null) {
            response.setCodigoCategoria(
                    usuario.getCategoria().getCodigoCategoria()
            );
        }

        return response;
    }

}
