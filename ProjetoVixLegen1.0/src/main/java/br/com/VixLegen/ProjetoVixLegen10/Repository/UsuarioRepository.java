package br.com.VixLegen.ProjetoVixLegen10.Repository;

import br.com.VixLegen.ProjetoVixLegen10.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
